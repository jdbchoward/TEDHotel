package TEDHotelReservation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.BrowserLoader;
import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.TestOperations;
import PageObjects.Wait;
import junit.framework.Assert;


/**   
* @Title: TED HotelSearch Automation Test case 
* @Package TEDHotelReservation 
* @Description: Test Hotel Search function
* @author: Howard
* @compay: PQA     
* @date 09/10/2016 
* @version V1.0   
*/

public class TestRoomSearch {
	private WebDriver driver;
	private String baseUrl;
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;
	TestOperations testOperation;
	static Logger log = Logger.getLogger(TestRoomSearch.class.getName());

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {

		common = PageFactory.initElements(driver, CommonActions.class);
		String browserType = common.getSettings().getValue("browserType");
		BrowserLoader brower = new BrowserLoader(browserType);
		driver = brower.driver;
		// elementsRepositoryAction =
		// ElementsRepositoryAction.getInstance(driver);
		//
		// baseUrl = "https://demox.mmxreservations.com/";
		wait = new Wait(driver);

		testOperation = PageFactory.initElements(driver, TestOperations.class);

	}

	@Test
	public void testSearchInRegularWay() throws Exception {

		testOperation.searchRooms();

		// driver.findElement(By.xpath("(//input[@name='startDate'])[2]")).clear();
		// driver.findElement(By.xpath("(//input[@name='startDate'])[2]")).sendKeys("Apr
		// 20, 2018");
		// driver.findElement(By.xpath("(//input[@value='Search'])[2]")).click();

		wait.threadWait(10000);

		log.debug(driver.getTitle());
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Hotel List"));

	}

	@Test(dependsOnMethods = { "testSearchInRegularWay" })
	public void testSearchWhenDateFormatIsWrong() throws Exception {

		testOperation.searchRoomsWhenDateWrong();

		String tooltip = testOperation.getToolTip("TED_Search_tooltip", "content");
		Assert.assertTrue(tooltip.equalsIgnoreCase("Should be a valid date"));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();	
		driver.quit();
	}
	

}
