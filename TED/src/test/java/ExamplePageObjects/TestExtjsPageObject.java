package ExamplePageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObjects.CommonActions;
import PageObjects.ElementsRepositoryAction;
import PageObjects.TestOperations;
import PageObjects.Wait;

public class TestExtjsPageObject {
	
	private WebDriver driver;
	private Wait wait;
	CommonActions common;
	ElementsRepositoryAction elementsRepositoryAction;
	static Logger log = Logger.getLogger(TestExtjsPageObject.class.getName());
	
	public TestExtjsPageObject(WebDriver driver)
	{
		this.driver=driver;
		wait = new Wait(driver);
		elementsRepositoryAction = new ElementsRepositoryAction(driver);
	}
	
	
	public void openUrl(String url)
	{
		driver.get(url);
		wait.WaitUntilPageLoaded();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	

	@FindBy(xpath = "//div[contains(text(),'Collapsible')]/../../*/img")
	public WebElement collapsibleBtn; 
	
	@FindBy(xpath = "//td[descendant::div[contains(text(),'Collapsible')]]/div/div[2]/div/div")
	public WebElement collapsibleTxt; 
	
	@FindBy(xpath = "//span[contains(text(),'Company')]")
	public WebElement array_grid_company;	
	
	@FindBy(xpath = "//span[contains(text(),'Company')]/following-sibling::div")
	public WebElement array_grid_company_menu;	

	@FindBy(xpath = "//span[contains(text(),'Columns')]")
	public WebElement array_grid_company_menu_Columns;	
	
	@FindBy(xpath = "//tr/td[1]/div")
	public WebElement array_grid_company_row1;
	
	@FindBy(xpath = "//span[contains(text(),'Price')]")
	public WebElement array_grid_price;
}
