package Listeners_Reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listen extends Reports implements ITestListener {

	
	private static final ThreadLocal<ExtentTest> Log_reader = new ThreadLocal<>();
	ExtentReports Report = Reporter();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String method_Name = result.getMethod().getMethodName();
		Report.createTest(method_Name);
		Log_reader.set(Report.createTest(method_Name));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String method_Name = result.getMethod().getMethodName();
		Log_reader.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String method_Name = result.getMethod().getMethodName();
		Report.createTest(method_Name).fail(result.getThrowable());
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
