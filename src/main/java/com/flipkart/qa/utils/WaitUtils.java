package com.flipkart.qa.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Common Utility for Wait Methods & ScrolltoElement methods
 * @author riddh
 *
 */
public class WaitUtils {
	/**
	 * Static wait for 2 seconds
	 * @throws InterruptedException
	 */
	public static void wait2Second() throws InterruptedException {
		Thread.sleep(2000);
	}

	/**
	 * Method to Wait until element is clickable for given timeout
	 * @param xpath
	 * @param driver
	 * @param timeout
	 * @return
	 */
	public static WebElement waitForElementToBeClickable(By xpath, WebDriver driver, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(xpath));
	}
	
	/**
	 * explicity wait until element is clicable
	 * @param Element
	 * @param driver
	 */
	public static void explicit_wait_elementToBeClickable(WebElement Element, WebDriver driver) {
		try	{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(Element));
		}
		catch(Exception e)
		{
			ReportUtils.log.fail(e);
		}
	}
	/**
	 * Method to wait for element until it is visible
	 * @param xpath
	 * @param driver
	 * @param timeOut
	 */
	public static void waitForElementToBeVisibile(By xpath, WebDriver driver, int timeOut)
	{
		try	{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
		}
		catch(Exception e)
		{
			ReportUtils.log.fail(e);
		}
	}
	/**
	 * @param ele
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void ScrollToElement(WebElement ele, WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		// Wait.wait2Second();
	}
	/**
	 * @param ele
	 * @param driver
	 */
	public static void ScrolltoElementusingActionclass(WebElement ele, WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(ele);
		actions.perform();
	}
}
