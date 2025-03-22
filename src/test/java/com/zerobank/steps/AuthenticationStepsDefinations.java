package com.zerobank.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.zerobank.pages.AuthenticationPage;
import com.zerobank.utils.Base;
import com.zerobank.utils.Reports;

public class AuthenticationStepsDefinations extends Base {

	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	AuthenticationPage loginPage;

	@Given("User is on the login page")
	public void user_is_on_the_login_page() {

		try {
			loginPage = new AuthenticationPage(driver, test);
			boolean isLoggedIn = loginPage.logIn();
			Assert.assertTrue(isLoggedIn, "Failed to navigate to login page.");
		} catch (Exception e) {
			Assert.fail("An error occurred while trying to access the login page: " + e.getMessage());
		}
	}

	@When("User enters {string} and {string} in respective field")
	public void user_enters_and(String username1, String password1) {
		try {
			loginPage.credentials(username1, password1);
		} catch (Exception e) {
			Assert.fail("An error occurred while entering credentials: " + e.getMessage());
		}
	}

	@And("User clicks the login button")
	public void user_clicks_the_login_button() {
		try {
			boolean validateSignIn = loginPage.signIn();
			Assert.assertTrue(validateSignIn, "Login button click failed.");
		} catch (Exception e) {
			Assert.fail("An error occurred while clicking the login button: " + e.getMessage());
		}
	}
	@And("User clicks the login button2")
	public void user_clicks_the_login_button2() {
	    loginPage.wrongSignIn();
	}

	
	@Then("User is navigated to their account")
	public void user_is_navigated_to_their_account() {
		try {
			System.out.println("Checking account summary...");
			boolean validateAccount = loginPage.accountSummary();
			Assert.assertTrue(validateAccount, "Failed to navigate to account summary.");

		} catch (Exception e) {
			Assert.fail("An error occurred while navigating to the account summary: " + e.getMessage());
		}

	}

	@Then("It should be on login page")
	public void it_should_be_on_login_page() {
		try {
	        // Use the method from LoginPage to check if the user is on the login page
	        boolean isOnLoginPage = loginPage.isOnLoginPage();
	        Assert.assertTrue(isOnLoginPage, "User  is not on the login page.");
	        
	        System.out.println("User  is on the login page.");
	    } catch (Exception e) {
	        Assert.fail("An error occurred while checking if the user is on the login page: " + e.getMessage());
	    }
	}

}
