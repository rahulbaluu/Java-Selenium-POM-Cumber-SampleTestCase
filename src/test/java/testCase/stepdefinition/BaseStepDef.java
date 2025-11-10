package testCase.stepdefinition;

import org.openqa.selenium.WebDriver;
import testCase.pages.*;
import utilities.DriverSetup;
import utilities.ExcelUtils;

public class BaseStepDef {
	protected WebDriver driver;
	protected ExcelUtils excelUtils;
	protected BasePage basePage;
	protected CartPage cartPage;
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected ProductPage productPage;
	protected RegistrationPage registrationPage;
	protected SignupLoginPage signupPage;

	public BaseStepDef() {
		this.driver = DriverSetup.getDriver();

		this.basePage = new BasePage(driver);
		this.cartPage = new CartPage(driver);
		this.homePage = new HomePage(driver);
		this.loginPage = new LoginPage(driver);
		this.productPage = new ProductPage(driver);
		this.registrationPage = new RegistrationPage(driver);
		this.signupPage = new SignupLoginPage(driver);
	}

	public void loadExcel(String sheetName) throws Exception {
		excelUtils = new ExcelUtils();
		ExcelUtils.setExcelFile("src/test/resources/TestData.xlsx", sheetName);
	}
}
