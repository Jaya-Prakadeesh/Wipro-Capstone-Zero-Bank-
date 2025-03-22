@BillPayment
Feature: Bill Payment
  @SuccessfulBillPayment
  Scenario: Pay bills to payees
    Given the user is loggedd in
    When the user navigates to the bill payment page
    And selects a payee
    Then enters "100" as amount and select date
    And clicks on the pay button
    Then Success message "The payment was successfully submitted." is displayed
    
  @SchedulePayment  
  Scenario: Schedule bill payment for future date
    Given the user is loggedd in
    When the user navigates to the bill payment page
    And selects a payee
    Then enters "500" as amount and select future date
    And clicks on the pay button
    Then Success message "The payment was successfully submitted." is displayed
    
  @KeptAmountFieldEmpty  
  Scenario: Schedule bill payment for future date
    Given the user is loggedd in
    When the user navigates to the bill payment page
    And selects a payee
    Then select date
    And clicks on the pay button
    Then Alert message "Please fill out this field." is displayed
    
    