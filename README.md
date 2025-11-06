# Java-Selenium-POM-Cumber-SampleTestCase
Feature: Login and Logout the existing account

Scenario: Login and Logout existing account Given Open the website home page and verify successfully When Click Signup Login button And Enter the correct email and password And Click the login button And Verify Login as username is visible And Click the Logout button And Verify it is navigated to the login page Then Every step is screenshotted

Scenario: Login User with incorrect email and password Given Open the website home page and verify successfully When Click Signup Login button And Enter the incorrect email and password And Click the login button And Verify error 'Your email or password is incorrect!' is visible Then Every step is screenshotted

Feature: Create, Verify and Delete the created account

Scenario: Registering the new user and delete the created user Given Open the website home page When Verify the home page is visible successfully And Click on Signup Login button And Verify New User Signup is visible And Enter name, email address And click Signup button And Verify ENTER ACCOUNT INFORMATION is visible And Enter the details in the Signup form and click create Account button And Verify ACCOUNT CREATED is visible and click Continue button And Verify Login in as username is visible And Click Delete Account button And Verify that Account Deleted is visible and click continue Then Take screenshot on every step

Scenario: Register User with existing email Given Open the website home page When Verify the home page is visible successfully And Click on Signup Login button And Verify New User Signup is visible And Enter name and already registered email address And click Signup button And Verify error 'Email Address already exist!' in signup is visible Then Take screenshot on every step

Scenario: Place Order Register while Checkout Given Open the website home page When Verify the home page is visible successfully And Add products to cart And Click cart button And Verify that cart page is displayed And Click Proceed To Checkout And Click RegisterLogin button And Fill all details in Signup and create account And Verify ACCOUNT CREATED is visible and click Continue button And Verify Login in as username is visible And Click Cart button from login page And Click Proceed To Checkout And Verify Address Details and Review Your Order And Enter description in comment text area and click Place Order And Enter payment details: Name on Card, Card Number, CVC, Expiration date And Click Pay and Confirm Order button And Verify success message 'Your order has been confirmed!' And Click Delete Account button And Verify that Account Deleted is visible and click continue Then Take screenshot on every step

Feature: Listing and Searching of Product

Scenario: Verify All Products and product detail page Given Open the home page When Verify home page is visible And Click on products button And Verify user is navigated to ALL PRODUCTS page successfully And The products list is visible And Click on View Product of first product And User is landed to product detail page And Verify that detail detail is visible: product name, category, price, availability, condition, brand Then Screenshot on every step

Scenario: Verify searched product is listed Given Open the home page When Verify home page is visible And Click on products button And Verify user is navigated to ALL PRODUCTS page successfully And Enter product 'Blue Top' in search input and click search button And Verify 'Blue Top' is visible Then Screenshot on every step

Scenario: View Category Products Given Open the home page When Verify home page is visible And Click on products button And Verify that categories are visible on left side bar And Click on 'Women' category And Click on 'Tops' category link under Women category And Verify that category page is displayed and confirm text 'WOMEN - Tops Products' And On left side bar, click on 'Jeans' sub-category link of 'Men' category And Verify that user is navigated to 'MEN - Jeans PRODUCTS' category page Then Screenshot on every step

Feature: Testing the functions in cart

Scenario: Verify Subscription in Cart page Given Open home page When Verify home page visible And Click Cart button And Scroll down to footer And Verify text 'SUBSCRIPTION' And Enter email address in input and click arrow button And Verify success message 'You have been successfully subscribed!' is visible Then Screenshot taken for all step

Scenario: Add Products in Cart Given Open home page When Verify home page visible And Click products button And Hover over first product and click 'Add to cart' And Click 'Continue Shopping' button And Hover over second product and click 'Add to cart' And Click 'View Cart' link And Verify both products are added to Cart And Verify their prices, quantity and total price Then Screenshot taken for all step

Scenario: Verify Product quantity in Cart Given Open home page When Verify home page visible And Click 'View Product' for any product on home page And Verify product detail is opened And Increase quantity to 4 And Click on 'Add to cart' button And Verify that product is displayed in cart page with exact quantity Then Screenshot taken for all step

Scenario: Remove Products From Cart Given Open home page When Verify home page visible And Click products button And Hover over first product and click 'Add to cart' And Click 'Continue Shopping' button And Hover over second product and click 'Add to cart' And Click 'View Cart' link And Verify cart page is displayed And Click X button corresponding to particular product And Verify that product is removed from the cart Then Screenshot taken for all step
