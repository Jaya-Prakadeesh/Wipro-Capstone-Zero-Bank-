package com.zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.Reports;

import junit.framework.Assert;

public class AddNewPayeePage {
	private WebDriver driver;
	private WebDriverWait wait;
	ExtentTest extentTest;
	
	public AddNewPayeePage() {
		
	}
	public AddNewPayeePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		this.extentTest = test;
	}
	
	private By paybills = By.id("pay_bills_link");
	private By newPayee = By.cssSelector("a[href='#ui-tabs-2']");
	private By payeeName = By.id("np_new_payee_name");
	private By payeeAddr = By.id("np_new_payee_address");
	private By payeeAcct = By.id("np_new_payee_account");
	private By payeeDetail = By.id("np_new_payee_details");
	private By addBtn = By.id("add_new_payee");
	private By successMssg = By.xpath("//div[contains(text(),'The new payee was successfully created.')]");
	
	public void selectPaybills() {
		driver.findElement(paybills).click();
	}
	
	public void selectAddNewPayeeTab() {
		driver.findElement(newPayee).click();
	}
	
	public void enterPayeeDetails() {
		driver.findElement(payeeName).sendKeys("Vijay");
		driver.findElement(payeeAddr).sendKeys("No345, Church street, chennai");
		driver.findElement(payeeAcct).sendKeys("147258369");
		driver.findElement(payeeDetail).sendKeys("Personal account for Vijay");
	}
	
	public void clickAddBttn() {
		driver.findElement(addBtn).click();
	}
	
	public boolean validateSuccessfulPayment() {
		boolean actReasult = true;
		try {
			driver.findElement(successMssg);
			Reports.generateReport(driver, extentTest, Status.PASS, "Created Successfull");
		} catch (Exception e) {
			actReasult = false;
			Reports.generateReport(driver, extentTest, Status.FAIL, "Created Unsuccessful");
		}
		return actReasult;
	}
	
	public boolean alertMessage() {
		boolean actRst = true;
		String exMssg = "Please fill out this field.";
		try {
			WebElement payeename = driver.findElement(payeeName);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String alertMssg = (String) js.executeScript("return arguments[0].validationMessage;", payeename);
			System.out.println(alertMssg);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Assert.assertEquals(alertMssg, exMssg);
			Reports.generateReport(driver, extentTest, Status.PASS, exMssg);
		}catch(Exception e) {
			actRst = false;
			Reports.generateReport(driver, extentTest, Status.FAIL, exMssg);
		}
		return actRst;

}
}
