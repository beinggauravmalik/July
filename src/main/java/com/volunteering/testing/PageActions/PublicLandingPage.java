package com.volunteering.testing.PageActions;

import org.openqa.selenium.WebDriver;

import com.volunteering.testing.utils.GetPage;

public class PublicLandingPage extends GetPage {
	WebDriver driver;

	public PublicLandingPage(WebDriver driver) {
		super(driver, "PublicLandingPage");
		this.driver = driver;
	}

	public void verifyUserIsOnPublicLandingPage() {
		
		element ("div_publicLandingPage").isDisplayed();
	}
	
	public void verifyieeelogo() {

		element("ieeeLogo").isDisplayed();
	}

	public void clicksigninlink() {

		element("signInlink").click();
	}

	
	
}