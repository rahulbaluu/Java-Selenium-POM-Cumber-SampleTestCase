package testCase.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import testCase.pages.*;
import utilities.DriverSetup;

import java.util.List;
import java.util.Map;

public class NewDeleteUserStepDef {
	WebDriver driver;
	HomePage homePage;
	SignupLoginPage signupPage;
	RegistrationPage registrationPage;
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;

	String actualUsername;
	String actualEmailAddress;

	public NewDeleteUserStepDef() {
		this.driver = DriverSetup.getDriver();
		this.homePage = new HomePage(driver);
		this.signupPage = new SignupLoginPage(driver);
		this.registrationPage = new RegistrationPage(driver);
		this.loginPage = new LoginPage(driver);
		this.productPage = new ProductPage(driver);
		this.cartPage = new CartPage(driver);
	}
	@Given("Click on Signup Login button")
	public void clickOnSignupLoginButton() {
		homePage.clickOnSignupButton();
	}

	@When("Verify New User Signup is visible")
	public void verifyNewUserSignupIsVisible() {
		signupPage.verifyLoginSignupPageVisible();
	}

	@And("Enter name {string}, email address {string}")
	public void enterNameEmailAddress(String name, String emailAddress) {
		actualUsername = name;
		actualEmailAddress = emailAddress;
		signupPage.enterTheNameEmailInSignupTextBox(actualUsername, actualEmailAddress);
	}

	@Then("Verify error {string} in signup is visible")
	public void verifyErrorEmailAddressAlreadyExistInSignupIsVisible(String expectedErrorMessage) {
		String actualErrorMessage = signupPage.getErrorMessageSignup();
		System.out.println("Actual error message on page: " + actualErrorMessage);
		System.out.println("Expected error message: " + expectedErrorMessage);
		Assert.assertEquals(expectedErrorMessage, "Email Address already exist!", actualErrorMessage);
	}

	@And("Verify ENTER ACCOUNT INFORMATION is visible")
	public void verifyENTERACCOUNTINFORMATIONIsVisible() {
		registrationPage.verifyTheTextPresent();
	}

	@And("Enter the details in the Signup form:")
	public void enterTheDetailsInTheSignupFormAndClickCreateAccountButton(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		Map<String, String> data = dataList.get(0);

		registrationPage.fillRegistrationForm(
				data.get("password"), data.get("firstName"), data.get("lastName"), data.get("company"),
				data.get("address1"), data.get("address2"), data.get("country"), data.get("state"),
				data.get("city"), data.get("zipcode"), data.get("mobileNumber"), data.get("date"), data.get("month"), data.get("year")
		);
	}

	@And("Verify ACCOUNT CREATED is visible and click Continue button")
	public void verifyACCOUNTCREATEDIsVisibleAndClickContinueButton() {
		registrationPage.verifyTheTextAccountCreated();
	}

	@And("Verify Login in as {string} is visible")
	public void verifyLoginInAsUsernameIsVisible(String expectedUsername) {
		loginPage.verifyLoginUserName(expectedUsername);
	}

	@And("click Signup button")
	public void clickSignupButton() {
		signupPage.clickSignupButton();
	}

	@And("Click Delete Account button")
	public void clickDeleteAccountButton() {
		loginPage.clickDeleteAccountButton();
	}

	@Then("Verify that Account Deleted is visible and click continue")
	public void verify_that_account_deleted_is_visible_and_click_continue() {
		loginPage.verifyAccountDeleteTextVisible();
	}

	@And("Enter name {string} and already registered email address {string}")
	public void enterNameAndAlreadyRegisteredEmailAddress(String name, String emailAddress) {
		signupPage.enterTheNameEmailInSignupTextBox(name, emailAddress);
	}

	@Given("Add products to cart")
	public void addProductsToCart() {
		productPage.productToCart();
	}

	@When("Click cart button")
	public void clickCartButton() {
		productPage.clickCart();
	}

	@And("Verify that cart page is displayed")
	public void verifyThatCartPageIsDisplayed() {
		cartPage.verifyTheCartPage();
	}

	@And("Click Proceed To Checkout")
	public void clickProceedToCheckout() {
		cartPage.clickCheckoutButton();
	}

	@And("Click RegisterLogin button")
	public void clickRegisterLoginButton() {
		cartPage.registerLogin();
	}

	@And("Fill all details in Signup and create account")
	public void fillAllDetailsInSignupAndCreateAccount() {
		actualUsername = "hello";
		actualEmailAddress = "r1h3l34@gmail.com";
		signupPage.enterTheNameEmailInSignupTextBox(actualUsername, actualEmailAddress);
		signupPage.clickSignupButton();
		registrationPage.verifyTheTextPresent();
	}

	@And("Click Cart button from login page")
	public void clickCartButtonFromLoginPage() {
		cartPage.clickCartButton();
	}

	@And("Verify Address Details and Review Your Order")
	public void verifyAddressDetailsAndReviewYourOrder() {
		cartPage.verifyAddressDetails();
	}

	@And("Enter description in {string} text area and click Place Order")
	public void enterDescriptionInCommentTextAreaAndClickPlaceOrder(String comment) {
		cartPage.enterDescriptionInCart(comment);
	}

	@And("Enter payment details:")
	public void enterPaymentDetails(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		Map<String, String> data = dataList.get(0);

		String nameOnCard = data.get("Name on Card");
		String cardNumber = data.get("Card Number");
		String cvc = data.get("CVC");
		String expMonth = data.get("Expiry Month");
		String expYear = data.get("Expiry Year");

		cartPage.enterPaymentDetails(nameOnCard, cardNumber, cvc, expMonth, expYear);
	}

	@And("Click Pay and Confirm Order button")
	public void clickPayAndConfirmOrderButton() {
		cartPage.clickPayAndConfirm();
	}

	@And("Verify success message {string}")
	public void verifySuccessMessageYourOrderHasBeenPlacedSuccessfully(String expectedMessage) {
		cartPage.verifySuccessMessageYourOrder(expectedMessage);
	}
}
