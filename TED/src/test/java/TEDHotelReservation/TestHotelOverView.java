package TEDHotelReservation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
/**   
* @Title: TED Hotel Overview Automation Test case 
* @Package TEDHotelReservation 
* @Description: Test Hotel Overview function
* @author: Howard
* @compay: PQA     
* @date 09/11/2016 
* @version V1.0   
*/
import org.testng.annotations.Test;

import PageObjects.BrowserLoader;
import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.TestOperations;
import PageObjects.Wait;
import junit.framework.Assert;

public class TestHotelOverView {
	private WebDriver driver;
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;
	TestOperations testOperation;
	static Logger log = Logger.getLogger(TestHotelFilter.class.getName());

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {

		common = PageFactory.initElements(driver, CommonActions.class);
		String browserType = common.getSettings().getValue("browserType");
		BrowserLoader brower = new BrowserLoader(browserType);
		driver = brower.driver;
		wait = new Wait(driver);
		elementsRepositoryAction = new ElementsRepositoryAction(driver);
		testOperation = PageFactory.initElements(driver, TestOperations.class);

	}

	@Test
	public void testHotelOverViewFunction() throws Exception {

		testOperation.searchRooms();
		wait.threadWait(10000);
		WebElement webElement = testOperation.getHotelOverViewInfo();
		if (webElement != null) {
			webElement.click();
			wait.waitForElementPresent(
					elementsRepositoryAction.getByObjectFromKey("TED_HotelOverView_viewLabel", null));
			String hotelReview = elementsRepositoryAction.getElementNoWait("TED_HotelOverView_viewLabel").getText();
			String hotelDetail = elementsRepositoryAction.getElementNoWait("TED_HotelOverView_HotelDetailText")
					.getText();
			Assert.assertEquals("Hotel Description", hotelReview);
			Assert.assertTrue(hotelDetail.contains("Fairmont Waterfront"));
		}

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

}
