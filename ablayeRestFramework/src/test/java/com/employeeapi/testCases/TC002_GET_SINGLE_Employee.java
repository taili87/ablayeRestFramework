package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GET_SINGLE_Employee extends TestBase{
	
	@BeforeClass
	public void getEmployeeData() throws InterruptedException {
		logger.info("Started TC002_GET_SINGLE_USERS");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);

		Thread.sleep(3000);
	}

	@Test(priority = 1)
	void checkResponseBody() {
		logger.info("Checking Response Body");
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}

	@Test(priority = 2)
	void checkStatusCode() {
		logger.info("Checking Status code");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 3)
	void checkResponseTime() {
		logger.info("Checking Response Time");
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 2000);
	

	}

	@Test(priority = 4)
	void checkStatusLine() {
		logger.info("Checking status Line");
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test(priority = 5)
	void checkContentType() {
		logger.info("Checking Content Type");
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");

	}

	@Test(priority = 6)
	void checkServerType() {
		logger.info("Checking server type");
		String serverType = response.header("Server");
		logger.info("Server Type is = " + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	

	
	
	
}



