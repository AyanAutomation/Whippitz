package Listeners_Reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

public class Listen extends Reports implements ITestListener {

	ExtentReports Report = Reporter();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String method_Name = result.getMethod().getMethodName();
		Report.createTest(method_Name);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
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
