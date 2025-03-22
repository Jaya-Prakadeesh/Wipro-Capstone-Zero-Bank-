package com.zerobank.steps;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.zerobank.pages.DownloadPdfPage;
import com.zerobank.utils.Base;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DownlaodPdfStepDefinition {
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	DownloadPdfPage downloadPage;
	
	@When("selects a online statements")
	public void selects_a_online_statements() {
		downloadPage = new DownloadPdfPage(driver,test);
	    downloadPage.selectOnlineStatement(); 
	}

	@Then("select account and date range")
	public void select_account_and_date_range() {
	    downloadPage.selectAccountAndDateRange();
	}

	@Then("clicked the download option and verifying")
	public void clicked_the_download_option() {
	   downloadPage.clickedDownload();
	   downloadPage.clickedKeep();
	   downloadPage.verifyingPDF();
	}

}
