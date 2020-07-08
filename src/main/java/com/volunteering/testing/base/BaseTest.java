package com.volunteering.testing.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.volunteering.testing.PageActions.LoginPage;
import com.volunteering.testing.PageActions.PublicLandingPage;
import com.volunteering.testing.utils.TestSessionInitiator;
import com.volunteering.testing.utils.TimeUtil;

public class BaseTest {
	
	protected WebDriver driver;
		protected TestSessionInitiator test;
		
	@BeforeSuite
	public void browser() {
		initializebrowser();
	}
	
	
	public void initializebrowser () {
		FileInputStream fis = null;
		
		try {
			 fis = new FileInputStream ("C:\\Users\\gaurav.malik\\eclipse-workspace\\VolunteeringAutomation\\src\\main\\java\\com\\volunteering\\testing\\config\\config.properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		Properties prop = new Properties ();
		try {
			prop.load(fis);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String browsername = prop.getProperty("browser");
		if (browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\gaurav.malik\\Downloads\\chromedriver_win32 (7)\\chromedriver.exe");
        	 driver = new ChromeDriver();
		}
		
		else if (browsername.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\gaurav.malik\\Downloads\\chromedriver_win32 (7)\\chromedriver.exe");
        	 driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TimeUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TimeUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	@BeforeClass
	public void testInitiator () {
	 test = new TestSessionInitiator ();
		System.out.println(driver);
		test.START_TEST_SESSION(driver);
	}
	
	
	
	
	
	
	
	
	

}
