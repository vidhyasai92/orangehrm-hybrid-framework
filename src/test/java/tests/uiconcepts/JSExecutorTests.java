package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JSUtils;
import utils.WaitUtils;

public class JSExecutorTests extends BaseTest {
    
    @Test(priority = 1, description = "Test JavaScript click on hidden element")
    public void testJSClick() {
        getDriver().get("https://demoqa.com/buttons");
        
        WebElement clickButton = WaitUtils.waitForElementToBePresent(By.xpath("//button[text()='Click Me']"));
        
        // Use JS click instead of regular click
        JSUtils.clickElement(clickButton);
        
        WebElement message = WaitUtils.waitForElementToBeVisible(By.id("dynamicClickMessage"));
        Assert.assertEquals(message.getText(), "You have done a dynamic click", "JS click should trigger the action");
        
        logger.info("JavaScript click test passed");
    }
    
    @Test(priority = 2, description = "Test scrolling to element using JavaScript")
    public void testScrollToElement() {
        getDriver().get("https://demoqa.com/");
        
        WebElement footer = WaitUtils.waitForElementToBePresent(By.xpath("//footer"));
        
        // Scroll to footer using JavaScript
        JSUtils.scrollToElement(footer);
        
        // Verify element is in view
        Assert.assertTrue(footer.isDisplayed(), "Footer should be visible after scrolling");
        
        // Scroll back to top
        JSUtils.scrollToTop();
        
        WebElement header = getDriver().findElement(By.xpath("//header"));
        Assert.assertTrue(header.isDisplayed(), "Header should be visible after scrolling to top");
        
        logger.info("Scroll to element test passed");
    }
    
    @Test(priority = 3, description = "Test JavaScript text input")
    public void testJSTextInput() {
        getDriver().get("https://demoqa.com/text-box");
        
        WebElement nameField = WaitUtils.waitForElementToBeVisible(By.id("userName"));
        
        String testName = "JavaScript Input Test";
        
        // Use JavaScript to set value
        JSUtils.enterText(nameField, testName);
        
        String enteredValue = nameField.getAttribute("value");
        Assert.assertEquals(enteredValue, testName, "JavaScript should set the text value correctly");
        
        logger.info("JavaScript text input test passed");
    }
    
    @Test(priority = 4, description = "Test getting page information using JavaScript")
    public void testGetPageInfo() {
        getDriver().get("https://demoqa.com/");
        
        // Get page title using JavaScript
        String jsTitle = JSUtils.getPageTitle();
        String seleniumTitle = getDriver().getTitle();
        Assert.assertEquals(jsTitle, seleniumTitle, "JavaScript and Selenium should return same title");
        
        // Get page URL using JavaScript
        String jsUrl = JSUtils.getCurrentUrl();
        String seleniumUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(jsUrl, seleniumUrl, "JavaScript and Selenium should return same URL");
        
        logger.info("Get page info test passed - Title: " + jsTitle);
    }
    
    @Test(priority = 5, description = "Test highlighting element using JavaScript")
    public void testHighlightElement() {
        getDriver().get("https://demoqa.com/text-box");
        
        WebElement nameField = WaitUtils.waitForElementToBeVisible(By.id("userName"));
        
        // Highlight element
        JSUtils.highlightElement(nameField);
        
        // Verify element has highlight style (border should be changed)
        String borderStyle = nameField.getCssValue("border");
        Assert.assertTrue(borderStyle.contains("red") || borderStyle.contains("rgb(255, 0, 0)"), 
                         "Element should have red border after highlighting");
        
        logger.info("Highlight element test passed");
    }
    
    @Test(priority = 6, description = "Test JavaScript alert generation")
    public void testGenerateAlert() {
        getDriver().get("https://demoqa.com/");
        
        // Generate alert using JavaScript
        JSUtils.generateAlert("This is a JavaScript generated alert");
        
        // Handle the alert
        String alertText = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alertText, "This is a JavaScript generated alert", "Alert text should match");
        
        getDriver().switchTo().alert().accept();
        
        logger.info("Generate alert test passed");
    }
    
    @Test(priority = 7, description = "Test refresh page using JavaScript")
    public void testRefreshPage() {
        getDriver().get("https://demoqa.com/text-box");
        
        WebElement nameField = WaitUtils.waitForElementToBeVisible(By.id("userName"));
        nameField.sendKeys("Test Value");
        
        String valueBeforeRefresh = nameField.getAttribute("value");
        Assert.assertEquals(valueBeforeRefresh, "Test Value", "Value should be set before refresh");
        
        // Refresh page using JavaScript
        JSUtils.refreshPage();
        
        // Wait for page to reload
        nameField = WaitUtils.waitForElementToBeVisible(By.id("userName"));
        String valueAfterRefresh = nameField.getAttribute("value");
        Assert.assertTrue(valueAfterRefresh.isEmpty(), "Value should be cleared after refresh");
        
        logger.info("Refresh page test passed");
    }
}