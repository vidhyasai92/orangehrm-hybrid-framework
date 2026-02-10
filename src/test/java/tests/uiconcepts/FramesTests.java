package tests.uiconcepts;

import base.BaseTest;
import base.TestListener;
import com.aventstack.extentreports.Status;
import config.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FramesPage;

public class FramesTests extends BaseTest {
    
    private FramesPage framesPage;
    
    @BeforeMethod
    public void navigateToFramesPage() {
        getDriver().get(ConfigLoader.getBaseUrlDemoQA() + "/frames");
        framesPage = new FramesPage();
        logger.info("Navigated to Frames page");
    }
    
    @Test(priority = 1, description = "Verify switching to frame 1 and reading content")
    public void testFrame1() {
        TestListener.getExtentTest().log(Status.INFO, "Testing frame 1");
        
        framesPage.switchToFrame1();
        String headingText = framesPage.getFrameHeadingText();
        
        Assert.assertNotNull(headingText, "Frame heading should not be null");
        Assert.assertTrue(headingText.contains("This is a sample page"), 
                         "Frame should contain expected text");
        
        framesPage.switchToDefaultContent();
        
        TestListener.getExtentTest().log(Status.PASS, "Frame 1 test passed: " + headingText);
        logger.info("Frame 1 test passed");
    }
    
    @Test(priority = 2, description = "Verify switching to frame 2 and reading content")
    public void testFrame2() {
        TestListener.getExtentTest().log(Status.INFO, "Testing frame 2");
        
        framesPage.switchToFrame2();
        String headingText = framesPage.getFrameHeadingText();
        
        Assert.assertNotNull(headingText, "Frame heading should not be null");
        
        framesPage.switchToDefaultContent();
        
        TestListener.getExtentTest().log(Status.PASS, "Frame 2 test passed: " + headingText);
        logger.info("Frame 2 test passed");
    }
}
