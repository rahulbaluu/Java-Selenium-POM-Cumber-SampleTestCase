package testCase.stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testCase.pages.BasePage;
import testCase.pages.HomePage;
import utilities.DriverSetup;


public class Hooks {
	public static WebDriver driver;
	public static BasePage basePage;

	@Before
	public void beforeEachScenario() {
		driver = DriverSetup.getDriver();
		basePage = new HomePage(driver);
		basePage.navigateTo("https://www.automationexercise.com");
		System.out.println("Browser launched before scenario");
		System.out.println(driver.getTitle());
		driver.findElement(By.cssSelector("p.fc-button-label")).click();
	}

	@After
	public void afterEachScenario() {
		DriverSetup.quit();
		System.out.println("Browser closed after scenario");
	}
}
