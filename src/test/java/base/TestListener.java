package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    
    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = "reports/ExtentReport_" + timestamp + ".html";
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");
        sparkReporter.config().setReportName("Hybrid Framework Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "OrangeHRM");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        
        logger.info("ExtentReports initialized: {}", reportPath);
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("Test started: {}", result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, result.getThrowable());
        
        // Capture screenshot on failure
        try {
            String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName());
            if (screenshotPath != null) {
                extentTest.get().addScreenCaptureFromPath(screenshotPath);
                logger.info("Screenshot captured: {}", screenshotPath);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
        
        logger.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        logger.warn("Test skipped: {}", result.getMethod().getMethodName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            logger.info("ExtentReports flushed successfully");
        }
    }
    
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}
