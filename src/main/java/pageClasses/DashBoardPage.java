package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;

public class DashBoardPage {

	WebDriver driver;	

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	 By header = By.xpath("//h6[text()='Dashboard']");
	
	public String getPageHeaderText() {
		String headerText = driver.findElement(header).getText();
		ReportUtils.log.info("DashBoard: Header Text is : " + headerText);
		return headerText;
	}

}
