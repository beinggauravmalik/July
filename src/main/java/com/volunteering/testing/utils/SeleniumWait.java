package com.volunteering.testing.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumWait {
	WebDriver driver;
	WebDriverWait wait;
	int timeout;
	
	public SeleniumWait (WebDriver driver, int timeout) {
		this.driver= driver;
		this.timeout= timeout;
		this.wait= new WebDriverWait (driver, timeout);
	}

	public void waitForPageToLoadCompletely() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
	}

	public void waitForElementToBeClickable( WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath()));
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
		}
	}
	public void waitForPreLoaderToDisappear() {
		resetImplicitTimeout(5);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("body div[class='preloader']")));
		} catch (TimeoutException e) {
			Assert.fail("Assertion Failed : Loader doesn't disappear within " + timeout + " second(s).");
		}
		resetImplicitTimeout(timeout);
	}
	

	public void waitForStableDom(int threshold /* ms */) {
		// Waits for the DOM to have been stable for `threshold` milliseconds.
		// Requires MutationObserver.
		// Registers observer to track last seen DOM mutation, function to see
		// if the DOM has been stable for a time.
		// https://developer.mozilla.org/en-US/docs/Web/API/MutationObserver
		// The string below is run inside an anonymous function by
		// executeScript().
		String observer = "var lastMod=new Date;" + "var observer=new MutationObserver(function(){lastMod=new Date});"
				+ "observer.observe(document.body,{childList:true,subtree:true});"
				+ "window.__DOM_STABLE_FOR=function(threshold){return ((new Date)-lastMod)>=threshold};";
		if ((Boolean) ((JavascriptExecutor) this.driver)
				.executeScript("return typeof(window.__DOM_STABLE_FOR) === 'undefined'")) {
			// Inject observer
			((JavascriptExecutor) this.driver).executeScript(observer);
		}
		wait.until(ExpectedConditions.jsReturnsValue(String.format("return window.__DOM_STABLE_FOR(%d)", threshold)));
	}
}
