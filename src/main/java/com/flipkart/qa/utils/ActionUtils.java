package com.flipkart.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.flipkart.qa.properties.OR_HomePage;
import com.flipkart.qa.properties.OR_ProductPage;

public class ActionUtils {
	public static WebDriver driver;
	public ActionUtils(WebDriver driver)
	{
		ActionUtils.driver = driver;
	}
	
	/**
	 * This method returns true/false - based on whether element is displayed on page or not
	 * @param element
	 * @param webElementName
	 * @return boolean flag
	 */
	public static boolean isElementDisplayed(WebElement element, String webElementName) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			ReportUtils.log.info("SUCCESS: Element is displayed : " + webElementName);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
		}
		return flag;
	}
	/**
	 * @param driver
	 * @param ele
	 * @param itemName
	 * @return webElement
	 */
	public static WebElement getWebElement(String ele, String itemName )
	{
		//String eleXpath = ele + itemName + "']";
		//WebElement webElement = driver.findElement(By.xpath(eleXpath));
		String eleXpath = ele + itemName + "')]";
		WebElement webElement = null;
		try	{
			webElement = WaitUtils.waitForElementToBeClickable(By.xpath(eleXpath),driver, 30);
			ReportUtils.log.info("WebElement is Found : "+itemName);
		}
		catch (Exception e)	{
			ReportUtils.log.fail(e);
		}
		return webElement;
	}
	public boolean isElementDisplayed(String menuType, String menuItem)
	{
		boolean flag=false;
		try
		{
			switch (menuType) {
			case "mainMenuItem":
				flag = isElementDisplayed(getWebElement(OR_HomePage.mainMenuItem, menuItem), menuItem);
				break;
			case "subMenuItem":
				flag = isElementDisplayed(getWebElement(OR_HomePage.subMenuItem, menuItem), menuItem);
				break;
			case "subSectionItem":
				flag = isElementDisplayed(getWebElement(OR_HomePage.subSectionItem, menuItem), menuItem);
				break;
			case "sortByOption":
				flag = isElementDisplayed(getWebElement(OR_ProductPage.sortByOption, menuItem), menuItem);
				break;
			case "productSize":
				flag = isElementDisplayed(getWebElement(OR_ProductPage.productSize, menuItem), menuItem);
				break;
			case "product":
				flag = isElementDisplayed(getWebElement(OR_ProductPage.product, menuItem), menuItem);
				break;
			default:
				break;
			}
			
		}
		catch(Exception e)
		{
			ReportUtils.log.fail(e);
		}
		return flag;
	}
}
