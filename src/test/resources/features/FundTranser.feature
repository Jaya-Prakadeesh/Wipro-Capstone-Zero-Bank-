@FundTransferModule
Feature: Fund Transfer
#http://zero.webappsecurity.com/
	@SuccessTransfer
  Scenario: Transfer funds between accounts
    Given the user is logged in 
    When the user navigates to the fund transfer page
    And selects the sender and the reciever
    And enters the "650" and click submit
    Then a success message should be displayed
    
    
   @InsufficientBalance
   Scenario: Transfer funds between accounts with more than present balance
   Given the user is logged in 
    When the user navigates to the fund transfer page
    And selects the sender and the reciever
   And enters the "8000" and click submit
   Then it should show "Insufficient funds" message
   
   @NegativeBalance
   Scenario: Transfer funds between accounts with negative amount
   Given the user is logged in 
    When the user navigates to the fund transfer page
    And selects the sender and the reciever
   And enters the "-800" and click submit
   Then it should show "Invalid amount entered" message
   