package Listeners_Reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listen extends Reports implements ITestListener {

	
	// must be static so all tests can access the same ThreadLocal
    private static final ThreadLocal<ExtentTest> Log_reader = new ThreadLocal<>();
	ExtentReports Report = Reporter();
	
	
	public static ExtentTest Print_in_Report() {
        return Log_reader.get();
    }	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String method_Name = result.getMethod().getMethodName();
		Log_reader.set(Report.createTest(method_Name)); /* Here Log_reader is storing the MEthod name and since*/
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		/* Since createTest()(Log_reader also storing extent report) and method_Name has been Stored in "Log_reader" object on " onTestStart(ITestResult result)" 
		so in this Method execution no need to write it( Code :- "String method_Name = result.getMethod().getMethodName();" ) here again. */
		
		Log_reader.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		

		/* Since createTest() and method_Name has been Stored in "Log_reader" object on " onTestStart(ITestResult result)" 
		so in this Method execution no need to write it( Code :- "String method_Name = result.getMethod().getMethodName();" ) here again. */
		
		Log_reader.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		Report.flush();
	}

}
