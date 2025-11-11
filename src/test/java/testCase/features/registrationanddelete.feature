@registrationAndDelete
Feature: Create, Verify and Delete the created account

  @newUser
  Scenario Outline: Registering the new user and delete the created user
    Given Click on Signup Login button
    When Verify New User Signup is visible
    And Enter name "<name>", email address "<email>"
    And click Signup button
    And Verify ENTER ACCOUNT INFORMATION is visible
    And Enter the details in the Signup form from given sheetname "<SheetName>" and rownumber <RowNumber>
    And Verify ACCOUNT CREATED is visible and click Continue button
    And Verify Login in as "<name>" is visible
    And Click Delete Account button
    Then Verify that Account Deleted is visible and click continue

    Examples:
      | name  | email             | SheetName          | RowNumber |
      | hello | r1h3l40@gmail.com | RegistrationDetail | 1         |
      | Hi    | r1h3l41@gmail.com | RegistrationDetail | 2         |

  @duplicateEmailRegistration
  Scenario Outline: Register User with existing email
    Given Click on Signup Login button
    When Verify New User Signup is visible
    And Enter name "<Name>" and already registered email address "<Email>"
    And click Signup button
    Then Verify error 'Email Address already exist!' in signup is visible

  Examples:
    | Name | Email         |
    | 178  | 178@gmail.com |

  @checkoutRegistration
  Scenario Outline: Place Order Register while Checkout
    Given Add products to cart
    When Click cart button
    And Verify that cart page is displayed
    And Click Proceed To Checkout
    And Click RegisterLogin button
    And Enter name "<name>", email address "<email>"
    And click Signup button
    And Enter the details in the Signup form from given sheetname "<SheetName>" and rownumber <RowNumber>
    And Verify ACCOUNT CREATED is visible and click Continue button
    And Verify Login in as "<name>" is visible
    And Click Cart button from login page
    And Click Proceed To Checkout
    And Verify Address Details and Review Your Order
    And Enter description in "<comment>" text area and click Place Order
    And Enter payment details from given Excel Sheet "<CardSheet>" and rowNumber <RowNum>
    And Click Pay and Confirm Order button
    And Verify success message "<successMsg>"
    And Click Delete Account button
    Then Verify that Account Deleted is visible and click continue
    Examples:
      | name  | email             | SheetName          | RowNumber | comment         | CardSheet   | RowNum | successMsg |
      | hello | r1h3l50@gmail.com | RegistrationDetail | 1         | Deliver it soon | CardDetails | 1      |  Congratulations! Your order has been confirmed!|
      | Hi    | r1h3l51@gmail.com | RegistrationDetail | 2         | deliver it soon | CardDetails | 2      |  Congratulations! Your order has been confirmed!|
