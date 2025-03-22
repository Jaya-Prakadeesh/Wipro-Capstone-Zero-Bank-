package com.zerobank.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.Reports;

public class LogOutPage {
	private WebDriver driver;
	private WebDriverWait wait;
	ExtentTest extentTest;

	public LogOutPage() {

	}

	public LogOutPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		this.extentTest = test;
	}
	
	private By dropToggle = By.xpath("//a[normalize-space()='username']");
	private By logoutBttn = By.id("logout_link");
	private By username = By.xpath("//input[@id='user_login']");
	private By password = By.xpath("//input[@id='user_password']");
	private By button = By.xpath("//input[@type='submit']");
	
	public void selectLogOut() {
		try{
			driver.findElement(dropToggle).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBttn)).click();
	} catch (NoSuchElementException e) {
		Reports.generateReport(driver, extentTest, Status.FAIL, "Logout is unsuccessful.");
	}
	}
	
	public boolean isItOnLogin() {
		try {

			boolean UsernameVisible = driver.findElement(username).isDisplayed();
			boolean PasswordVisible = driver.findElement(password).isDisplayed();
			boolean LoginVisible = driver.findElement(button).isDisplayed();
			return UsernameVisible && PasswordVisible && LoginVisible;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public void verifying() {
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		try{
			if(driver.getCurrentUrl().contains("Log in")) {
		
			System.out.println("Session is expired");
		}
		else {
			System.out.println("User can still able to access previous page");
		}}
		 catch (NoSuchElementException e) {
				Reports.generateReport(driver, extentTest, Status.FAIL, "User can still able to access previous page.");
			}
	}

}
