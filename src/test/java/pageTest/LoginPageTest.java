package pageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageClasses.DashBoardPage;
import pageClasses.LoginPage;

public class LoginPageTest extends BaseTest{
	
	LoginPage lp;
	DashBoardPage db;
	
	@Test
	public void verifyLoginWithCorrectCredentialsTest() {
		lp = new LoginPage(driver);
		lp.enterUsername("Admin");
		lp.enterPassword("admin123");
		lp.clickLogin();
		
		db = new DashBoardPage(driver);		
		String headerText = db.getPageHeaderText();		
		Assert.assertEquals(headerText, "Dashboard");
	}
	
	@Test
	public void verifyLoginWithCorrectCredentialsTest2() {
		lp = new LoginPage(driver);
		lp.login("Admin", "admin1234");	
		
		db = new DashBoardPage(driver);		
		String headerText = db.getPageHeaderText();		
		Assert.assertEquals(headerText, "Dashboard");
	}
	
	@Test
	public void verifyLoginWithInCorrectCredentialsTest() {
		lp = new LoginPage(driver);
		lp.login("Admin", "admin1234");	
				
		String errorMsg = lp.getErrorMessage();	
		Assert.assertEquals(errorMsg, "Invalid credentials");
	}


}
