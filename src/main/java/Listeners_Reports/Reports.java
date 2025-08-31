package Listeners_Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	
	
	public ExtentReports Reporter() {
		
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//Extended_Reports.html");
		
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Whippitz Automation Test Report");
		
		
		
		ExtentReports rp = new ExtentReports();
		
		rp.attachReporter(spark);
		rp.setSystemInfo("Automated By", "Ayan Sengupta Automation Test Engineer");
		
		return rp;
		
	}

}
