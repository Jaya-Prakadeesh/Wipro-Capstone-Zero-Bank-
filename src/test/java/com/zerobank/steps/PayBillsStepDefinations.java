package com.zerobank.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.BillPaymentsPage;
import com.zerobank.pages.FundTranseferPage;
import com.zerobank.utils.Base;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PayBillsStepDefinations {
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	AccountSummaryPage summaryPage;
	BillPaymentsPage payPage;
	
	@Given("the user is loggedd in")
	public void the_user_is_loggedd_in() {
		summaryPage = new AccountSummaryPage(driver, test);
		payPage = new BillPaymentsPage(driver, test);
		summaryPage.signIn();
		summaryPage.back();
		payPage.selectOB();
		
	}

	@When("the user navigates to the bill payment page")
	public void the_user_navigates_to_the_bill_payment_page() {
	  payPage.selectPb();
	}

	@And("selects a payee")
	public void selects_a_payee() {
	    payPage.selectpy();
	}

	@Then("enters {string} as amount and select date")
	public void enters_as_amount_and_select_date(String string) {
	  payPage.enterAmtAndDate(string);
	}
	
	@Then("enters {string} as amount and select future date")
	public void enters_as_amount_and_select_future_date(String string) {
	payPage.enterAmtAndFDate(string);
	}
	
	@Then("select date")
	public void select_date() {
		payPage.dateForEmptyField();
	}

	@And("clicks on the pay button")
	public void clicks_on_the_pay_button() {
	    payPage.clikPay();
	}

	@Then("Success message {string} is displayed")
	public void success_message_is_displayed(String string) {
		payPage = new BillPaymentsPage(driver, test);
		boolean actResult = payPage.validateSuccessful();
		Assert.assertFalse(actResult);

	}
	
	@Then("Alert message {string} is displayed")
	public void alert_message_is_displayed(String string) {
		payPage = new BillPaymentsPage(driver, test);
		boolean actR = payPage.alertMessage();
		Assert.assertTrue(actR);
		
	}
}
