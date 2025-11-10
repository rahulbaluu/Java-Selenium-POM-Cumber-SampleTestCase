@loginLogout
Feature: Login and Logout the existing account

  @validLogin
  Scenario Outline: Login and Logout with credentials
    Given Click Signup Login button
    When Enter the email "<email>" and password "<password>"
    And Click the login button
    And Verify "<expectedOutcome>"
    And Click the Logout button
    Then Verify it is navigated to the login page

  Examples:
      | email         | password | expectedOutcome              |
      | 178@gmail.com | 178      | Login as username is visible |

  @invalidLogin
  Scenario Outline: Login User with incorrect email and password
    Given Click Signup Login button
    When Enter the incorrect email "<email>" and password "<password>"
    And Click the login button
    Then Verify error "<expectedOutcome>" is visible

  Examples:
      | email         | password | expectedOutcome                      |
      | 178@gmail.com | 171      | Your email or password is incorrect! |






