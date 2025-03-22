package com.zerobank.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.AddNewPayeePage;
import com.zerobank.pages.BillPaymentsPage;
import com.zerobank.utils.Base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewPayeeStepDefinition {
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	AccountSummaryPage summaryPage;
	BillPaymentsPage payPage;
	AddNewPayeePage addPayeePage;
	
	@Given("the user is loggedd in to page")
	public void the_user_is_loggedd_in_to_page() {
		summaryPage = new AccountSummaryPage(driver, test);
		payPage = new BillPaymentsPage(driver, test);
		addPayeePage = new AddNewPayeePage(driver,test);
		summaryPage.signIn();
		summaryPage.back();
		payPage.selectOB();
	}

	@When("the user navigates to pay bills")
	public void the_user_navigates_to_pay_bills() {
	   addPayeePage.selectPaybills();
	}

	@When("selects a add new payee")
	public void selects_a_add_new_payee() {
	   addPayeePage.selectAddNewPayeeTab();
	}

	@Then("user enters the details")
	public void user_enters_the_details() {
	    addPayeePage.enterPayeeDetails();
	}

	@Then("click add button")
	public void click_add_button() {
		addPayeePage.clickAddBttn();
	}
	
	@Then("Successfully created message {string} is displayed")
	public void successfully_created_message_is_displayed(String string) {
		addPayeePage = new AddNewPayeePage(driver,test);
		boolean actResult = addPayeePage.validateSuccessfulPayment();
		Assert.assertFalse(actResult);
	}
	
	@Then("A alert message {string} is displayed")
	public void alert_message_is_displayed(String string) {
		addPayeePage = new AddNewPayeePage(driver,test);
		boolean actR = addPayeePage.alertMessage();
		Assert.assertTrue(actR);
		
	}

}
