package testCase.stepdefinition;

import constant.URLConstant;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import utilities.DriverSetup;


public class Hooks extends BaseStepDef {


	@Before
	public void beforeEachScenario() {
		driver = DriverSetup.getDriver();
		basePage.navigateTo(URLConstant.baseurl);
		System.out.println("Browser launched before scenario");
		System.out.println(driver.getTitle());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement consentURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(URLConstant.constantButton)));
		consentURL.click();
	}

	@After
	public void afterEachScenario() {
		DriverSetup.quit();
		System.out.println("Browser closed after scenario");
	}
}
