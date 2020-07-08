package com.volunteering.testing.PageActions;

import org.openqa.selenium.WebDriver;


import com.volunteering.testing.utils.GetPage;
import com.volunteering.testing.utils.YamlReader;
import static com.volunteering.testing.utils.YamlReader.readvaluefromYamlfile;

public class LoginPage extends GetPage{

	WebDriver driver;
	 public LoginPage (WebDriver driver) {
		 super (driver, "LoginPage");
		 this.driver = driver;
	 }
	
	 
	public void enterusername () {
		wait.hardWait(5);
		wait.waitForPageToLoadCompletely();
		
		element ("username").sendKeys("02363505@domain.com");
	}
	


	public void enterpassword () {
		element ("password").sendKeys("qaIEEE");
		
	}
	
	public void clicksigninbutton () {
		
element("signInButton").click();
	}
	
	
	
	
	
	
	
	
	
	

}
