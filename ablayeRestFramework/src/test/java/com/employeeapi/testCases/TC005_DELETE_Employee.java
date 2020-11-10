package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DELETE_Employee extends TestBase {

	@BeforeClass
	public void deleteEmployeeData() throws InterruptedException {
		logger.info("Started TC002_GET_SINGLE_USERS");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/");

		// First get the JSONPATH object instance from the response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Capture ID
		String empID = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/" + empID); // PASS THE ID TO DELETE FROM DATABASE

		Thread.sleep(3000);
	}

	@Test(priority = 1)
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
		logger.info("Records ID Number 1 has been deleted, Successfully! ");
	}

	@Test(priority = 2)
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 3)
	public void checkStatusLine() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
}
