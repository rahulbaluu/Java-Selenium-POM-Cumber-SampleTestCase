package testCase.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import testCase.pages.ProductPage;
import utilities.DriverSetup;

public class Productpagestepdef {
	private WebDriver driver;
	private ProductPage productPage;

	public Productpagestepdef(){
		this.driver = DriverSetup.getDriver();
		this.productPage = new ProductPage(driver);
	}

	@Given("Click on products button")
	public void clickOnProductsButton() {
		productPage.openProductPage();
	}

	@When("Verify user is navigated to All Products page successfully")
	public void verifyUserIsNavigatedToAllProductsPageSuccessfully() {
		productPage.verifyProductPageTitle();
	}

	@And("The products list is visible")
	public void theProductsListIsVisible() {
		Assert.assertTrue("All Products heading is not visible!", productPage.verifyAllProducts());
	}

	@And("Click on View Product of first product")
	public void clickOnViewProductOfFirstProduct() {
		productPage.clickFirstViewProduct();
	}

	@And("User is landed to product detail page")
	public void userIsLandedToProductDetailPage() {
		productPage.verifyProductDetailPageTitle();
	}

	@And("Enter product {string} in search input and click search button")
	public void enterProductNameInSearchInputAndClickSearchButton(String productName) {
		productPage.enterProductName(productName);
	}

	@And("Verify {string} is visible")
	public void verifySearchedProductIsVisible(String productCategory) {
		boolean isVisible = productPage.verifyEnterProductIsVisible(productCategory);
		if (isVisible) {
			System.out.println("Product '" + productCategory + "' is visible");
		}
		else {
			System.out.println("Product '" + productCategory + "' is not visible");
		}
	}

	@Then("Verify that detail detail is visible")
	public void verifyThatDetailDetailIsVisibleProductNameCategoryPriceAvailabilityConditionBrand() {
		productPage.areProductDetailsVisible();
		Assert.assertTrue("Not all product details are visible!", productPage.areProductDetailsVisible());
	}

	@When("Verify that categories are visible on left side bar")
	public void verifyThatCategoriesAreVisibleOnLeftSideBar() {
		productPage.verifyThatCategoriesAreVisible();
	}

	@And("Click on {string} category")
	public void clickOnWomenCategory(String categoryName) {
		productPage.clickOnWomenCategory(categoryName);
	}

	@And("Click on {string} category link under Women category")
	public void clickOnAnyCategoryLinkUnderWomenCategory(String subCategoryName) {
		productPage.clickOnWomenProduct(subCategoryName);
	}

	@And("Verify that category page is displayed and confirm text {string}")
	public void verifyThatCategoryPageIsDisplayedAndConfirmTextWOMENTOPSPRODUCTS(String expectedCategoryName) {
		boolean isDisplayed = productPage.isCategoryPageDisplayed(expectedCategoryName);
		Assert.assertTrue(isDisplayed);
	}

	@And("On left side bar, click on {string} sub-category link of {string} category")
	public void onLeftSideBarClickOnAnySubCategoryLinkOfMenCategory(String subCategoryName, String categoryName) {
		productPage.onLeftSideBarClickOnAnySubCategory(subCategoryName, categoryName );
	}

	@And("Verify that user is navigated to {string} category page")
	public void verifyThatUserIsNavigatedToThatCategoryPage(String expectedCategoryName) {
		boolean isDisplayed = productPage.isCategoryPageDisplayed(expectedCategoryName);
		Assert.assertTrue(isDisplayed);
	}

	@Then("Screenshot on every step")
	public void screenshotOnEveryStep() {
		productPage.closeProductDetailPage();
	}
}
