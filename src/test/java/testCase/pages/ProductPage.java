package testCase.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class ProductPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
		return heading.getText().trim().equals("ALL PRODUCTS");
	}

	public void clickFirstViewProduct() {
		WebElement viewProductButton = driver.findElement(By.linkText("View Product"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductButton);
		viewProductButton.click();
	}

	public void verifyProductDetailPageTitle() {
		System.out.println(driver.getTitle());
	}

	public boolean areProductDetailsVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait and verify visibility of all product detail elements
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']/h2")));
		WebElement category = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']/p[1]")));
		WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']//span[1]/span[1]")));
		WebElement availability = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p/b[text()='Availability:']/parent::p")));
		WebElement condition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p/b[text()='Condition:']/parent::p")));
		WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p/b[text()='Brand:']/parent::p")));

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
		driver.findElement(By.id("search_product")).sendKeys(productName);
		driver.findElement(By.id("submit_search")).click();
	}

	public boolean verifyEnterProductIsVisible(String productCategory) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[text()='" + productCategory + "']")));
		return product.isDisplayed();
	}

	public void productToCart() {
		WebElement addToCartButton = driver.findElement(By.xpath("//a[@data-product-id='1' and normalize-space()='Add to cart']"));
		addToCartButton.click();
	}

	public void clickCart(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Cart")));
		cart.click();
	}

	public void verifyThatCategoriesAreVisible(){
		WebElement categorySection = driver.findElement(By.cssSelector("div.category-products"));
		Assert.assertTrue(categorySection.isDisplayed());
		// Locate all category names (Women, Men, Kids)
		List<WebElement> categories = driver.findElements(
				By.cssSelector("div.category-products .panel-title a"));
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
					"//div[@class='panel-group category-products']//a[normalize-space()='%s']",
					categoryName
			);

			WebElement categoryElement = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(categoryXPath))
			);

			categoryElement.click();
			System.out.println("Clicked on category: " + categoryName);
	}

	public void clickOnWomenProduct(String subCategoryName){
		String subCategoryXPath = String.format("//div[@id='Women']//a[normalize-space()='%s']", subCategoryName);
		WebElement subCategoryElement = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(subCategoryXPath)));
		subCategoryElement.click();
		System.out.println(" Clicked on subcategory under Women: " + subCategoryName);
	}

	public boolean isCategoryPageDisplayed(String expectedCategoryName){
		// Locate the category title
		WebElement categoryTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='title text-center']")));
		String actualCategoryTitle = categoryTitle.getText();
		System.out.println("Category title: " + actualCategoryTitle);
		System.out.println("Expected Category title: " + expectedCategoryName);
		return actualCategoryTitle.equalsIgnoreCase(expectedCategoryName);
	}

	public void onLeftSideBarClickOnAnySubCategory(String subCategoryName, String categoryName) {
		// Build XPath dynamically based on category and sub-category
		String categoryXPath = String.format("//a[normalize-space()='%s' and @data-toggle='collapse']", categoryName);
		WebElement categoryElement = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(categoryXPath)));
		categoryElement.click();

		// Only click if not already expanded
		WebElement categoryDiv = driver.findElement(By.id(categoryName));
		if (!categoryDiv.getAttribute("class").contains("in")) {
			categoryElement.click();
		}

		// Wait for sub-category to be clickable
		String subCategoryXPath = String.format("//div[@id='%s']//a[normalize-space()='%s']", categoryName, subCategoryName);
		WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subCategoryXPath)));
		subCategoryElement.click();
		System.out.println("Clicked on subcategory '" + subCategoryName + "' under category '" + categoryName + "'");
	}

	public void closeProductDetailPage() {
		driver.close();
	}
}
