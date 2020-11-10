package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "1"; // Hard coded the values
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("EmployeesRestAPI"); // Added Logger
		PropertyConfigurator.configure("logs/log4j.properties"); // added logger
		logger.setLevel(Level.DEBUG);
	}
	
	@AfterClass
	void tearDown() {
		logger.info("Finished TC002");
	}
}
