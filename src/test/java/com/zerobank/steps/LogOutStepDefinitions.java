package com.zerobank.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.AuthenticationPage;
import com.zerobank.pages.LogOutPage;
import com.zerobank.utils.Base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LogOutStepDefinitions {
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	LogOutPage logoutPage;
	AuthenticationPage loginPage;
	
	@Given("User click logout button")
	public void user_click_logout_button() {
	   logoutPage = new LogOutPage(driver,test);
	   loginPage = new AuthenticationPage(driver, test);
	   logoutPage.selectLogOut();
	}

	@Then ("User should not able to access previous page")
	public void user_should_not_able_to_access_previous_page(){
		logoutPage.verifying();
	}
	@Then("It should be on the login page")
	public void it_should_be_on_the_login_page() {
		try {
	        // Use the method from LoginPage to check if the user is on the login page
	        boolean isOnLoginPage = logoutPage.isItOnLogin();
	        Assert.assertTrue(isOnLoginPage, "User  is not on the login page.");
	        
	        System.out.println("User  is on the login page.");
	    } catch (Exception e) {
	        Assert.fail("An error occurred while checking if the user is on the login page: " + e.getMessage());
	    }
	}

}
