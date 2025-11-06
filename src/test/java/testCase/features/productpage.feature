# Created by rahulbalashanmugam at 21/10/2025
Feature: Listing and Searching of Product

  Scenario: Verify All Products and product detail page
    Given Click on products button
    When Verify user is navigated to All Products page successfully
    And The products list is visible
    And Click on View Product of first product
    And User is landed to product detail page
    Then Verify that detail detail is visible

  Scenario Outline: Verify searched product is listed
    Given Click on products button
    When Verify user is navigated to All Products page successfully
    And Enter product "<productName>" in search input and click search button
    Then Verify "<productName>" is visible

  Examples:
    | productName                 |
    | Blue Top                    |
    | Winter Top                  |
    | Sleeves Printed Top - White |

  Scenario Outline: Verify navigation to different category and sub-category pages
    Given Click on products button
    When Verify that categories are visible on left side bar
    And Click on '<mainCategory>' category
    And Click on '<subCategory>' category link under Women category
    And Verify that category page is displayed and confirm text '<expectedCategoryText>'
    And On left side bar, click on '<subCategory2>' sub-category link of '<mainCategory2>' category
    Then Verify that user is navigated to '<expectedCategoryText2>' category page

  Examples:
    | mainCategory | subCategory | expectedCategoryText   | subCategory2 | mainCategory2 | expectedCategoryText2 |
    | Women        | Tops        | WOMEN -  Tops Products | Jeans        | Men           | MEN -  Jeans PRODUCTS  |






