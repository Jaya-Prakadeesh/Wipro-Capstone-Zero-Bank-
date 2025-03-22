@LogoutFunctionality
Feature: Logging out
	@SuccessfulLogout
  Scenario: Loggig in and Logging out
    Given User is logged in
    And User click logout button
    Then It should be on the login page
    
   @VerifySessionExpiryLogout
   Scenario: Verifying Session Expiry
    Given User is logged in
    And User click logout button
    Then User should not able to access previous page
    
    