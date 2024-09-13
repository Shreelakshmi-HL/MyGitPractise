package Training_FW.SeleniumFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject()
	{
		String path =System.getProperty("user.dir")+"//reports//index.html";
		
		//create object of reporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//setting title and name in the header
		reporter.config().setReportName("Test Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//report attachment is done by extent object of ExtentReports class
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "shreelakshmi");
		return extent;
		
		
		
	}
}
