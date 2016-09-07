package PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class TestOperations {
	
	private WebDriver driver;
	private String baseUrl;
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;
	
	public TestOperations(WebDriver driver)
	{
		this.driver=driver;
		baseUrl = "https://demox.mmxreservations.com/";
		wait = new Wait(driver);
		elementsRepositoryAction = ElementsRepositoryAction.getInstance(driver);
	}
	
	public void searchRooms()
	{
		driver.get(baseUrl + "ui#/fe2?pageCode=UC2026");
		wait.WaitUntilPageLoaded();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    

		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").clear();		
		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").sendKeys("Apr 23, 2018");
		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").clear();
		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").sendKeys("2");
		elementsRepositoryAction.getElement("TED_Search_btn").click();
	}

}
