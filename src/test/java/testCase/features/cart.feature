# Created by rahulbalashanmugam at 22/10/2025
Feature: Testing the functions in cart

  Scenario Outline: Verify Subscription in Cart page
   Given Click Cart button
    When Scroll down to footer
    And Verify text '<Subscription>'
    And Enter email "<email>" in input and click arrow button
    Then Verify success message 'You have been successfully subscribed!' is visible

   Examples:
     | Subscription | email     |
     | SUBSCRIPTION | 178@gmail |

  Scenario: Add Products in Cart
    Given Click products button
    When Hover over first product and click add to cart
    And Click Continue Shopping button
    And Hover over second product and click add to cart
    And Click view Cart link
    And Verify both products are added to Cart
    Then Verify their prices, quantity and total price


  Scenario Outline: Verify Product quantity in Cart
    Given Click view Product for any product on home page
    When Verify product detail is opened
    And Increase quantity to "<reqQuantity>"
    And Click on add to cart button
    Then Verify that product is displayed in cart page with exact quantity

  Examples:
    | reqQuantity |
    | 4           |

  Scenario: Remove Products From Cart
    Given Click products button
    When Hover over first product and click add to cart
    And Click Continue Shopping button
    And Hover over second product and click add to cart
    And Click view Cart link
    And Verify cart page is displayed
    And Click X button corresponding to particular product
    Then Verify that product is removed from the cart



