package TEDHotelReservation;

/**   
* @Title: TED Hotel Reservation Automation Test case 
* @Package TEDHotelReservation 
* @Description: Test room search function
* @author: Howard
* @compay: PQA     
* @date 09/10/2016 
* @version V1.0   
*/ 

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.BrowserLoader;
import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.Wait;
import junit.framework.Assert;

public class TestRoomSearch {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		common = PageFactory.initElements(driver, CommonActions.class);
		String browserType = common.getSettings().getValue("browserType");
		BrowserLoader brower = new BrowserLoader(browserType);
		driver = brower.driver;
		elementsRepositoryAction = ElementsRepositoryAction.getInstance(driver);
		
		baseUrl = "https://demox.mmxreservations.com/";
		wait = new Wait(driver);
		
	}

	@Test
	public void testRanorexTestCase() throws Exception {
		driver.get(baseUrl + "ui#/fe2?pageCode=UC2026");
		wait.WaitUntilPageLoaded();
		
	    driver.findElement(By.xpath("(//input[@name='startDate'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@name='startDate'])[2]")).sendKeys("Apr 23, 2018");
	    driver.findElement(By.xpath("(//input[@name='endDate'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@name='endDate'])[2]")).sendKeys("Apr 24, 2018");
	  
	    
	    
	    
//		elementsRepositoryAction.getElementNoWait("TED_Search_StartDate").sendKeys("Apr 23, 2018");
//		elementsRepositoryAction.getElementNoWait("TED_Search_EndDate").sendKeys("Apr 24, 2018");
//		elementsRepositoryAction.getElementNoWait("TED_Search_RoomCount").sendKeys("2");
		elementsRepositoryAction.getElement("TED_Search_btn").click();

		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Hotel List"));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
