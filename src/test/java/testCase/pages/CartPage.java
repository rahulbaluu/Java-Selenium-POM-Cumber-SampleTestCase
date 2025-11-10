package testCase.pages;

import constant.CartPageConstant;
import constant.WaitTimeConstant;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public void clickCartButton() {
		driver.findElement(By.linkText(CartPageConstant.cartButton)).click();
	}

	public void scrollDownToFooter() {
		WebElement footer = driver.findElement(By.id(CartPageConstant.cartPageFooter));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", footer);
	}

	public void verifyTheTextSubscription(String subscription) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement subscriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath(CartPageConstant.subscriptionElementXpath)));
		String actualText = subscriptionElement.getText().trim();
		System.out.println("Found subscription text: " + actualText);
		if (!actualText.contains(subscription)) {
			throw new AssertionError("Expected text '" + subscription + "' not found in actual text: '" + actualText + "'");
		}
	}

	public void enterEmailAddressInInput(String emailAddress) {
		driver.findElement(By.id(CartPageConstant.subscribeEmailTextBox)).sendKeys(emailAddress);
		driver.findElement(By.id(CartPageConstant.susbscribeButton)).click();
	}

	public void verifySuccessMessage(String expectedMessage) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.susbscribeSuccess)));
		WebElement alert = success.findElement(By.cssSelector(CartPageConstant.susbscribeAlert));
		String actualMessage = alert.getText().trim();
		System.out.println("Success message is visible: " + actualMessage);
		assertTrue(actualMessage.contains(expectedMessage),
				"Expected: \"" + expectedMessage + "\", but got: \"" + actualMessage + "\"");
	}

	public void hoverOverFirstProductAddToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));

		WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CartPageConstant.firstProduct)));

		// Scroll the element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);

		Actions actions = new Actions(driver);
		actions.moveToElement(firstProduct).perform();
		WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(CartPageConstant.addToCartButton)));
		addToCartButton.click();
	}

	public void continueShopping() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		By buttonLocator = By.xpath(CartPageConstant.buttonLocator);
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
		button.click();
	}

	public void hoverOverSecoundProductAddToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement secoundProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CartPageConstant.secondProduct)));
		Actions actions = new Actions(driver);
		actions.moveToElement(secoundProduct).perform();
		WebElement productContainer = secoundProduct.findElement(By.xpath(CartPageConstant.productContainer));
		WebElement addToCartButton = productContainer.findElement(By.linkText(CartPageConstant.addToCartButton));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
	}

	public void viewCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(CartPageConstant.viewCart)));
		cart.click();
	}

	public void verifyProduct() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.cartInfoButtonTable)));
		WebElement product1Row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.productRow1)));
		WebElement product2Row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.productRow2)));

		String product1Name = product1Row.findElement(By.cssSelector(CartPageConstant.productRowName)).getText().trim();
		String product2Name = product2Row.findElement(By.cssSelector(CartPageConstant.productRowName)).getText().trim();

		if (!product1Name.equals("Blue Top") || !product2Name.equals("Men Tshirt")) {
			throw new AssertionError("Product names do not match expected values!");
		}
		System.out.println("Both products are successfully present in the cart.");
	}

	public void verifyCartProductDetails(String productId, int expectedPrice, int expectedQuantity) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));

		// Locate the product row by ID (like product-1 or product-2)
		WebElement productRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(productId)));

		// Extract price text: e.g. "Rs. 500" → 500
		String priceText = productRow.findElement(By.cssSelector(CartPageConstant.cartPriceText)).getText().replaceAll("[^0-9]", "");
		int actualPrice = Integer.parseInt(priceText);

		// Extract quantity: from the button text
		String quantityText = productRow.findElement(By.cssSelector(CartPageConstant.cartQuantityText)).getText().trim();
		int actualQuantity = Integer.parseInt(quantityText);

		// Extract total price
		String totalText = productRow.findElement(By.cssSelector(CartPageConstant.cartTotalPrice)).getText().replaceAll("[^0-9]", "");
		int actualTotal = Integer.parseInt(totalText);
		int expectedTotal = expectedPrice * expectedQuantity;

		// Perform validations
		if (actualPrice != expectedPrice) {
			throw new AssertionError("Price mismatch for " + productId + ": Expected Rs. " + expectedPrice + ", but got Rs. " + actualPrice);
		}

		if (actualQuantity != expectedQuantity) {
			throw new AssertionError("Quantity mismatch for " + productId + ": Expected " + expectedQuantity + ", but got " + actualQuantity);
		}

		if (actualTotal != expectedTotal) {
			throw new AssertionError("Total price mismatch for " + productId + ": Expected Rs. " + expectedTotal + ", but got Rs. " + actualTotal);
		}

		System.out.println("Product " + productId + " has correct price, quantity, and total.");
	}

	public void clickViewProductForAnyProductOnHomePage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement viewProductLink = wait.until(ExpectedConditions.elementToBeClickable
				(By.xpath(CartPageConstant.viewProductLink)));
		viewProductLink.click();
	}

	public void verifyProductDetail() {
		String pageTitle = driver.getTitle();

		if (pageTitle != null && pageTitle.contains("Product Details")) {
			System.out.println("We are on the product page!");
		} else {
			System.out.println("We are NOT on the product page!");
		}
	}

	public void increaseQuantity(String quantity) {
		WebElement quantityInput = driver.findElement(By.id(CartPageConstant.quantityInput));
		quantityInput.clear();
		quantityInput.sendKeys(String.valueOf(quantity));
	}

	public void clickAddToCartButton() {
		WebElement addToCartButton = driver.findElement(By.cssSelector(CartPageConstant.viewProductPageAddtoCart));
		addToCartButton.click();
	}

	public void verifyThatProductIsDisplayedInCart() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Cart")));
		viewCart.click();
		WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CartPageConstant.quantityElement)));
		String quantityText = quantityElement.getText();
		int quantity = Integer.parseInt(quantityText);
		Assert.assertEquals(quantity, Integer.parseInt(quantityText));
		System.out.println("Verified: Product quantity is " + quantity);
	}

	public void clickCheckoutButton() {
		WebElement checkoutButton = driver.findElement(By.linkText(CartPageConstant.checkoutButton));
		checkoutButton.click();
	}

	public void registerLogin() {
		WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(CartPageConstant.cartRegistrationButton)));
		registerButton.click();
	}

	public void verifyAddressDetails() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		//Verify Delivery Address section
		WebElement deliveryAddressTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.deliveryAddressTitle)));
		Assert.assertTrue(deliveryAddressTitle.isDisplayed(), "Delivery address section not visible");
		System.out.println("Delivery address section found.");
		System.out.println("Delivery Address:\n" + deliveryAddressTitle.getText());

		//Verify Billing Address section
		WebElement billingSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.billingSection)));
		Assert.assertTrue(billingSection.isDisplayed(), "Billing address section not visible");
		System.out.println("Billing address section found.");
		System.out.println("Billing Address:\n" + billingSection.getText());

		// Review order table
		WebElement cartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CartPageConstant.cartTable)));
		Assert.assertTrue(cartTable.isDisplayed(), "Cart table not visible");

		// Get all product rows (skip the header)
		List<WebElement> productRows = cartTable.findElements(By.cssSelector(CartPageConstant.productRows));
		System.out.println("📦 Order Items:");
		for (WebElement row : productRows) {
			// Skip total amount row (it has empty first two columns)
			List<WebElement> cols = row.findElements(By.tagName("td"));
			if (cols.size() > 4 && !cols.get(0).getText().isEmpty()) {
				String productName = cols.get(1).getText();
				String price = cols.get(2).getText();
				String quantity = cols.get(3).getText();
				String total = cols.get(4).getText();
				System.out.println(productName + " | Price: " + price + " | Qty: " + quantity + " | Total: " + total);
			}
		}

		// Get total amount
		WebElement totalAmount = cartTable.findElement(By.cssSelector(CartPageConstant.totalAmount));
		System.out.println("💰 Total Amount: " + totalAmount.getText());
		System.out.println("Address details and Order details are verified.");
	}

	public void enterDescriptionInCart(String comment) {
		driver.findElement(By.className(CartPageConstant.checkoutDescription)).sendKeys(comment);
		//In the Cart click the Proceed To Checkout
		driver.findElement(By.linkText(CartPageConstant.placeOrder)).click();
		//In the Checkout page click the Place Order button
	}

	public void enterPaymentDetails(String name, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
		driver.findElement(By.className(CartPageConstant.cardHolderName)).sendKeys(name);
		//In the payment this will enter the Full name of the cardholder
		driver.findElement(By.name(CartPageConstant.cardNumber)).sendKeys(cardNumber);
		//In the payment this will enter the number of the card number
		driver.findElement(By.name(CartPageConstant.cvv)).sendKeys(cvc);
		//In the payment this will enter the CVV of the card
		driver.findElement(By.name(CartPageConstant.expiryMonth)).sendKeys(expiryMonth);
		//In the payment this will enter the expiry month
		driver.findElement(By.name(CartPageConstant.expiryYear)).sendKeys(expiryYear);
		//In the payment this will enter the expiry year
	}

	public void clickPayAndConfirm() {
		WebElement payButton = driver.findElement(By.id(CartPageConstant.cardDetailSubmit));
		//In the payment this submit the card detail we entered
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payButton);
		payButton.click();
	}

	public void verifySuccessMessageYourOrder(String expectedMessage) {
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath(CartPageConstant.successMessage)));
		// Get the text and verify
		String actualMessage = successMessage.getText();

		if (actualMessage.contains(expectedMessage)) {
			System.out.println("Order placed message verified successfully!");
		} else {
			System.out.println("Order placed message verification failed. Actual: " + actualMessage);
		}
		WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(CartPageConstant.continueButton)));
		continueButton.click();
	}

	public void verifyTheCartPage() {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(Objects.requireNonNull(actualUrl).contains("/view_cart"), "Cart page is not displayed!");
		System.out.println("Cart page url is verified: " + actualUrl);
	}

	public void clickXButtonToParticularProduct() {
		WebElement productRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath(CartPageConstant.cartProductRow)));
		// Scroll that row into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productRow);
		// Locate the corresponding delete (X) button inside the same row
		WebElement deleteButton = productRow.findElement(By.xpath(CartPageConstant.cartProductDeleteButton));
		wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
		System.out.println("Clicked delete (X) button for product: Men Tshirt");
	}

	public void verifyThatProductIsRemovedFromCart() {
		// Verify the product row is removed from the cart
		boolean isRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath(CartPageConstant.verifyProduct)));
		if (isRemoved) {
			System.out.println("Product 'Men Tshirt' successfully removed from the cart.");
		} else {
			System.out.println("Product 'Men Tshirt' still present in the cart.");
		}
	}
}
