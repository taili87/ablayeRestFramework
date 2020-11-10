package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_POST_User extends TestBase {

	// RequestSpecification httpRequest;
	// Response response;

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("Started TC003 USING POST REQUESTS TO CREATE NEW RECORDS IN THE DATABASE");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		logger.info(requestParams.put("name", empName));
		logger.info(requestParams.put("salary", empSalary));
		logger.info(requestParams.put("age", empAge));

		// Add a header starting the request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());

		response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(3000);

	}

	@Test(priority = 1)
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
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

	@Test(priority = 4)
	public void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}

	@Test(priority = 5)
	public void checkServerType() {
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	@Test(priority = 6)
	public void checkContentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, null);
	}

}
