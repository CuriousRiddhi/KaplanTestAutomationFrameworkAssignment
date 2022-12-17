package pageTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.BaseUtils;
import utils.ReportUtils;

public class BaseTest {
	
	WebDriver driver; 
	
	@BeforeSuite
	public void initialise() {
		ReportUtils.initReport();
	}
		
	
	@BeforeMethod
	public void initialize(Method method) throws IOException {	
		
		ReportUtils.createTest(method.getName());
		
		String browser = BaseUtils.getConfigValue("browser");
		
		switch (browser) {
		
		case "chrome":
			driver = new ChromeDriver();
			ReportUtils.log.info("Chrome Browser Launched");
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			ReportUtils.log.info("Firefox Browser Launched");
			break;
			
		case "edge":
			driver = new EdgeDriver();
			ReportUtils.log.info("Edge Browser Launched");
			break;
			
		case "safari":
			driver = new SafariDriver();
			ReportUtils.log.info("Safari Browser Launched");
			break;

		default:
			break;
		}
				
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(BaseUtils.getConfigValue("implitWait"))));
		//Just for Chandan, don't copy it blindly
		//driver.manage().window().setPosition(new Point(4000, 100));
		String urlAddress =BaseUtils.getConfigValue("url"); 
		driver.get(urlAddress);
		ReportUtils.log.info("Url Launched: " + urlAddress);
	}
	
	@AfterMethod
	public void end(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			ReportUtils.log.fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(BaseUtils.getScreenShotPath(driver,
							result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName()))
							.build());
		}
		
		driver.quit();
		ReportUtils.log.info("Browser Closed");
	}
	
	@AfterSuite
	public void tearDown() {
		ReportUtils.generateReport();
	}
	

}
