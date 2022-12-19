package com.flipkart.qa.utils;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtils {
	
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest log;
	
	/**
	 * Method to initate ExtentReport
	 * @return
	 */
	public static ExtentReports initReport() {
		try
		{
			String time = String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy hh.mm.ss a")));
			
			spark = new ExtentSparkReporter("target/AutomationReport_" + time + ".html");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			
			spark.config().setDocumentTitle("ECommerce Automation Report");
			spark.config().setReportName("Automation Report");
			spark.config().setTheme(Theme.DARK);		
			spark.loadXMLConfig(new File("extent-config.xml"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return extent;				
	}
	
    /**
     * Method to Generate Report
     */
    public static void generateReport() {
    	extent.flush();
	}
    
    /**
     * Method to Create Test for extent report
     * @param methodName
     */
    public static void createTest(String methodName) {
    	log = extent.createTest(methodName);
    }


}
