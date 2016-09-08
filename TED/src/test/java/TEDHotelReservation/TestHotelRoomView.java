package TEDHotelReservation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
/**   
* @Title: TED Hotel Room view Automation Test case 
* @Package TEDHotelReservation 
* @Description: Test Hotel room view function
* @author: Howard
* @compay: PQA     
* @date 09/12/2016 
* @version V1.0   
*/
import org.testng.annotations.Test;

import PageObjects.BrowserLoader;
import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.TestOperations;
import PageObjects.Wait;
import junit.framework.Assert;

public class TestHotelRoomView {
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

		//on the hotel there will be a price tag shows "From $** /Night"
		//after you click "Rooms" button, the same price room will be show below
		//so if this room been show then this function is fine
		testOperation.searchRooms();
		wait.threadWait(10000);
		int hoteldisplayed = testOperation.getHotelRoomViewInfo();
		boolean isDispayed=(hoteldisplayed>=1?true:false);
	    Assert.assertTrue(isDispayed);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

}
