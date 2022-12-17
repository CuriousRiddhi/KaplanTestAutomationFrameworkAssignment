package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ReportUtils;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By username = By.xpath("//input[@name='username']");
	private By password = By.xpath("//input[@name='password']");
	private By loginButton = By.xpath("//button[@type='submit']");
	private By errrorMessage = By.xpath("//p[text()='Invalid credentials']");
	
	public void enterUsername(String userName) {
		try {
			List<WebElement> elements = driver.findElements(username);
			if(elements.size() > 0) {
				elements.get(0).sendKeys(userName);						
			}
			
			ReportUtils.log.info("Enter User Name : "+ userName);
		}
		
		catch(Exception e){
			ReportUtils.log.info("Couldn't enter username, an error has been obervered " + e);
		}
					
	}
	
	public void enterPassword(String pswd) {
		driver.findElement(password).sendKeys(pswd);
		ReportUtils.log.info("Enter password : *****");
	}
	
	public void clickLogin() {
		driver.findElement(loginButton).click();
		ReportUtils.log.info("Clicking on Submit button");
	}

	public void login(String userName,String pswd) {
		enterUsername(userName);
		enterPassword(pswd);
		clickLogin();
	}
	
	public String getErrorMessage() {
		
		return driver.findElement(errrorMessage).getText();
	}
}
