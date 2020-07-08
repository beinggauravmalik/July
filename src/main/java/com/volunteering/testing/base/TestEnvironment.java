package com.volunteering.testing.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.volunteering.testing.ExtentReports.ExtentReport;

public class TestEnvironment {
WebDriver driver;
ExtentReport extent;


@AfterMethod
public void tearDown () {
	driver.quit();
}

@BeforeTest
public void testStart () {
	
	
}















}
