package com.demowebshopproject.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {
	
	ExtentReports extent;
    ExtentSparkReporter sparkReporter ;
    ExtentTest test ;
    String reportName;
    WebDriver driver;
    BaseClass baseClassObj;
	Logger logger = LogManager.getLogger(ExtentReportManager.class);

	  public void onStart(ITestContext context) {

		  String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		  reportName= "extent-report-" + timestamp + ".html";
		  
		  sparkReporter = new ExtentSparkReporter(".\\Reports\\" + reportName);
		
		  extent =  new ExtentReports();
		  
	      extent.attachReporter(sparkReporter);	
	      test = extent.createTest(" Test", "Testing Demo Webshop");
	      
	      logger.info("Inside onStart method , test execution started " + context.getName());
	  }
	
	  public void onTestStart(ITestResult result) {

		  logger.info("Inside onTestStart method , test execution started " + result.getName());
	  }
	  
	  public void onTestSuccess(ITestResult result) {

		 test = extent.createTest(result.getName());
		 test.log(Status.PASS, result.getName() + " got successfully executed");
	  }
	  
	  public void onTestFailure(ITestResult result) {
		  
		  test = extent.createTest(result.getClass().getName());
		  test.log(Status.FAIL, result.getName() + " is failed");
		  test.log(Status.FAIL, result.getThrowable().getMessage() + " is the reason for failure");
		  String imagePath = null;
		  WebDriver driver = null;
		  try {
			
			  ITestContext context = result.getTestContext();
			  driver = (WebDriver)context.getAttribute("WebDriver");
			  baseClassObj = new BaseClass(driver);
        	  imagePath = baseClassObj.takeScreenshot(result.getName(),driver);
		  } catch (Exception e) {
			e.printStackTrace();
		} 
  		test.addScreenCaptureFromPath(imagePath,result.getMethod().getMethodName());

	  }
	 
	  public void onTestSkipped(ITestResult result) {

		  test = extent.createTest(result.getName());
		  test.log(Status.SKIP, result.getName() + " got skipped");

	  }
	  
	  public void onTestFailedWithTimeout(ITestResult result) {
		    
		  onTestFailure(result);
		  }
	  
	  public void onFinish(ITestContext context) {

		  extent.flush();
	  }
}
