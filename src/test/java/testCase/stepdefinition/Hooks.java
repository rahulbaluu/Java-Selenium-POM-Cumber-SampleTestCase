package testCase.stepdefinition;

import constant.URLConstant;
import constant.WaitTimeConstant;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverSetup;

import java.time.Duration;


public class Hooks extends BaseStepDef {


	@Before
	public void beforeEachScenario() {
		driver = DriverSetup.getDriver();
		basePage.navigateTo(URLConstant.baseurl);
		System.out.println("Browser launched before scenario");
		System.out.println(driver.getTitle());
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		//WebElement consentURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(URLConstant.constantButton)));
		//consentURL.click();
		//While executing in the GitHubAction their will not be any pop-up message or add will not be displayed
	}

	@After
	public void afterEachScenario() {
		DriverSetup.quit();
		System.out.println("Browser closed after scenario");
	}
}
