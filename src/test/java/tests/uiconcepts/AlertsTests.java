package tests.uiconcepts;

import base.BaseTest;
import base.TestListener;
import com.aventstack.extentreports.Status;
import config.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsPage;

public class AlertsTests extends BaseTest {
    
    private AlertsPage alertsPage;
    
    @BeforeMethod
    public void navigateToAlertsPage() {
        getDriver().get(ConfigLoader.getBaseUrlDemoQA() + "/alerts");
        alertsPage = new AlertsPage();
        logger.info("Navigated to Alerts page");
    }
    
    @Test(priority = 1, description = "Verify simple alert accept")
    public void testSimpleAlert() {
        TestListener.getExtentTest().log(Status.INFO, "Testing simple alert");
        
        alertsPage.clickSimpleAlert();
        String alertText = alertsPage.acceptAlert();
        
        Assert.assertNotNull(alertText, "Alert text should not be null");
        TestListener.getExtentTest().log(Status.PASS, "Simple alert accepted: " + alertText);
        logger.info("Simple alert test passed");
    }
    
    @Test(priority = 2, description = "Verify timer alert")
    public void testTimerAlert() {
        TestListener.getExtentTest().log(Status.INFO, "Testing timer alert");
        
        alertsPage.clickTimerAlert();
        String alertText = alertsPage.acceptAlert();
        
        Assert.assertNotNull(alertText, "Timer alert text should not be null");
        TestListener.getExtentTest().log(Status.PASS, "Timer alert accepted: " + alertText);
        logger.info("Timer alert test passed");
    }
    
    @Test(priority = 3, description = "Verify confirm alert accept")
    public void testConfirmAlertAccept() {
        TestListener.getExtentTest().log(Status.INFO, "Testing confirm alert accept");
        
        alertsPage.clickConfirmAlert();
        alertsPage.acceptAlert();
        
        String result = alertsPage.getConfirmResult();
        Assert.assertTrue(result.contains("Ok"), "Result should contain 'Ok'");
        
        TestListener.getExtentTest().log(Status.PASS, "Confirm alert accepted: " + result);
        logger.info("Confirm alert accept test passed");
    }
    
    @Test(priority = 4, description = "Verify confirm alert dismiss")
    public void testConfirmAlertDismiss() {
        TestListener.getExtentTest().log(Status.INFO, "Testing confirm alert dismiss");
        
        alertsPage.clickConfirmAlert();
        alertsPage.dismissAlert();
        
        String result = alertsPage.getConfirmResult();
        Assert.assertTrue(result.contains("Cancel"), "Result should contain 'Cancel'");
        
        TestListener.getExtentTest().log(Status.PASS, "Confirm alert dismissed: " + result);
        logger.info("Confirm alert dismiss test passed");
    }
    
    @Test(priority = 5, description = "Verify prompt alert with text input")
    public void testPromptAlert() {
        TestListener.getExtentTest().log(Status.INFO, "Testing prompt alert");
        
        alertsPage.clickPromptAlert();
        String inputText = "Test User";
        alertsPage.sendKeysToAlert(inputText);
        
        String result = alertsPage.getPromptResult();
        Assert.assertTrue(result.contains(inputText), "Result should contain input text");
        
        TestListener.getExtentTest().log(Status.PASS, "Prompt alert test passed: " + result);
        logger.info("Prompt alert test passed");
    }
}
