package TEDHotelReservation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
/**   
* @Title: TED Hotel Filter Automation Test case 
* @Package TEDHotelReservation 
* @Description: Test Hotel Filter function
* @author: Howard
* @compay: PQA     
* @date 09/10/2016 
* @version V1.0   
*/
import org.testng.annotations.Test;

import PageObjects.BrowserLoader;
import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.TestOperations;
import PageObjects.Wait;
import junit.framework.Assert;

public class TestHotelFilter {
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
//		elementsRepositoryAction = ElementsRepositoryAction.getInstance(driver);
		elementsRepositoryAction = new ElementsRepositoryAction(driver);
		testOperation = PageFactory.initElements(driver, TestOperations.class);

	}

	@Test
	public void testFilterByRating() throws Exception {

		int expectedhotelNumber=0;
		int actualhotelnumber=0;
		testOperation.searchRooms();
		wait.threadWait(10000);
		String rateLabel=testOperation.filterByRating();
		expectedhotelNumber=testOperation.getHotelNumber(rateLabel);		
		
		String hotels=elementsRepositoryAction.getElementNoWait("TED_Filter_availableHotels").getText();
		actualhotelnumber=testOperation.getHotelNumber(hotels);
		
		Assert.assertEquals(expectedhotelNumber, actualhotelnumber);

	}



	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
	

}