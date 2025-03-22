@AuthenticationModule
Feature: User Authentication 

#Background: login
#Given: User is on the login page

	@validCredentials
  Scenario: User Authentication with valid credentials
   Given User is on the login page
    When User enters "username" and "password" in respective field
    And User clicks the login button
    Then User is navigated to their account

   @invalidCredentials
   Scenario: User Authentication with invalid credentials
  	Given User is on the login page
    When User enters "user" and "pass" in respective field
    And User clicks the login button2
  	Then It should be on login page
    
   @emptyCredentials
   Scenario: User Authentication with n credentials
   	Given User is on the login page
    When User enters " " and "" in respective field
    And User clicks the login button2
    Then It should be on login page