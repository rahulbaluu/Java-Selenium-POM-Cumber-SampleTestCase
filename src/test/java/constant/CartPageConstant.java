package constant;

public class CartPageConstant {
	public static String cartButton = "Cart";
	public static String cartPageFooter = "footer";
	public static String subscriptionElementXpath = "//div[@class='single-widget']//h2[text()='Subscription']";
	public static String subscribeEmailTextBox = "susbscribe_email";
	public static String susbscribeButton = "subscribe";
	public static String susbscribeSuccess = "success-subscribe";
	public static String susbscribeAlert = ".alert-success.alert";
	public static String firstProduct = "a[data-product-id='1']";
	public static String secondProduct = "a[data-product-id='2']";
	public static String addToCartButton = "Add to cart";
	public static String buttonLocator = "//button[text()='" + "Continue Shopping" + "'] | //a[text()='" + "Continue Shopping" + "']";
	public static String productContainer = "./ancestor::div[contains(@class,'product')]";
	public static String viewCart = "View Cart";
	public static String cartInfoButtonTable = "cart_info_table";
	public static String productRow1 = "product-1";
	public static String productRow2 = "product-2";
	public static String productRowName = ".cart_description h4 a";
	public static String cartPriceText = ".cart_price p";
	public static String cartQuantityText = ".cart_quantity button";
	public static String cartTotalPrice = ".cart_total_price";
	public static String viewProductLink = "//a[contains(text(), '" + "View Product" + "')]";
	public static String quantityInput = "quantity";
	public static String viewProductPageAddtoCart = "button.btn.btn-default.cart";
	public static String quantityElement = "td.cart_quantity button.disabled";
	public static String checkoutButton = "Proceed To Checkout";
	public static String cartRegistrationButton = "Register / Login";
	public static String deliveryAddressTitle = "address_delivery";
	public static String billingSection = "address_invoice";
	public static String cartTable = "cart_info";
	public static String productRows = "tbody tr";
	public static String totalAmount = "tbody tr:last-child .cart_total_price";
	public static String checkoutDescription = "form-control";
	public static String placeOrder = "Place Order";
	public static String cardHolderName = "form-control";
	public static String cardNumber = "card_number";
	public static String cvv = "cvc";
	public static String expiryMonth = "expiry_month";
	public static String expiryYear = "expiry_year";
	public static String cardDetailSubmit = "submit";
	public static String successMessage = "//div[@class='col-sm-9 col-sm-offset-1']/p";
	public static String continueButton = "Continue";
	public static String cartProductRow = "//table[@id='cart_info_table']//tr[td[@class='cart_description']//a[contains(text(),'Men Tshirt')]]";
	public static String cartProductDeleteButton = ".//a[contains(@class,'cart_quantity_delete')]";
	public static String verifyProduct = "//table[@id='cart_info_table']//tr[td[@class='cart_description']//a[contains(text(),'Men Tshirt')]]";

}
