package testCase.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testCase.pages.*;


public class CartStepDef extends BaseStepDef{

	String emailAddress;

	@Given("Click Cart button")
	public void clickCartButton() {
		cartPage = new CartPage(driver);
		cartPage.clickCartButton();
	}

	@When("Scroll down to footer")
	public void scrollDownToFooter() {
		cartPage.scrollDownToFooter();
	}

	@And("Verify text {string}")
	public void verifyTextSUBSCRIPTION(String subscription) {
		cartPage.verifyTheTextSubscription(subscription);
	}

	@And("Enter email {string} in input and click arrow button")
	public void enterEmailAddressInInputAndClickArrowButton(String email) {
		emailAddress = email;
		cartPage.enterEmailAddressInInput(emailAddress);
	}

	@Then("Verify success message {string} is visible")
	public void verifySuccessMessageYouHaveBeenSuccessfullySubscribedIsVisible(String expectedMessage) {
		cartPage.verifySuccessMessage(expectedMessage);
	}

	@Given("Click view Product for any product on home page")
	public void clickViewProductForAnyProductOnHomePage() {
		cartPage.clickViewProductForAnyProductOnHomePage();
	}

	@Given("Click products button")
	public void clickProductsButton() {
		productPage.openProductPage();
	}

	@When("Hover over first product and click add to cart")
	public void hoverOverFirstProductAndClickAddToCart() {
		cartPage.hoverOverFirstProductAddToCart();
	}

	@And("Click Continue Shopping button")
	public void clickContinueShoppingButton() {
		cartPage.continueShopping();
	}

	@And("Hover over second product and click add to cart")
	public void hoverOverSecondProductAndClickAddToCart() {
		cartPage.hoverOverSecoundProductAddToCart();
	}

	@And("Click view Cart link")
	public void clickViewCartLink() {
		cartPage.viewCart();
	}

	@And("Verify both products are added to Cart")
	public void verifyBothProductsAreAddedToCart() {
		cartPage.verifyProduct();
	}

	@Then("Verify their prices, quantity and total price")
	public void verifyTheirPricesQuantityAndTotalPrice() {
		cartPage.verifyCartProductDetails("product-1", 500, 1);
		cartPage.verifyCartProductDetails("product-2", 400, 1);
	}

	@When("Verify product detail is opened")
	public void verifyProductDetailIsOpened() {
		cartPage.verifyProductDetail();
	}

	@And("Click on add to cart button")
	public void clickOnAddToCartButton() {
		cartPage.clickAddToCartButton();
	}

	@Then("Verify that product is displayed in cart page with exact quantity")
	public void verifyThatProductIsDisplayedInCartPageWithExactQuantity() {
		cartPage.verifyThatProductIsDisplayedInCart();
	}

	@And("Verify cart page is displayed")
	public void verifyCartPageIsDisplayed() {
		cartPage.verifyTheCartPage();
	}

	@And("Click X button corresponding to particular product")
	public void clickXButtonCorrespondingToParticularProduct() {
		cartPage.clickXButtonToParticularProduct();
	}

	@Then("Verify that product is removed from the cart")
	public void verifyThatProductIsRemovedFromTheCart() {
		cartPage.verifyThatProductIsRemovedFromCart();
	}

	@And("Increase quantity to {string}")
	public void increaseQuantityTo(String quantity) {
		int qty = Integer.parseInt(quantity);
		cartPage.increaseQuantity(String.valueOf(qty));
	}

}
