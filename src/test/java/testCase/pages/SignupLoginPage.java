package testCase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class SignupLoginPage extends BasePage {
	public SignupLoginPage(WebDriver driver) {
		super(driver);
	}

	public void verifyLoginSignupPageVisible() {
		System.out.println(driver.getTitle());
		if (Objects.equals(driver.getTitle(), "Automation Exercise - Signup / Login")) {
			System.out.println("Login/Signup Page Visible Successfully");
		} else {
			System.out.println("Login/Signup Page Not Visible");
		}
	}

	public void enterTheNameEmailInSignupTextBox(String username, String email) {
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys(email);
	}

	public void clickSignupButton() {
		driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();
	}

	public void enterTheEmailAndPasswordInLoginTextBox(String email, String password) {
		driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(password);
	}

	public void enterTheIncorrectEmailAndPasswordInLoginTextBox(String email, String password) {
		driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(password);
	}

	public String getErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorMessageElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//p[text()='Your email or password is incorrect!']")));
		return errorMessageElement.getText();
	}

	public String getErrorMessageSignup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorMessageElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//p[text()='Email Address already exist!']")));
		return errorMessageElement.getText();
	}

	public void clickLoginButton() {
		driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
	}
}
