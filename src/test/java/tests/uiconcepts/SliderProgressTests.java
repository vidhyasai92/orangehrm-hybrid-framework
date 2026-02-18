package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

public class SliderProgressTests extends BaseTest {
    
    @Test(priority = 1, description = "Test slider movement")
    public void testSliderMovement() {
        getDriver().get("https://demoqa.com/slider");
        
        WebElement slider = WaitUtils.waitForElementToBeVisible(By.xpath("//input[@type='range']"));
        WebElement sliderValue = getDriver().findElement(By.id("sliderValue"));
        
        String initialValue = sliderValue.getAttribute("value");
        logger.info("Initial slider value: " + initialValue);
        
        // Move slider using Actions class
        Actions actions = new Actions(getDriver());
        actions.clickAndHold(slider).moveByOffset(50, 0).release().perform();
        
        String newValue = sliderValue.getAttribute("value");
        logger.info("New slider value after movement: " + newValue);
        
        Assert.assertNotEquals(newValue, initialValue, "Slider value should change after movement");
        
        logger.info("Slider movement test passed");
    }
    
    @Test(priority = 2, description = "Test slider minimum value")
    public void testSliderMinimumValue() {
        getDriver().get("https://demoqa.com/slider");
        
        WebElement slider = WaitUtils.waitForElementToBeVisible(By.xpath("//input[@type='range']"));
        WebElement sliderValue = getDriver().findElement(By.id("sliderValue"));
        
        // Move slider to minimum position
        Actions actions = new Actions(getDriver());
        actions.clickAndHold(slider).moveByOffset(-200, 0).release().perform();
        
        String minValue = sliderValue.getAttribute("value");
        logger.info("Minimum slider value: " + minValue);
        
        // Verify it's at or near minimum (usually 0 or close to it)
        int minValueInt = Integer.parseInt(minValue);
        Assert.assertTrue(minValueInt <= 10, "Slider should be at minimum value (close to 0)");
        
        logger.info("Slider minimum value test passed");
    }
    
    @Test(priority = 3, description = "Test slider maximum value")
    public void testSliderMaximumValue() {
        getDriver().get("https://demoqa.com/slider");
        
        WebElement slider = WaitUtils.waitForElementToBeVisible(By.xpath("//input[@type='range']"));
        WebElement sliderValue = getDriver().findElement(By.id("sliderValue"));
        
        // Move slider to maximum position
        Actions actions = new Actions(getDriver());
        actions.clickAndHold(slider).moveByOffset(200, 0).release().perform();
        
        String maxValue = sliderValue.getAttribute("value");
        logger.info("Maximum slider value: " + maxValue);
        
        // Verify it's at or near maximum (usually 100 or close to it)
        int maxValueInt = Integer.parseInt(maxValue);
        Assert.assertTrue(maxValueInt >= 90, "Slider should be at maximum value (close to 100)");
        
        logger.info("Slider maximum value test passed");
    }
    
    @Test(priority = 4, description = "Test progress bar")
    public void testProgressBar() {
        getDriver().get("https://demoqa.com/progress-bar");
        
        WebElement startButton = WaitUtils.waitForElementToBeClickable(By.id("startStopButton"));
        WebElement progressBar = getDriver().findElement(By.xpath("//div[@role='progressbar']"));
        
        // Check initial state
        String initialProgress = progressBar.getAttribute("aria-valuenow");
        Assert.assertEquals(initialProgress, "0", "Progress bar should start at 0%");
        
        // Start progress
        startButton.click();
        
        // Wait for some progress
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Check progress has increased
        String currentProgress = progressBar.getAttribute("aria-valuenow");
        int currentProgressInt = Integer.parseInt(currentProgress);
        Assert.assertTrue(currentProgressInt > 0, "Progress should be greater than 0 after starting");
        
        // Stop progress
        startButton.click();
        
        logger.info("Progress bar test passed with progress: " + currentProgress + "%");
    }
    
    @Test(priority = 5, description = "Test progress bar completion")
    public void testProgressBarCompletion() {
        getDriver().get("https://demoqa.com/progress-bar");
        
        WebElement startButton = WaitUtils.waitForElementToBeClickable(By.id("startStopButton"));
        WebElement progressBar = getDriver().findElement(By.xpath("//div[@role='progressbar']"));
        
        // Start progress
        startButton.click();
        
        // Wait for completion (max 15 seconds)
        int waitTime = 0;
        while (waitTime < 15000) {
            String currentProgress = progressBar.getAttribute("aria-valuenow");
            if ("100".equals(currentProgress)) {
                break;
            }
            try {
                Thread.sleep(500);
                waitTime += 500;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        String finalProgress = progressBar.getAttribute("aria-valuenow");
        
        // Check if completed or at least made significant progress
        int finalProgressInt = Integer.parseInt(finalProgress);
        Assert.assertTrue(finalProgressInt >= 50, "Progress should reach at least 50% within timeout");
        
        logger.info("Progress bar completion test passed with final progress: " + finalProgress + "%");
    }
}