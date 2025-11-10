package testCase.pages;

import constant.LoginPageConstant;
import constant.WaitTimeConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver) {
		super(driver);
	}


	public void verifyLoginUserName(String expectedUsername) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement loginTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageConstant.loginTextElement)));
		String actualUsername = loginTextElement.getText().trim();

		if (actualUsername.equals(expectedUsername)) {
			System.out.println("Username match: " + actualUsername);
		} else {
			System.out.println("Username mismatch. Expected: " + expectedUsername + ", Found: " + actualUsername);
		}
	}

	public void verifyUserName(String expectedOutcome) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement loginTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageConstant.loginTextElement)));
		String actualUsername = loginTextElement.getText().trim();
		String expectedUsername = expectedOutcome.split("@")[0];

		if (actualUsername.equals(expectedUsername)) {
			System.out.println("Username match: " + actualUsername);
		} else {
			System.out.println("Username mismatch. Expected: " + expectedUsername + ", Found: " + actualUsername);
		}
	}

	public void clickDeleteAccountButton() {
		driver.findElement(By.linkText(LoginPageConstant.deleteAccountButton)).click();
	}

	public void clickLogoutButton() {
		driver.findElement(By.linkText(LoginPageConstant.logoutButtonText)).click();
	}

	public void verifyAccountDeleteTextVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement deletedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath(LoginPageConstant.deletedMessage)));
		if (deletedMessage.isDisplayed() && deletedMessage.getText().equals("ACCOUNT DELETED!")) {
			System.out.println("Account deleted! is displayed");
			driver.findElement(By.linkText(LoginPageConstant.continueButton)).click();
		}
		else {
			System.out.println("Account deleted! is not displayed");
		}
	}
}
