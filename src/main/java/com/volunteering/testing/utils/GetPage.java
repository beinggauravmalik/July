package com.volunteering.testing.utils;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class GetPage {
	WebDriver driver;
	String pagename;
	protected SeleniumWait wait;
	public GetPage(WebDriver driver, String pagename) {
		this.driver = driver;
		this.pagename = pagename;
		this.wait = new SeleniumWait(driver, 10);
	}

	public String[] readspecfile(String elementname) {

		String elementtype = null;
		String elementvalue = null;

		File file = new File(
				"C:\\Users\\gaurav.malik\\eclipse-workspace\\VolunteeringAutomation\\src\\main\\java\\com\\volunteering\\testing\\PageObjects\\"
						+ pagename + ".spec");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(fis));
			String line = reader.readLine();
			while (line != null) {
				// The split() method is used to split a string into an array of substrings, and
				// returns the new array.
				if (line.split("   ")[0].equals(elementname)) {

					System.out.println(line.split("   ")[1]);
					System.out.println(line.split("   ")[2]);
					elementtype = line.split("   ")[1];
					elementvalue = line.split("   ")[2];
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new String[] { elementtype, elementvalue };
	}

	public WebElement getLocator(String elementtype, String elementvalue) {
		WebElement ele = null;
		if (elementtype.equalsIgnoreCase("xpath")) {

			ele = driver.findElement(By.xpath(elementvalue));
		}

		else if (elementtype.equalsIgnoreCase("css")) {

			ele = driver.findElement(By.cssSelector(elementvalue));
		} else if (elementtype.equalsIgnoreCase("id")) {

			ele = driver.findElement(By.id(elementvalue));
		} else if (elementtype.equalsIgnoreCase("class")) {
			ele = driver.findElement(By.className(elementvalue));
		}
		return ele;
	}

	public WebElement element(String elementname) {

		String[] array;
		WebElement element;

		array = readspecfile(elementname);

		element = getLocator(array[0], array[1]);
		return element;
	}

	public void isElementDisplayed(String elementName) {
		//wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed();
		assertTrue(result, "Assertion Failed: element '" + elementName + "' is not displayed.");
		Reporter.log("Assertion Passed: element " + elementName + " is displayed.", true);
	}
	
	public void verifypagetitle(String text) {
		
		//Thread.sleep(3000);
		String actualTitle = driver.getTitle();
		String expectedTitle = text;
		Assert.assertEquals(actualTitle,expectedTitle, "page title not matched");
		Reporter.log("page title matched", true);
		
	}	

	public void verifyanytext(String elementNamee, String text) {
		//Thread.sleep(3000);
		//WebElement element = element(elementNamee);
		//String actualText = element.getText();
		//String expectedText = text;
		//Assert.assertEquals(expectedText,actualText, "element text not matched");
		Assert.assertEquals(element(elementNamee).getText(), text, "element text not matched" );
		Reporter.log("element text matched", true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}