package com.zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.Reports;

import junit.framework.Assert;

public class BillPaymentsPage {
	private WebDriver driver;
	private WebDriverWait wait;
	ExtentTest extendTest;
	
	public BillPaymentsPage() {
		
	}
	public BillPaymentsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		this.extendTest = test;
	}
	
	private By onlinebaning = By.xpath("//strong[normalize-space()='Online Banking']");
	private By paybills = By.id("pay_bills_link");
	private By payee = By.xpath("//select[@id='sp_payee']//option[@value='bofa'][normalize-space()='Bank of America']");
	private By amountval = By.id("sp_amount");
	private By date = By.id("sp_date");
	private By datepicker = By.id("ui-datepicker-div");
	private By cmonth = By.className("ui-datepicker-month");
	private By cyear = By.className("ui-datepicker-year");
	private By selectDate = By.xpath("//td[@data-handler='selectDay']/a[text()='18']");
	private By payBtn = By.id("pay_saved_payees");
	private By success = By.xpath("//span[@title='$ 100 payed to payee bofa']");
	private By fDate = By.xpath("//td[@data-handler='selectDay']/a[text()='1']");
	private By next = By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']");
	
	
	public void selectOB() {
		driver.findElement(onlinebaning).click();
	}
	
	public void selectPb() {
		driver.findElement(paybills).click();
	}
	
	public void selectpy() {
		driver.findElement(payee).click();
	}
	
	public void enterAmtAndDate(String amount) {
		driver.findElement(amountval).sendKeys(amount);
		driver.findElement(date).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(datepicker));
		driver.findElement(selectDate).click();
	}
	
	public void enterAmtAndFDate(String amount) {
		driver.findElement(amountval).sendKeys(amount);
		driver.findElement(date).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(datepicker));
		String aMonth = driver.findElement(cmonth).getText();
		String aYear = driver.findElement(cyear).getText();
		while(!(aMonth.equals("April")&& aYear.equals("2025"))) {
			driver.findElement(next).click();
			aMonth = driver.findElement(cmonth).getText();
			aYear = driver.findElement(cyear).getText();
		}
		driver.findElement(fDate).click();
	}
	
	public void dateForEmptyField() {
		driver.findElement(date).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(datepicker));
		driver.findElement(selectDate).click();
	}
	public void clikPay() {
		driver.findElement(payBtn).click();
	}
	
	public boolean validateSuccessful() {
		boolean actReasult = true;
		try {
			driver.findElement(success);
			Reports.generateReport(driver, extendTest, Status.PASS, "Payment Successfull");
		} catch (Exception e) {
			actReasult = false;
			Reports.generateReport(driver, extendTest, Status.FAIL, "Payment Unsuccessful");
		}
		return actReasult;
	}
	
	public boolean alertMessage() {
		boolean actRst = true;
		String exMssg = "Please fill out this field.";
		try {
			WebElement amount = driver.findElement(amountval);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String alertMssg = (String) js.executeScript("return arguments[0].validationMessage;", amount);
			System.out.println(alertMssg);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Assert.assertEquals(alertMssg, exMssg);
			Reports.generateReport(driver, extendTest, Status.PASS, exMssg);
		}catch(Exception e) {
			actRst = false;
			Reports.generateReport(driver, extendTest, Status.FAIL, exMssg);
		}
		return actRst;
	}
	

}
