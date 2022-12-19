package com.flipkart.qa.properties;

/**
 * Xpaths for Product page elements
 * @author riddh
 *
 */
public class OR_ProductPage {
	public static final String sortByOption = "//div[contains(text(),'";
	public static final String product = "//div[@class='_2B099V']//a[contains(text(),'";
	public static final String productName= "//span[@class='B_NuCI']";
	public static final String productPrice= "//div[@class='_30jeq3 _16Jk6d']";	
	public static final String addToCartButton = "//button[text()='Add to cart']";
	public static final String productSize = "//span[text()='Size']//..//a[@class='_1fGeJ5 _2UVyXR _31hAvz'][contains(text(),'";
	public static final String productNameOnCart = "//a[@class='_2Kn22P gBNbID']";
	public static final String productPriceOnCart = "//span[@class='_2-ut7f _1WpvJ7']";
	
	public static final String removeBtn = "//div[text()='Remove']";
	public static final String removeItemPopup = "//div[text()='Remove Item']";
	public static final String removeButtomonPopup = "//div[@class='_3dsJAO _24d-qY FhkMJZ'][text()='Remove']";
	//Successfully removed Babysafe Baby Boys & Baby Girls Graphic Print Pure Cotton T Shirt from your cart	
}
