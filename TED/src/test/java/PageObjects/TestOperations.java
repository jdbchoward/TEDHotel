package PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
		elementsRepositoryAction = ElementsRepositoryAction.getInstance(driver);
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

}
