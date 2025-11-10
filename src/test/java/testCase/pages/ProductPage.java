package testCase.pages;

import constant.CartPageConstant;
import constant.ProductPageConstant;
import constant.WaitTimeConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class ProductPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
	public ProductPage(WebDriver driver) {
		super(driver);
	}

	public void openProductPage() {
		driver.findElement(By.cssSelector("i.material-icons.card_travel")).click();
	}

	public void verifyProductPageTitle() {
		if (Objects.equals(driver.getTitle(), "Automation Exercise - All Products")) {
			System.out.println("User is in product page" + driver.getTitle());
		} else {
			System.out.println("User not in product page");
		}
	}

	public boolean verifyAllProducts() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.allProductText)));
		return heading.getText().trim().equals("ALL PRODUCTS");
	}

	public void clickFirstViewProduct() {
		WebElement viewProductButton = driver.findElement(By.linkText(ProductPageConstant.viewProduct));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductButton);
		viewProductButton.click();
	}

	public void verifyProductDetailPageTitle() {
		System.out.println(driver.getTitle());
	}

	public boolean areProductDetailsVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));

		// Wait and verify visibility of all product detail elements
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.productName)));
		WebElement category = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.productCategory)));
		WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.productPrice)));
		WebElement availability = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.productAvailability)));
		WebElement condition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.productCondition)));
		WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.productBrand)));

		// Optional: Print values for debugging
		System.out.println("Name: " + name.getText());
		System.out.println("Category: " + category.getText());
		System.out.println("Price: " + price.getText());
		System.out.println("Availability: " + availability.getText());
		System.out.println("Condition: " + condition.getText());
		System.out.println("Brand: " + brand.getText());

		// Final verification
		return name.isDisplayed()
				&& category.isDisplayed()
				&& price.isDisplayed()
				&& availability.isDisplayed()
				&& condition.isDisplayed()
				&& brand.isDisplayed();
	}

	public void enterProductName(String productName) {
		driver.findElement(By.id(ProductPageConstant.productSearchBar)).sendKeys(productName);
		driver.findElement(By.id(ProductPageConstant.productSearchIcon)).click();
	}

	public boolean verifyEnterProductIsVisible(String productCategory) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[text()='" + productCategory + "']")));
		return product.isDisplayed();
	}

	public void productToCart() {
		WebElement addToCartButton = driver.findElement(By.xpath(ProductPageConstant.productFirstProductCart));
		addToCartButton.click();
	}

	public void clickCart(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(CartPageConstant.viewCart)));
		cart.click();
	}

	public void verifyThatCategoriesAreVisible(){
		WebElement categorySection = driver.findElement(By.cssSelector(ProductPageConstant.productCategorySection));
		Assert.assertTrue(categorySection.isDisplayed());
		// Locate all category names (Women, Men, Kids)
		List<WebElement> categories = driver.findElements(
				By.cssSelector(ProductPageConstant.productPagePrice));
		Assert.assertTrue(categories.size() > 0);
		System.out.println("Categories found:");
		for (WebElement cat : categories) {
			String catName = cat.getText().trim();
			System.out.println(" - " + catName);
			Assert.assertTrue(cat.isDisplayed());
		}
		System.out.println("Categories are visible on the left sidebar.");
	}

	public void clickOnWomenCategory(String categoryName){
			// Method to click category dynamically
			String categoryXPath = String.format(
					ProductPageConstant.categoryXpath, categoryName
			);

			WebElement categoryElement = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(categoryXPath))
			);

			categoryElement.click();
			System.out.println("Clicked on category: " + categoryName);
	}

	public void clickOnWomenProduct(String subCategoryName){
		String subCategoryXPath = String.format(ProductPageConstant.subCategoryXPath, subCategoryName);
		WebElement subCategoryElement = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(subCategoryXPath)));
		subCategoryElement.click();
		System.out.println(" Clicked on subcategory under Women: " + subCategoryName);
	}

	public boolean isCategoryPageDisplayed(String expectedCategoryName){
		// Locate the category title
		WebElement categoryTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductPageConstant.categoryTitle)));
		String actualCategoryTitle = categoryTitle.getText();
		System.out.println("Category title: " + actualCategoryTitle);
		System.out.println("Expected Category title: " + expectedCategoryName);
		return actualCategoryTitle.equalsIgnoreCase(expectedCategoryName);
	}

	public void onLeftSideBarClickOnAnySubCategory(String subCategoryName, String categoryName) {
		// Build XPath dynamically based on category and sub-category
		String categoryXPath = String.format(ProductPageConstant.categoryXpathDynamic, categoryName);
		WebElement categoryElement = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(categoryXPath)));
		categoryElement.click();

		// Only click if not already expanded
		WebElement categoryDiv = driver.findElement(By.id(categoryName));
		if (!categoryDiv.getAttribute("class").contains("in")) {
			categoryElement.click();
		}

		// Wait for sub-category to be clickable
		String subCategoryXPath = String.format(ProductPageConstant.subcategoryXpathDynamic, categoryName, subCategoryName);
		WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subCategoryXPath)));
		subCategoryElement.click();
		System.out.println("Clicked on subcategory '" + subCategoryName + "' under category '" + categoryName + "'");
	}
}
