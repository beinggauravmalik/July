package com.volunteering.testing.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot { 
	
	public static void capturescreenshot (WebDriver driver, String screenshotname) {
	
	TakesScreenshot ts = (TakesScreenshot)driver;
	File src= ts.getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(src, new File ("./Screenshots/"+screenshotname+".png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Screenshot Taken");
	
	
	

}
}