package com.zerobank.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.Reports;

public class FundTranseferPage {
	private WebDriver driver;
	private WebDriverWait wait;
	ExtentTest extentTest;

	public FundTranseferPage() {
		// TODO Auto-generated constructor stub
	}

	public FundTranseferPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		this.extentTest = test;
	}

	private By transferFundSection = By.xpath("//a[text()='Transfer Funds']");
	private By headerTag = By.xpath("//h2[text()='Transfer Money & Make Payments']");
	private By sender = By.id("tf_fromAccountId");
	private By reciever = By.xpath("//select[@id='tf_toAccountId']//option[@value='6'][normalize-space()='Brokerage(Avail. balance = $ 197)']");
	private By amountField = By.id("tf_amount");
	private By description = By.id("tf_description");
	private By continueButton = By.xpath("//button[@id='btn_submit']");
	private By submitButton = By.xpath("//button[@id='btn_submit']");
	private By successfullMessage = By.xpath("//div/div/div[@class='alert alert-success']");

	public boolean validateTransferFundSection() {
		driver.findElement(transferFundSection).click();

		boolean actResult = true;
		try {
			WebElement currentTag = driver.findElement(headerTag);
			currentTag.isDisplayed();
			Reports.generateReport(driver, extentTest, Status.PASS, "Navigated to Transfer Fund Section");
		} catch (Exception e) {
			actResult = false;
			Reports.generateReport(driver, extentTest, Status.FAIL,
					"Not navigated to Transfer Fund Section" + e.getMessage());
		}
		return actResult;
	}

	public void selectingAccountType() {
		driver.findElement(sender).click();
		driver.findElement(reciever).click();
	}

	public boolean amountGiven(String amount) {
		boolean actResult = true;
		try {
			driver.findElement(amountField).sendKeys(amount);
			driver.findElement(description).sendKeys("You successfully submitted for your transaction");
			driver.findElement(continueButton).click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(submitButton));
			driver.findElement(submitButton).click();
			Reports.generateReport(driver, extentTest, Status.PASS, "Fund transfer successful");

		} catch (Exception e) {
		actResult = false;
		Reports.generateReport(driver, extentTest, Status.FAIL, amount);
	}
		System.out.println(actResult);
		return actResult;
		
	}

	public boolean validatePayment() {
		boolean actReasult = true;
		try {
			driver.findElement(successfullMessage);
			System.out.println(successfullMessage);
			Reports.generateReport(driver, extentTest, Status.PASS, "Payment Successfull");
		} catch (Exception e) {
			actReasult = false;
			Reports.generateReport(driver, extentTest, Status.FAIL, "Payment Unsuccessful");
		}
		return actReasult;
	}
}
