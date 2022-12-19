package pageTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.flipkart.qa.utils.BaseUtils;
import com.flipkart.qa.utils.ReportUtils;
import com.flipkart.qa.utils.ExcelUtils;

public class BaseTest {
	
	WebDriver driver; 
	public static ExcelUtils excelRiddhi = null;
	//public static String testDataPath = System.getProperty("user.dir") + File.separator + "test-data";
	/**
	 * Initialize Extent report & Excel dataprovider objects
	 */
	@BeforeSuite
	public void initialise() {
		ReportUtils.initReport();
		excelRiddhi = new ExcelUtils(BaseUtils.getConfigValue("testDataPath"));
	}		
	/**
	 * Verify Browser and Open Application URL 
	 * @param method
	 * @throws IOException
	 */
	@BeforeMethod
	public void init(Method method) throws IOException {			
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
		String urlAddress =BaseUtils.getConfigValue("url"); 
		driver.get(urlAddress);
		ReportUtils.log.info("Url Launched: " + urlAddress);
	}
	
	/**
	 * Update result in Report logs, if its failure
	 * @param result
	 * @throws IOException
	 */
	@AfterMethod
	public void end(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			ReportUtils.log.fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(BaseUtils.getScreenShotPath(driver,
							result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName()))
							.build());
		}		
		ReportUtils.log.info("Adding Result to Extent Report");
	}
	
	/**
	 * Close all instances of chrome driver
	 * Generate report after suite Annotation
	 */
	@AfterSuite
	public void tearDown() {
		driver.quit();
		ReportUtils.log.info("Generating Report in After Suite Annotation");
		ReportUtils.generateReport();
	}
	

}
