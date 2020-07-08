package com.volunteering.testing.utils;

import org.openqa.selenium.WebDriver;

import com.volunteering.testing.PageActions.LoginPage;
import com.volunteering.testing.PageActions.PublicLandingPage;

public class TestSessionInitiator {
	
	
	public 	PublicLandingPage landingpage;
	public LoginPage loginpage;	
	
	
	
	public void START_TEST_SESSION(WebDriver driver) {
		System.out.println("gaurav");
	 landingpage = new PublicLandingPage (driver);
	 loginpage = new LoginPage (driver);
	 System.out.println("malik");
	}

}
