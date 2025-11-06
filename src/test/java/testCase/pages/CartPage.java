package testCase.pages;

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

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public void clickCartButton() {
		driver.findElement(By.linkText("Cart")).click();
	}

	public void scrollDownToFooter() {
		WebElement footer = driver.findElement(By.id("footer"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", footer);
	}

	public void verifyTheTextSubscription(String subscription) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement subscriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//div[@class='single-widget']//h2[text()='Subscription']")));
		String actualText = subscriptionElement.getText().trim();
		System.out.println("Found subscription text: " + actualText);
		if (!actualText.contains(subscription)) {
			throw new AssertionError("Expected text '" + subscription + "' not found in actual text: '" + actualText + "'");
		}
	}

	public void enterEmailAddressInInput(String emailAddress) {
		driver.findElement(By.id("susbscribe_email")).sendKeys(emailAddress);
		driver.findElement(By.id("subscribe")).click();
	}

	public void verifySuccessMessage(String expectedMessage) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe")));
		WebElement alert = success.findElement(By.cssSelector(".alert-success.alert"));
		String actualMessage = alert.getText().trim();
		System.out.println("Success message is visible: " + actualMessage);
		assertTrue(actualMessage.contains(expectedMessage),
				"Expected: \"" + expectedMessage + "\", but got: \"" + actualMessage + "\"");
	}

	public void hoverOverFirstProductAddToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-product-id='1']")));

		// Scroll the element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);

		Actions actions = new Actions(driver);
		actions.moveToElement(firstProduct).perform();
		WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));
		addToCartButton.click();
	}

	public void continueShopping() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By buttonLocator = By.xpath("//button[text()='" + "Continue Shopping" + "'] | //a[text()='" + "Continue Shopping" + "']");
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
		button.click();
	}

	public void hoverOverSecoundProductAddToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement secoundProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-product-id='2']")));
		Actions actions = new Actions(driver);
		actions.moveToElement(secoundProduct).perform();
		WebElement productContainer = secoundProduct.findElement(By.xpath("./ancestor::div[contains(@class,'product')]"));
		WebElement addToCartButton = productContainer.findElement(By.linkText("Add to cart"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
	}

	public void viewCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Cart")));
		cart.click();
	}

	public void verifyProduct() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_info_table")));
		WebElement product1Row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-1")));
		WebElement product2Row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-2")));

		String product1Name = product1Row.findElement(By.cssSelector(".cart_description h4 a")).getText().trim();
		String product2Name = product2Row.findElement(By.cssSelector(".cart_description h4 a")).getText().trim();

		if (!product1Name.equals("Blue Top") || !product2Name.equals("Men Tshirt")) {
			throw new AssertionError("Product names do not match expected values!");
		}
		System.out.println("Both products are successfully present in the cart.");
	}

	public void verifyCartProductDetails(String productId, int expectedPrice, int expectedQuantity) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the product row by ID (like product-1 or product-2)
		WebElement productRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(productId)));

		// Extract price text: e.g. "Rs. 500" → 500
		String priceText = productRow.findElement(By.cssSelector(".cart_price p")).getText().replaceAll("[^0-9]", "");
		int actualPrice = Integer.parseInt(priceText);

		// Extract quantity: from the button text
		String quantityText = productRow.findElement(By.cssSelector(".cart_quantity button")).getText().trim();
		int actualQuantity = Integer.parseInt(quantityText);

		// Extract total price
		String totalText = productRow.findElement(By.cssSelector(".cart_total_price")).getText().replaceAll("[^0-9]", "");
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement viewProductLink = wait.until(ExpectedConditions.elementToBeClickable
				(By.xpath("//a[contains(text(), '" + "View Product" + "')]")));
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
		WebElement quantityInput = driver.findElement(By.id("quantity"));
		quantityInput.clear();
		quantityInput.sendKeys(String.valueOf(quantity));
	}

	public void clickAddToCartButton() {
		WebElement addToCartButton = driver.findElement(By.cssSelector("button.btn.btn-default.cart"));
		addToCartButton.click();
	}

	public void verifyThatProductIsDisplayedInCart() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Cart")));
		viewCart.click();
		WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.cart_quantity button.disabled")));
		String quantityText = quantityElement.getText();
		int quantity = Integer.parseInt(quantityText);
		Assert.assertEquals(quantity, Integer.parseInt(quantityText));
		System.out.println("Verified: Product quantity is " + quantity);
	}

	public void clickCheckoutButton() {
		WebElement checkoutButton = driver.findElement(By.linkText("Proceed To Checkout"));
		checkoutButton.click();
	}

	public void registerLogin() {
		WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Register / Login")));
		registerButton.click();
	}

	public void verifyAddressDetails() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Verify Delivery Address section
		WebElement deliveryAddressTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("address_delivery")));
		Assert.assertTrue(deliveryAddressTitle.isDisplayed(), "Delivery address section not visible");
		System.out.println("Delivery address section found.");
		System.out.println("Delivery Address:\n" + deliveryAddressTitle.getText());

		//Verify Billing Address section
		WebElement billingSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_invoice")));
		Assert.assertTrue(billingSection.isDisplayed(), "Billing address section not visible");
		System.out.println("✅ Billing address section found.");
		System.out.println("💳 Billing Address:\n" + billingSection.getText());

		// Review order table
		WebElement cartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_info")));
		Assert.assertTrue(cartTable.isDisplayed(), "Cart table not visible");

		// Get all product rows (skip the header)
		List<WebElement> productRows = cartTable.findElements(By.cssSelector("tbody tr"));
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
		WebElement totalAmount = cartTable.findElement(By.cssSelector("tbody tr:last-child .cart_total_price"));
		System.out.println("💰 Total Amount: " + totalAmount.getText());
		System.out.println("Address details and Order details are verified.");
	}

	public void enterDescriptionInCart(String comment) {
		driver.findElement(By.className("form-control")).sendKeys(comment);
		//In the Cart click the Proceed To Checkout
		driver.findElement(By.linkText("Place Order")).click();
		//In the Checkout page click the Place Order button
	}

	public void enterPaymentDetails(String name, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
		driver.findElement(By.className("form-control")).sendKeys(name);
		//In the payment this will enter the Full name of the cardholder
		driver.findElement(By.name("card_number")).sendKeys(cardNumber);
		//In the payment this will enter the number of the card number
		driver.findElement(By.name("cvc")).sendKeys(cvc);
		//In the payment this will enter the CVV of the card
		driver.findElement(By.name("expiry_month")).sendKeys(expiryMonth);
		//In the payment this will enter the expiry month
		driver.findElement(By.name("expiry_year")).sendKeys(expiryYear);
		//In the payment this will enter the expiry year
	}

	public void clickPayAndConfirm() {
		WebElement payButton = driver.findElement(By.id("submit"));
		//In the payment this submit the card detail we entered
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payButton);
		payButton.click();
	}

	public void verifySuccessMessageYourOrder(String expectedMessage) {
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-sm-9 col-sm-offset-1']/p")));
		// Get the text and verify
		String actualMessage = successMessage.getText();

		if (actualMessage.contains(expectedMessage)) {
			System.out.println("Order placed message verified successfully!");
		} else {
			System.out.println("Order placed message verification failed. Actual: " + actualMessage);
		}
		WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Continue")));
		continueButton.click();
	}

	public void verifyTheCartPage() {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(Objects.requireNonNull(actualUrl).contains("/view_cart"), "Cart page is not displayed!");
		System.out.println("Cart page url is verified: " + actualUrl);
	}

	public void clickXButtonToParticularProduct() {
		WebElement productRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//table[@id='cart_info_table']//tr[td[@class='cart_description']//a[contains(text(),'Men Tshirt')]]")));
		// Scroll that row into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productRow);
		// Locate the corresponding delete (X) button inside the same row
		WebElement deleteButton = productRow.findElement(By.xpath(".//a[contains(@class,'cart_quantity_delete')]"));
		wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
		System.out.println("Clicked delete (X) button for product: Men Tshirt");
	}

	public void verifyThatProductIsRemovedFromCart() {
		// Verify the product row is removed from the cart
		boolean isRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//table[@id='cart_info_table']//tr[td[@class='cart_description']//a[contains(text(),'Men Tshirt')]]")));
		if (isRemoved) {
			System.out.println("Product 'Men Tshirt' successfully removed from the cart.");
		} else {
			System.out.println("Product 'Men Tshirt' still present in the cart.");
		}
	}
}
