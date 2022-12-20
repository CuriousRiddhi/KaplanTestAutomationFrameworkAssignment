package pageTest;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.flipkart.qa.pageClasses.HomePage;
import com.flipkart.qa.pageClasses.ProductPage;
import com.flipkart.qa.utils.ActionUtils;
import com.flipkart.qa.utils.BaseUtils;
import com.flipkart.qa.utils.ExcelUtils;

/** 
 * Verify Flipkart Test Scenario - to add product & remove product from the cart
 * @author riddhi
 *
 */
public class FlipkartAddtocartItemTest extends BaseTest {
	HomePage homePage;
	ProductPage prdPage;
	ActionUtils actionUtils;
	HashMap<String, String> productData;

	/**
	 * Test to verify Flipkart flow of adding and removing product from cart 
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void FlipkartAddRemoveProductTest() throws IOException, Exception {
		boolean isEleDisplayed = false;
		homePage = new HomePage(driver);
		prdPage = new ProductPage(driver);
		actionUtils = new ActionUtils(driver);

		productData = ExcelUtils.getExcelData("FlipKartCartTest");
		homePage.closeIconOnLoginPopup();
		String actualTitle = homePage.getHomePageTitle();
		String expectedTitle = BaseUtils.getConfigValue("homePageTitle");
		Assert.assertEquals(actualTitle, expectedTitle, "FAILED : Home Page Title is not matching with Expected Title");

		isEleDisplayed = actionUtils.isElementDisplayed("mainMenuItem", productData.get("mainMenuItem"));
		Assert.assertTrue(isEleDisplayed,
				"Fail: FlipKart Main Menu Element Displayed - " + productData.get("mainMenuItem"));
		homePage.clickOnMainMenuItem(productData.get("mainMenuItem"));

		isEleDisplayed = actionUtils.isElementDisplayed("subMenuItem", productData.get("subMenuItem"));
		Assert.assertTrue(isEleDisplayed,
				"Fail: FlipKart Sub Menu Item Element Displayed - " + productData.get("subMenuItem"));
		homePage.mouseHoverSubsection(productData.get("subMenuItem"));

		isEleDisplayed = actionUtils.isElementDisplayed("subSectionItem", productData.get("subSectionItem"));
		Assert.assertTrue(isEleDisplayed,
				"Fail: FlipKart Sub Section Element Displayed - " + productData.get("subSectionItem"));
		homePage.clickonSubItem(productData.get("subSectionItem"));

		isEleDisplayed = actionUtils.isElementDisplayed("sortByOption", productData.get("sortByOption"));
		Assert.assertTrue(isEleDisplayed,
				"Fail: FlipKart Sort By Option Element Displayed - " + productData.get("sortByOption"));
		prdPage.clickOnsortByOption(productData.get("sortByOption"));

		isEleDisplayed = actionUtils.isElementDisplayed("product", productData.get("product"));
		Assert.assertTrue(isEleDisplayed, "Fail: FlipKart Product Element Displayed - " + productData.get("product"));
		prdPage.clickOnProduct(productData.get("product"));

		prdPage.navigateToProductPage();
		isEleDisplayed = actionUtils.isElementDisplayed("productSize", productData.get("productSize"));
		Assert.assertTrue(prdPage.addToCartButton.isDisplayed(), "Fail: Flip Kart 'Add To Cart' Button is displayed!");

		prdPage.selectProductSize(productData.get("productSize"));
		prdPage.clickOnAddtoCartButton();

		Assert.assertTrue(prdPage.removeBtn.isDisplayed(), "Fail: Flip Kart 'Add To Cart' Button is displayed!");
		prdPage.clickOnRemoveItemButton();
		Assert.assertTrue(prdPage.removeItemPopup.isDisplayed(),
				"Fail: Flip Kart 'Remove Item Popup' Confirmation Popup is displayed!");
		Assert.assertTrue(prdPage.removeButtomonPopup.isDisplayed(),
				"Fail: Flip Kart Remove Button on Popup is displayed!");
		prdPage.clickOnRemoveItemPopup(productData.get("productBrand"), productData.get("product"));
	}
}
