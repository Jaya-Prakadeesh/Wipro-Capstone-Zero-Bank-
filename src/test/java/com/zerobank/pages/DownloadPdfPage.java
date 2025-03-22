package com.zerobank.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zerobank.utils.Reports;

public class DownloadPdfPage {
	private WebDriver driver;
	private WebDriverWait wait;
	ExtentTest extentTest;
	
	public DownloadPdfPage() {
		
	}
	public DownloadPdfPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		this.extentTest = test;
	}
	
	private By onlinestm = By.xpath("//a[normalize-space()='Online Statements']");
	private By accountType = By.xpath("//select[@id='os_accountId']//option[@value='5'][normalize-space()='Credit Card']");
	private By dateRange =By.linkText("2010");
	private By clickDownload = By.xpath("//a[contains(text(),'Statement 04/09/10(57K)')]");
	
	public void selectOnlineStatement() {
		driver.findElement(onlinestm).click();
	}
	
	public void selectAccountAndDateRange() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(accountType));
		driver.findElement(accountType).click();
		WebElement datelink = wait.until(ExpectedConditions.visibilityOfElementLocated(dateRange));
		datelink.click();
	}
	
	public void clickedDownload() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Statement 04/09/10(57K)')]")));
	}
	
	public void clickedKeep() {
	    try {
	        Robot rbt = new Robot();

	       
	        for (int i = 0; i < 10; i++) { 
	            rbt.keyPress(KeyEvent.VK_TAB);
	            rbt.keyRelease(KeyEvent.VK_TAB);
	        }

	        
	        rbt.keyPress(KeyEvent.VK_ENTER);
	        rbt.keyRelease(KeyEvent.VK_ENTER);

	        System.out.println("'Keep' button clicked successfully!");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public boolean verifyingPDF() {
	    String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
	    File dir = new File(downloadPath);

	    try {
	        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".pdf"));
	        

	        assert files != null && files.length > 0; 
	        
	        Reports.generateReport(driver, extentTest, Status.PASS, "PDF file found.");
	        System.out.println("PDF file found.");
	        return true;
	        
	    } catch (Exception e) {
	        Reports.generateReport(driver, extentTest, Status.FAIL, "No PDF file found.");
	        System.out.println("No PDF file found.");
	        return false;
	    }
	}
	

}
