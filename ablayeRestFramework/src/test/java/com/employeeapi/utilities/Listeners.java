package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/myReports.html");
		htmlReporter.config().setDocumentTitle("Automation API REST ");
		htmlReporter.config().setReportName("Rest API Test");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "Cloub base");
		extent.setSystemInfo("Environment ", "QA");
		extent.setSystemInfo("Tester", "Ablaye Thiam");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case passed is " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th reports
		test.log(Status.FAIL, "Test case failed is " + result.getName()); // add name in extent reports
		test.log(Status.FAIL, "Test case failed is " + result.getThrowable()); //  add error/exception in extend reports
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // Create new entry in th reports
		test.log(Status.SKIP, "Test case passed is " + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
