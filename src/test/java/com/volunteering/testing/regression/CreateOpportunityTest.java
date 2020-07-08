package com.volunteering.testing.regression;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.volunteering.testing.PageActions.PublicLandingPage;
import com.volunteering.testing.base.BaseTest;
import com.volunteering.testing.utils.ScreenShot;

public class CreateOpportunityTest extends BaseTest {
	WebDriver driver;
	 ExtentReports extent;
	 ExtentTest extentTest;
	
	@Test
	
	public void Step01_signInAsVolunteerAndSelectOpportunityCreatorRole() {
		
		extentTest = extent.startTest("Step01_signInAsVolunteerAndSelectOpportunityCreatorRole");
		test.landingpage.verifyieeelogo();
		
		test.landingpage.verifyUserIsOnPublicLandingPage();
		test.landingpage.verifyieeelogo();
		test.landingpage.clicksigninlink();
		test.loginpage.enterusername();
		test.loginpage.enterpassword();
		test.loginpage.clicksigninbutton();
	}	
	
	
	  @BeforeTest
	public void setExtent () {
	extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
	extent.addSystemInfo("Host Name", "Gaurav Windows");
	extent.addSystemInfo("User Name", "Gaurav Malik");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport() {
		
		extent.flush();
		extent.close();
		
	}
	public static String getScreenshotforExtent (WebDriver driver, String screenshotname) {
		
		String dateName = new SimpleDateFormat ("yyyyMMddhhmm").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"./Screenshots/"+screenshotname+ dateName+ ".png";
		try {
			FileUtils.copyFile(src, new File (destination));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return destination;
		
	}
	
	@AfterMethod
	public void tearDown (ITestResult result) {
		
		
		if (result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+ result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+ result.getThrowable());
			
			String screenshotPath = getScreenshotforExtent(driver, result.getName());
			
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
		}
		else if (result.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS "+ result.getName());
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS "+ result.getThrowable());
			
			String screenshotPath = getScreenshotforExtent(driver, result.getName());
			
			extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(screenshotPath));
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS "+ result.getName());
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS "+ result.getThrowable());
			
			String screenshotPath = getScreenshotforExtent(driver, result.getName());
			
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		}
			extent.endTest(extentTest);
			driver.quit();
			
		
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}

		
	