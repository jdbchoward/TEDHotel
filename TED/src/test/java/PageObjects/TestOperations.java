package PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestOperations {

	private WebDriver driver;
	private String baseUrl;
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;
	static Logger log = Logger.getLogger(TestOperations.class.getName());

	public TestOperations(WebDriver driver) {
		this.driver = driver;
		baseUrl = "https://demox.mmxreservations.com/";
		wait = new Wait(driver);
//		elementsRepositoryAction = ElementsRepositoryAction.getInstance(driver);
		
		elementsRepositoryAction = new ElementsRepositoryAction(driver);
	}

	public void searchRooms() {
		driver.get(baseUrl + "ui#/fe2?pageCode=UC2026");
		wait.WaitUntilPageLoaded();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").clear();
		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").sendKeys("Apr 23, 2018");
		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").clear();
		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").sendKeys("2");
		elementsRepositoryAction.getElement("TED_Search_btn").click();
	}

	public void searchRoomsWhenDateWrong() {
		driver.get(baseUrl + "ui#/fe2?pageCode=UC2026");
		wait.WaitUntilPageLoaded();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait.threadWait(10000);
		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").clear();
		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").sendKeys("11, 2018");
		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").clear();
		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").sendKeys("2");
		// elementsRepositoryAction.getElement("TED_Search_btn").click();
	}

	public String getToolTip(String element, String attribute) {
		Actions action = new Actions(driver);
		WebElement tooltip = elementsRepositoryAction.getElementNoWait(element);

		wait.threadWait(2000);

		action.clickAndHold(tooltip).perform();
		String ToolTipText = tooltip.getAttribute(attribute);

		return ToolTipText;
	}
	
	
	public String filterByRating()
	{
//		elementsRepositoryAction.getElementNoWait("TED_Filter_rating").click();	
		
		
		List<WebElement> listWebElements=driver.findElements(By.xpath("//input[@type='checkbox']"));

		listWebElements.get(1).click();
//		for(WebElement w:listWebElements)
//		{
//			System.out.println(w.getAttribute(""));
//			System.out.println(w.getText());
//		}
		
		String rateText=elementsRepositoryAction.getElementNoWait("TED_Filter_lable").getText();
//		assertEquals(driver.findElement(By.cssSelector("em.ng-binding.firepath-matching-node")).getText(), "2 Hotels");
        return rateText;
	}
	
	public int getHotelNumber(String hotels)
	{
		if(!hotels.equals("")||hotels!=null)
		{
			String[] getHotelNumber=hotels.split(" ");
			return Integer.parseInt(getHotelNumber[0]);
		}
		return -1;
	}

	
	public WebElement getHotelOverViewInfo()
	{
		List<WebElement> listWebElements=driver.findElements(By.linkText("Hotel Overview"));
		if(listWebElements!=null && listWebElements.size()>0)
		return listWebElements.get(0);	
		return null;

	}
	

	public int getHotelRoomViewInfo()
	{
		int count=0;

		List<WebElement> listRoomButton=driver.findElements(By.linkText("Rooms"));
		if(listRoomButton!=null && listRoomButton.size()>0)
			listRoomButton.get(0).click();
		
		String hotelPriceText=elementsRepositoryAction.getElement("TED_HotelOverView_hotelPrice").getText();
		log.debug("hotelPriceText= "+hotelPriceText);
		List<WebElement> listWebElements=driver.findElements(By.xpath("//span[contains(text(),'$')]"));
		if(listWebElements!=null && listWebElements.size()>0)
		{
			for(WebElement w:listWebElements)
			{
				log.debug("w.getText()= " +w.getText());
				if(w.getText().equalsIgnoreCase(hotelPriceText))
					count++;
			}
			
		}
		log.debug("count= " + count);
		return count;
	}
	
	public void selectHotel()
	{
		List<WebElement> listRoomButton=driver.findElements(By.linkText("Select Hotel"));
		if(listRoomButton!=null && listRoomButton.size()>0)
			listRoomButton.get(0).click();
	}
	
	public void modifySelectedRoom()
	{
		//change days tooltip tool
		Actions action = new Actions(driver);
		WebElement tooltip = driver.findElement(By.xpath("//div[@id='day-dte']"));
		wait.threadWait(2000);
		action.clickAndHold(tooltip).perform();
		
		//change room number selector
		List<WebElement> listSelector=driver.findElements(By.xpath("//div[@class='col-sm-12 text-center']/div/select"));
		if(listSelector!=null && listSelector.size()>=2)
		{
			//change room quantity
			new Select(listSelector.get(0)).selectByVisibleText("2 Rooms");
			//change adults
			new Select(listSelector.get(1)).selectByVisibleText("1 Adult");
		}
		
		
	}
    public void addToBooking()
    {
    	List<WebElement> listRoomButton=driver.findElements(By.linkText("Add to Booking"));
		if(listRoomButton!=null && listRoomButton.size()>0)
			listRoomButton.get(0).click();
    	
    }
    
    
    public boolean verifyRoomInfor()
    {
    	//get room quanlity choosen
    	List<WebElement> listRoomButton=driver.findElements(By.xpath("//div[@class='row summary-label larger-font']/div/b"));
		if(listRoomButton!=null && listRoomButton.size()>0)
			listRoomButton.get(0).getText();
    	//div[@class='row summary-label larger-font']/div/b
		
		
		//get Total price
		
    	return false;
    }
    	
    }