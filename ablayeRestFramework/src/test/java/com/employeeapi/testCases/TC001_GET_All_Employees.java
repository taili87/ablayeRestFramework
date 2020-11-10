package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_All_Employees extends TestBase {

	@BeforeClass
	public void getAllEmployee() throws InterruptedException {
		logger.info("Started TC001_GET_All_USERS");
		logger.info("Get the base URI");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users");

		Thread.sleep(3000);
	}

	@Test(priority = 1)
	void checkResponseBody() {
		logger.info("Checking Response Body");
		String responseBody = response.getBody().asString();
		logger.info("Response Body =" + responseBody);

		Assert.assertTrue(responseBody != null);
		logger.info("Validations passed");
	}

	@Test(priority = 2)
	void checkStatusCode() {
		logger.info("Checking Status code");
		int statusCode = response.getStatusCode();
		logger.info("Status code is = " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 3)
	void checkResponseTime() {
		logger.info("Checking Response Time");
		long responseTime = response.getTime();
		logger.info("Response time is = " + responseTime);

		if (responseTime > 2000) {
			logger.info("Response time is  ==" + responseTime);
			Assert.assertTrue(responseTime < 2000);
		}

	}

	@Test(priority = 4)
	void checkStatusLine() {
		logger.info("Checking status Line");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is = " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test(priority = 5)
	void checkContentType() {
		logger.info("Checking Content Type");
		String contentType = response.header("Content-Type");
		logger.info("Content type is = " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

	}

	@Test(priority = 6)
	void checkServerType() {
		logger.info("Checking server type");
		String serverType = response.header("Server");
		logger.info("Server Type is = " + serverType);
		Assert.assertEquals(serverType, "cloudflare");
	}

	/*
	 * @Test(priority = 7) void checkContentLength() {
	 * logger.info("checking content length"); String contentLength =
	 * response.header("Content-Length"); logger.info("Content Length is = " +
	 * contentLength);
	 * 
	 * if (Integer.parseInt(contentLength) > 100)
	 * logger.info("Content Length is less than 100");
	 * Assert.assertTrue(contentLength!=null);
	 * 
	 * }
	 */
	@Test(priority = 7)
	void checkCookies() {
		logger.info("Checking cookies");
		String cookies = response.header("PHPSESSID");
		logger.info("Cookies = " + cookies);
		Assert.assertFalse(cookies != null);

	}

}
