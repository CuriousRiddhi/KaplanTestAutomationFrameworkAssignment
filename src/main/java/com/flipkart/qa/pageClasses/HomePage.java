package com.flipkart.qa.pageClasses;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import com.flipkart.qa.properties.OR_HomePage;
import com.flipkart.qa.utils.ActionUtils;
import com.flipkart.qa.utils.BaseUtils;
import com.flipkart.qa.utils.ReportUtils;

/**
 * HomePage Class - INitialize page factory for Home Page Elements
 * 
 * @author riddh
 *
 */
public class HomePage {
	public WebDriver driver;
	ITestResult result;

	/**
	 * This constructor Initialize Page Factory Elements
	 * 
	 * @param driver
	 * @throws IOException
	 */
	public HomePage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory - OR (Object Repository)
	@FindBy(xpath = OR_HomePage.closeIcon)
	public WebElement closeIcon;

	@FindBy(xpath = OR_HomePage.flipkartLogo)
	public WebElement flipkartLogo;

	/**
	 * This Method Return Home page title
	 * 
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public String getHomePageTitle() throws IOException {
		String titleHomePage = driver.getTitle();
		try {
			ReportUtils.log.info("Home Page Title - " + titleHomePage);
		} catch (Exception e) {
			ReportUtils.log.info("Failed to verify Home Page Title");
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
		return titleHomePage;
	}

	// Return Boolean - Home Page Logo is displayed or not
	/**
	 * This Method returns boolean, whether home page logo is displayed or not
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getHomePageLogo() throws Exception {
		return flipkartLogo.isDisplayed();
	}

	/**
	 * Method to Click on Login Popup - CLose Icon This Method click on close icon
	 * on login popup
	 * 
	 * @param driver
	 */
	public void closeIconOnLoginPopup() {
		try {
			if (closeIcon.isDisplayed())
				closeIcon.click();
		} catch (Exception e) {
			ReportUtils.log.fail("Failed to Close Login popup : " + e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to click on Primary menu Option from Top Menu/Primary Tab
	 * 
	 * @param driver
	 * @param Mainmenuitem
	 */
	public void clickOnMainMenuItem(String Mainmenuitem) {
		try {
			WebElement webElement = ActionUtils.getWebElement(OR_HomePage.mainMenuItem, Mainmenuitem);
			webElement.click();
			ReportUtils.log.info("Main Menu Item Clicked :: " + Mainmenuitem);
		} catch (Exception e) {
			ReportUtils.log.fail("Failed to click on Main Menu : " + e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Mouse Hover Action on Sub Section Item
	 * 
	 * @param driver
	 * @param subMenuItem
	 */
	public void mouseHoverSubsection(String subMenuItem) {
		try {
			WebElement webElement = ActionUtils.getWebElement(OR_HomePage.subMenuItem, subMenuItem);
			assertTrue(webElement.isDisplayed(), "SUCCESS: Flip Kart - Sub Menu Item displayed!");
			webElement.click();
			ReportUtils.log.info("Sub Menu Item Clicked :: " + subMenuItem);
		} catch (Exception e) {
			ReportUtils.log.fail("Failed to mouse hover sub section : " + e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to Click on Sub Item under sub section
	 * 
	 * @param driver
	 * @param subSectionItem
	 */
	public void clickonSubItem(String subSectionItem) {
		try {
			WebElement webElement = ActionUtils.getWebElement(OR_HomePage.subSectionItem, subSectionItem);
			webElement.click();
			ReportUtils.log.info("Sub Section Item Clicked :: " + subSectionItem);
		} catch (Exception e) {
			ReportUtils.log.fail("Fail to click on Sub Section Item : " + e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

}
