@NewPayeeModule
Feature: Adding a New Payee Module
	@SuccessfulAddNewPayee
	Scenario: Successfully adding a new Payee
    Given the user is loggedd in to page
    When the user navigates to pay bills
    And selects a add new payee
    Then user enters the details
    And click add button
    Then Successfully created message "The new payee Vijay was successfully created." is displayed
    
  @AddNewPayeeWithMissingDetails
	Scenario: Adding a new Payee wwith missing details
    Given the user is loggedd in to page
    When the user navigates to pay bills
    And selects a add new payee
    And click add button
    Then A alert message "Please fill out this field." is displayed  