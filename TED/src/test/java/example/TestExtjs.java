package example;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ExamplePageObjects.TestExtjsPageObject;
import PageObjects.BrowserLoader;
import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.TestOperations;
import PageObjects.Wait;
import junit.framework.Assert;

public class TestExtjs {
	private WebDriver driver;
	private String baseUrl;
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;
	TestExtjsPageObject testObject;
	static Logger log = Logger.getLogger(TestExtjs.class.getName());

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

		testObject = PageFactory.initElements(driver, TestExtjsPageObject.class);

	}

	@Test (enabled=false)
	public void testBasicPanel() throws Exception {

		String expectString="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
		String url="http://examples.sencha.com/extjs/5.0.0/examples/kitchensink/#basic-panels";
		testObject.openUrl(url);
		testObject.collapsibleBtn.click(); 
		String lable=testObject.collapsibleTxt.getText();
        Assert.assertEquals(expectString, lable);
	}
	
	@Test (enabled=true)
	public void testArrayGrid()
	{
		String expect="Intel Corporation";
		String url="http://dev.sencha.com/extjs/5.0.0/examples/kitchensink/#array-grid";
		testObject.openUrl(url);		
		common.javascriptClick(driver, testObject.array_grid_company_menu);
		wait.threadWait(5000);
		testObject.array_grid_company_menu_Columns.click();
		testObject.array_grid_price.click();
		String row1=testObject.array_grid_company_row1.getText();
		Assert.assertEquals(expect, row1);
	}



	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();	
		driver.quit();
	}
	

}
