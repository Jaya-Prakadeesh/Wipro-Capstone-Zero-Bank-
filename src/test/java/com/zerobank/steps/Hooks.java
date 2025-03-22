package com.zerobank.steps;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.zerobank.utils.Base;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import com.zerobank.utils.PropertyReader;

public class Hooks extends Base {
	static WebDriver driver = Base.driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeAll()
	public static void beforeAll() {
	    try {
	        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/zero-bank-capstone/reports/MyReport.html");
	        sparkReporter.config().setDocumentTitle("Selenium Project");
	        sparkReporter.config().setReportName("Regression Testing");
	        sparkReporter.config().setTheme(Theme.STANDARD);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("Tester Name", "John");
	        extent.setSystemInfo("Browser Name", "Chrome");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	@AfterAll()
	public static void afterAll() {
	    extent.flush();
	}
	@Before
	public void initSteps(Scenario scenario) {
		System.out.println("Launching the Browser....");
		test = extent.createTest(scenario.getName());
		getBrowser();
	}
	
	@After
	public void teardown() {
		System.out.println("Closing the Browser....");
		Base.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		Base.driver.quit();
		System.out.println("Browser is CLosed....");
	}
}
