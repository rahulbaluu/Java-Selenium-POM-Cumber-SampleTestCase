Feature: Create, Verify and Delete the created account

  Scenario Outline: Registering the new user and delete the created user
    Given Click on Signup Login button
    When Verify New User Signup is visible
    And Enter name "<name>", email address "<email>"
    And click Signup button
    And Verify ENTER ACCOUNT INFORMATION is visible
    And Enter the details in the Signup form:
      | password   | firstName | lastName | company   | address1   | address2   | country   | state   | city   | zipcode | mobileNumber | date  | month   | year   |
      | <password> | <first>   | <last>   | <company> | <address1> | <address2> | <country> | <state> | <city> | <zip>   | <mobile>     | <date> | <month> | <year> |
    And Verify ACCOUNT CREATED is visible and click Continue button
    And Verify Login in as "<name>" is visible
    And Click Delete Account button
    Then Verify that Account Deleted is visible and click continue

    Examples:
      | name  | email             | password | first | last | company | address1 | address2  | country | state | city | zip    | mobile     | date | month | year |
      | hello | r1h3l39@gmail.com | hello123 | Hi    | How  | gmail   | Flat 1   | 2nd Floor | IND     | TN    | CBE  | 123456 | 1234567890 | 5    | 3     | 2001 |


  Scenario Outline: Register User with existing email
    Given Click on Signup Login button
    When Verify New User Signup is visible
    And Enter name "<Name>" and already registered email address "<Email>"
    And click Signup button
    Then Verify error 'Email Address already exist!' in signup is visible

  Examples:
    | Name | Email         |
    | 178  | 178@gmail.com |

  Scenario Outline: Place Order Register while Checkout
    Given Add products to cart
    When Click cart button
    And Verify that cart page is displayed
    And Click Proceed To Checkout
    And Click RegisterLogin button
    And Enter name "<name>", email address "<email>"
    And click Signup button
    And Enter the details in the Signup form:
      | password   | firstName | lastName | company   | address1   | address2   | country   | state   | city   | zipcode | mobileNumber | date   | month   | year   |
      | <password> | <first>   | <last>   | <company> | <address1> | <address2> | <country> | <state> | <city> | <zip>   | <mobile>     | <date> | <month> | <year> |
    And Verify ACCOUNT CREATED is visible and click Continue button
    And Verify Login in as "<name>" is visible
    And Click Cart button from login page
    And Click Proceed To Checkout
    And Verify Address Details and Review Your Order
    And Enter description in "<comment>" text area and click Place Order
    And Enter payment details:
      | Name on Card | Card Number | CVC   | Expiry Month | Expiry Year |
      | <cardName>   | <cardNo>    | <cvc> | <expMonth>   | <expYear>  |
    And Click Pay and Confirm Order button
    And Verify success message "<successMsg>"
    And Click Delete Account button
    Then Verify that Account Deleted is visible and click continue

  Examples:
    | name  | password | first | last | address1 | country | state | city | zip    | mobile | date | month | year | comment             | cardName | cardNo    | cvc | expMonth | expYear | successMsg                     | address2  | email             | company |
    | hello | hello123 | Hi    | How  | Flat 1   | IND     | TN    | CBE  | 123456 | 123456 | 5    | 2     | 1999 | Please deliver soon | Hello    | 123456789 | 123 | 12       | 2029    | Your order has been confirmed! | 2nd Floor | r1h3l39@gmail.com | gmail   |
