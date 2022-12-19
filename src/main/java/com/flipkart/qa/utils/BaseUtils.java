package com.flipkart.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
 * Base Utility Class - contains common method getconfigValue from config file, GetScreenshot etc
 * @author riddh
 *
 */
public class BaseUtils {
	/**
	 * This method returns key value from config.properties file
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String key)  {
		
		Properties property = new Properties();		
		try	{
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/config.properties");
		property.load(file);	
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return property.getProperty(key);
	}
	/**
	 * This method takes screenshot, if any test fails and returns path for screenshot file
	 * @param driver
	 * @param pageName
	 * @return
	 */
	public static String getScreenShotPath(WebDriver driver, String pageName) {
		String path = System.getProperty("user.dir") + "/target/image_"+ pageName+ ".png";		
		try
		{
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(path));
		}
		catch(Exception e) {
			ReportUtils.log.fail(e);
		}
		return path;
	}

	/**
	 * Compare whether actual item contains expected value or not
	 * @param actual
	 * @param expected
	 * @return
	 */
	public static boolean isMatching(String actual, String expected)
	{
		boolean isMatched = false;		
		try
		{
			if( actual.contains(expected))
			{
				isMatched = true;
				ReportUtils.log.info("MATCHED: Actual Item : "+actual + "Expected : "+expected);
			}
		}
		catch(Exception e) {
			ReportUtils.log.fail(e);
		}
		return isMatched;
	}
}
