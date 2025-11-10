package constant;

public class ProductPageConstant {
	public static String allProductText = "//h2[text()='All Products']";
	public static String viewProduct = "View Product";
	public static String productName = "//div[@class='product-information']/h2";
	public static String productCategory = "//div[@class='product-information']/p[1]";
	public static String productPrice = "//div[@class='product-information']//span[1]/span[1]";
	public static String productAvailability = "//p/b[text()='Availability:']/parent::p";
	public static String productCondition = "//p/b[text()='Condition:']/parent::p";
	public static String productBrand = "//p/b[text()='Brand:']/parent::p";
	public static String productSearchBar = "search_product";
	public static String productSearchIcon = "submit_search";
	public static String productFirstProductCart = "//a[@data-product-id='1' and normalize-space()='Add to cart']";
	public static String productCategorySection = "div.category-products";
	public static String productPagePrice = "div.category-products .panel-title a";
	public static String categoryXpath = "//div[@class='panel-group category-products']//a[normalize-space()='%s']";
	public static String subCategoryXPath = "//div[@id='Women']//a[normalize-space()='%s']";
	public static String categoryTitle = "//h2[@class='title text-center']";
	public static String categoryXpathDynamic = "//a[normalize-space()='%s' and @data-toggle='collapse']";
	public static String subcategoryXpathDynamic = "//div[@id='%s']//a[normalize-space()='%s']";
}
