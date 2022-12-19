/**
 * @author riddhi
 * Product page
 *
 */
package com.flipkart.qa.pageClasses;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import com.flipkart.qa.properties.OR_ProductPage;
import com.flipkart.qa.utils.ActionUtils;
import com.flipkart.qa.utils.BaseUtils;
import com.flipkart.qa.utils.ReportUtils;
import com.flipkart.qa.utils.WaitUtils;

/**
 * // * Initialize All Elements using Page Factory for Product Page
 * 
 * @author riddh
 *
 */
public class ProductPage {
	public WebDriver driver;
	ITestResult result;

	public ProductPage(WebDriver driver) throws IOException {
		// initialize Page factory for this class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory - OR (Object Repository)
	@FindBy(xpath = OR_ProductPage.productName)
	public WebElement productName;

	@FindBy(xpath = OR_ProductPage.productPrice)
	public WebElement productPrice;

	@FindBy(xpath = OR_ProductPage.productSize)
	public WebElement productSize;

	@FindBy(xpath = OR_ProductPage.addToCartButton)
	public WebElement addToCartButton;

	@FindBy(xpath = OR_ProductPage.productNameOnCart)
	public WebElement productNameOnCart;

	@FindBy(xpath = OR_ProductPage.productPriceOnCart)
	public WebElement productPriceOnCart;

	@FindBy(xpath = OR_ProductPage.removeBtn)
	public WebElement removeBtn;

	@FindBy(xpath = OR_ProductPage.removeItemPopup)
	public WebElement removeItemPopup;

	@FindBy(xpath = OR_ProductPage.removeButtomonPopup)
	public WebElement removeButtomonPopup;

	/**
	 * Method to click on any option on - Sort By Section
	 * 
	 * @param driver
	 * @param sortByOption
	 * @throws IOException
	 */
	public void clickOnsortByOption(String sortByOption) throws IOException {
		try {
			String sortByOptionXpath = OR_ProductPage.sortByOption + sortByOption + "')]";
			WebElement webElement = driver.findElement(By.xpath(sortByOptionXpath));
			assertTrue(webElement.isDisplayed(), "SUCCESS: Flip Kart - Sort By Option is displayed! " + sortByOption);
			WaitUtils.waitForElementToBeClickable(By.xpath(sortByOptionXpath), driver, 30);
			webElement.click();
			ReportUtils.log.info("Sort By Option Selected : " + sortByOption);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to click on product
	 * 
	 * @param driver
	 * @param product
	 */
	public void clickOnProduct(String product) {
		try {
			String productXpath = OR_ProductPage.product + product + "')]";
			WebElement webElement = driver.findElement(By.xpath(productXpath));
			assertTrue(webElement.isDisplayed(), "SUCCESS: Flip Kart - Product is Displayed! " + product);
			WaitUtils.waitForElementToBeClickable(By.xpath(productXpath), driver, 30);
			webElement.click();
			ReportUtils.log.info("Product Selected : " + product);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * * Method to Get Product Name before adding product to cart
	 * 
	 * @param driver
	 * @return
	 */
	public String getProductName() {
		String prdName = "";
		try {
			WaitUtils.waitForElementToBeVisibile(By.xpath(OR_ProductPage.productName), driver, 20);
			prdName = productName.getText();
			ReportUtils.log.info("Get Product Name From Product : " + prdName);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
		return prdName;
	}

	/**
	 * Method to Get Product Price before adding product to cart
	 * 
	 * @param driver
	 * @return
	 */
	public String getProductPrice() {
		String prdPrice = "";
		try {
			WaitUtils.waitForElementToBeVisibile(By.xpath(OR_ProductPage.productPrice), driver, 20);
			prdPrice = productPrice.getText();
			ReportUtils.log.info("Get Product Price From Product : " + prdPrice);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
		return prdPrice;
	}

	/**
	 * Method to add product to cart - select size & click on Add to cart button
	 * 
	 * @param prdSize
	 */
	public void addToCart(String prdSize) {
		try {
			// Click on Product Size
			selectProductSize(prdSize);
			String expectedProductName = getProductName();
			String expectedProductPrice = getProductPrice();
			// Click on Add To Cart Button
			clickOnAddtoCartButton();
			ReportUtils.log.info("MATCHED: Actual Product Name & Expected ProductName : "
					+ BaseUtils.isMatching(getProductNameFromCart(), expectedProductName));
			ReportUtils.log.info("MATCHED: Actual Product Price & Expected Product Price : "
					+ BaseUtils.isMatching(getProductPriceFromCart(), expectedProductPrice));
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to select Product Size
	 * 
	 * @param driver
	 * @param prdSize
	 */
	public void selectProductSize(String prdSize) {
		try {
			WebElement webElement = ActionUtils.getWebElement(OR_ProductPage.productSize, prdSize);
			WaitUtils.ScrolltoElementusingActionclass(webElement, driver);
			webElement.click();
			ReportUtils.log.info("Product Size Selected  : " + prdSize);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to Click on Add to cart button
	 * 
	 * @param driver
	 */
	public void clickOnAddtoCartButton() {
		try {
			WaitUtils.waitForElementToBeClickable(By.xpath(OR_ProductPage.addToCartButton), driver, 30);
			addToCartButton.click();
			ReportUtils.log.info("Add to Cart Button is clicked : ");
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to get Product Name From Cart Page
	 * 
	 * @return
	 */
	public String getProductNameFromCart() {
		String getProductNameOnCartItem = "";
		try {
			getProductNameOnCartItem = productNameOnCart.getText();
			ReportUtils.log.info("Product Name On the Cart Item : " + getProductNameOnCartItem);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
		return getProductNameOnCartItem;
	}

	/**
	 * Method to get product price from the cart
	 * 
	 * @return
	 */
	public String getProductPriceFromCart() {
		String getProductPriceOnCartItem = "";
		try {
			getProductPriceOnCartItem = productPriceOnCart.getText();
			ReportUtils.log.info("Product Name On the Cart Item : " + getProductPriceOnCartItem);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
		return getProductPriceOnCartItem;
	}

	/**
	 * Method to remove item from cart - - click on remove item Button & verify
	 * Remove Item
	 * 
	 * @param productBrand
	 * @param product
	 */
	public void removeFromCart(String productBrand, String product) {
		try {
			clickOnRemoveItemButton();
			clickOnRemoveItemPopup(productBrand, product);
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to click on Remove Item Button on Cart Page
	 * 
	 * @param driver
	 */
	public void clickOnRemoveItemButton() {
		try {
			WaitUtils.wait2Second();
			WaitUtils.waitForElementToBeClickable(By.xpath(OR_ProductPage.removeBtn), driver, 10);
			removeBtn.click();
			ReportUtils.log.info("Remove Button Clicked ");
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		} catch (Exception e) {
			ReportUtils.log.fail(e);
		}
	}

	/**
	 * Method to click on remove item popup and verify toaster message
	 * 
	 * @param driver
	 * @param brand
	 * @param productName
	 */
	public void clickOnRemoveItemPopup(String brand, String productName) {
		try {
			String removedItemSuccessToaster = "'Successfully removed " + brand + " " + productName;
			ReportUtils.log.info("Expected Success Toaster Message : " + removedItemSuccessToaster);
			String xpathToasterMessage = "//div[contains(text()," + removedItemSuccessToaster + "')]";
			WaitUtils.waitForElementToBeClickable(By.xpath(OR_ProductPage.removeBtn), driver, 10);
			removeBtn.click();
			ReportUtils.log.info("Remove Button Clicked From Remove Item Popup");
			WaitUtils.waitForElementToBeVisibile(By.xpath(xpathToasterMessage), driver, 10);
			ReportUtils.log.info("Success Toaster Message For Removed Items : "
					+ driver.findElement(By.xpath(xpathToasterMessage)).getText());
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}

	/**
	 * Method to navigate to another tab
	 * 
	 * @param driver
	 */
	public void navigateToProductPage() {
		try {
			// Get the current window handle
			String parentHandle = driver.getWindowHandle();
			// Get the list of window handles
			Set<String> tabs = driver.getWindowHandles();
			// Use the list of window handles to switch between windows
			for (String tab : tabs) {
				if (!tab.equals(parentHandle)) {
					driver.switchTo().window(tab);
				}
			}
		} catch (Exception e) {
			ReportUtils.log.fail(e);
			BaseUtils.getScreenShotPath(driver,
					result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		}
	}
}
