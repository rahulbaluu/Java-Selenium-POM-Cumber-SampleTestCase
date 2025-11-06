package testCase.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import testCase.pages.HomePage;
import testCase.pages.LoginPage;
import testCase.pages.SignupLoginPage;
import utilities.DriverSetup;

public class LoginLogoutStepDef {
	WebDriver driver;
	HomePage homePage;
	SignupLoginPage signupPage;
	LoginPage loginPage;
	String loginUsername;
	String loginPassword;

	public LoginLogoutStepDef() {
		this.driver = DriverSetup.getDriver();
		this.homePage = new HomePage(driver);
		this.signupPage = new SignupLoginPage(driver);
		this.loginPage = new LoginPage(driver);
	}

	@Given("Click Signup Login button")
	public void clickSignupLoginButton() {
		homePage.clickOnSignupButton();
	}

	@When("Enter the email {string} and password {string}")
	public void enterTheCorrectEmailAndPassword(String email, String password) {
		this.loginUsername = email;
		this.loginPassword = password;
		signupPage.enterTheEmailAndPasswordInLoginTextBox(email,password);
	}

	@And("Click the login button")
	public void clickTheLoginButton() {
		signupPage.clickLoginButton();
	}

	@And("Verify {string}")
	public void verifyLoginAsUsernameIsVisible(String expectedOutcome) {
		loginPage.verifyUserName(expectedOutcome);
	}

	@And("Enter the incorrect email {string} and password {string}")
	public void enterTheIncorrectEmailAndPassword(String email, String password) {
		this.loginUsername = email;
		this.loginPassword = password;
		signupPage.enterTheIncorrectEmailAndPasswordInLoginTextBox(email,password);
	}

	@And("Click the Logout button")
	public void clickTheLogoutButton() {
		loginPage.clickLogoutButton();
	}

	@Then("Verify it is navigated to the login page")
	public void verifyItIsNavigatedToTheLoginPage() {
		signupPage.verifyLoginSignupPageVisible();
	}

	@Then("Verify error {string} is visible")
	public void verifyErrorYourEmailOrPasswordIsIncorrectIsVisible(String expectedErrorMessage) {
		String actualErrorMessage = signupPage.getErrorMessage();

		System.out.println("Actual error message on page: " + actualErrorMessage);
		System.out.println("Expected error message: " + expectedErrorMessage);

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");
	}
}
