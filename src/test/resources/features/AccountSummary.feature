@AccountSummaryDetails
Feature: Account Summary
#http://zero.webappsecurity.com/

  Scenario: Verify account details and balances
    Given User is logged in
    When User navigates to the account summary page
    Then The account details should be displayed
    And The account balances should be correct