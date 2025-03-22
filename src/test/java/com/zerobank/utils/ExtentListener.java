package com.zerobank.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListener implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public  void onStart(ITestContext context)
	{
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("Selenium Project");
		sparkReporter.config().setReportName("Regression Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "John");
		extent.setSystemInfo("Browser Name", "Chrome");
	  }
	
	public  void onTestStart(ITestResult result) {		
	  }
 
	  
	public  void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "TC Passed : " + result.getName());
	  }
 
	public  void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "TC Failed : " + result.getName() + " "+ result.getThrowable());
	  }
 
	public  void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "TC Skipped : " + result.getName());
	  }
	public  void onFinish(ITestContext context) {
		extent.flush();
		  }
 
	
}

