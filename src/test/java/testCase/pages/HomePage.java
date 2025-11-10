package testCase.pages;

import constant.HomePageConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnSignupButton() {
		driver.findElement(By.linkText(HomePageConstant.signupLoginButton)).click();
	}
}
