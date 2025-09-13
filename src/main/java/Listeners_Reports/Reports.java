package Listeners_Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports {
	
	
	
	public ExtentReports Reporter() {
		
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//Extended_Reports.html");
		
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Whippitz Automation Test Report");
		spark.config().setTheme(Theme.STANDARD); // Options: STANDARD / DARK
        spark.config().setEncoding("UTF-8");
        spark.config().setTimelineEnabled(true); // Adds execution timeline bar
        spark.config().setCss(".badge { font-size: 12px; padding: 4px; }");
		
		
		ExtentReports rp = new ExtentReports();
		
		rp.attachReporter(spark);
		rp.setSystemInfo("Automated By", "Ayan Sengupta (Automation Test Engineer)");
        rp.setSystemInfo("Framework", "Selenium + TestNG");
        rp.setSystemInfo("Report Type", "Extent Spark HTML");
        rp.setSystemInfo("OS", System.getProperty("os.name"));
        rp.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return rp;
		
	}

}
