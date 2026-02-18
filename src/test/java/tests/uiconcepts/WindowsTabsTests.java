package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;
import utils.WindowUtils;

import java.util.Set;

public class WindowsTabsTests extends BaseTest {
    
    @Test(priority = 1, description = "Test opening new tab and switching")
    public void testNewTabHandling() {
        getDriver().get("https://demoqa.com/browser-windows");
        
        String originalWindow = getDriver().getWindowHandle();
        
        WebElement newTabButton = WaitUtils.waitForElementToBeClickable(By.id("tabButton"));
        newTabButton.click();
        
        // Wait for new tab to open
        WaitUtils.waitForNumberOfWindowsToBe(2);
        
        Set<String> allWindows = getDriver().getWindowHandles();
        Assert.assertEquals(allWindows.size(), 2, "Should have 2 windows/tabs open");
        
        // Switch to new tab
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                getDriver().switchTo().window(window);
                break;
            }
        }
        
        // Verify we're on the new tab
        WebElement heading = WaitUtils.waitForElementToBeVisible(By.id("sampleHeading"));
        Assert.assertEquals(heading.getText(), "This is a sample page", "New tab should show sample page");
        
        // Close new tab and switch back
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
        
        // Verify we're back on original page
        Assert.assertTrue(getDriver().findElement(By.id("tabButton")).isDisplayed(), "Should be back on original page");
        
        logger.info("New tab handling test passed");
    }
    
    @Test(priority = 2, description = "Test opening new window and switching")
    public void testNewWindowHandling() {
        getDriver().get("https://demoqa.com/browser-windows");
        
        String originalWindow = getDriver().getWindowHandle();
        
        WebElement newWindowButton = WaitUtils.waitForElementToBeClickable(By.id("windowButton"));
        newWindowButton.click();
        
        // Wait for new window to open
        WaitUtils.waitForNumberOfWindowsToBe(2);
        
        // Switch to new window using utility
        WindowUtils.switchToNewWindow(originalWindow);
        
        // Verify we're on the new window
        WebElement heading = WaitUtils.waitForElementToBeVisible(By.id("sampleHeading"));
        Assert.assertEquals(heading.getText(), "This is a sample page", "New window should show sample page");
        
        // Close new window and switch back
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
        
        logger.info("New window handling test passed");
    }
    
    @Test(priority = 3, description = "Test handling multiple windows")
    public void testMultipleWindowHandling() {
        getDriver().get("https://demoqa.com/browser-windows");
        
        String originalWindow = getDriver().getWindowHandle();
        
        // Open multiple tabs
        WebElement newTabButton = WaitUtils.waitForElementToBeClickable(By.id("tabButton"));
        newTabButton.click();
        WaitUtils.waitForNumberOfWindowsToBe(2);
        
        newTabButton.click();
        WaitUtils.waitForNumberOfWindowsToBe(3);
        
        Set<String> allWindows = getDriver().getWindowHandles();
        Assert.assertEquals(allWindows.size(), 3, "Should have 3 windows/tabs open");
        
        // Close all new windows and return to original
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                getDriver().switchTo().window(window);
                getDriver().close();
            }
        }
        
        getDriver().switchTo().window(originalWindow);
        
        // Verify only original window remains
        allWindows = getDriver().getWindowHandles();
        Assert.assertEquals(allWindows.size(), 1, "Should have only 1 window remaining");
        
        logger.info("Multiple window handling test passed");
    }
    
    @Test(priority = 4, description = "Test window title and URL verification")
    public void testWindowTitleAndUrl() {
        getDriver().get("https://demoqa.com/browser-windows");
        
        String originalWindow = getDriver().getWindowHandle();
        String originalTitle = getDriver().getTitle();
        String originalUrl = getDriver().getCurrentUrl();
        
        WebElement newTabButton = WaitUtils.waitForElementToBeClickable(By.id("tabButton"));
        newTabButton.click();
        
        WaitUtils.waitForNumberOfWindowsToBe(2);
        WindowUtils.switchToNewWindow(originalWindow);
        
        // Verify new window title and URL
        String newTitle = getDriver().getTitle();
        String newUrl = getDriver().getCurrentUrl();
        
        Assert.assertNotEquals(newTitle, originalTitle, "New window should have different title");
        Assert.assertNotEquals(newUrl, originalUrl, "New window should have different URL");
        
        // Switch back and verify original window
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
        
        Assert.assertEquals(getDriver().getTitle(), originalTitle, "Should be back to original title");
        Assert.assertEquals(getDriver().getCurrentUrl(), originalUrl, "Should be back to original URL");
        
        logger.info("Window title and URL verification test passed");
    }
}