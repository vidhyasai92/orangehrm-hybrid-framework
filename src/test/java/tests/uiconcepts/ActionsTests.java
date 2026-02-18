package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

public class ActionsTests extends BaseTest {
    
    @Test(priority = 1, description = "Test hover functionality")
    public void testHoverAction() {
        getDriver().get("https://demoqa.com/menu");
        
        WebElement mainItem = WaitUtils.waitForElementToBeVisible(By.xpath("//a[text()='Main Item 2']"));
        WebElement subList = getDriver().findElement(By.xpath("//ul[@id='nav']/li[2]/ul"));
        
        // Initially sub menu should not be visible
        Assert.assertFalse(subList.isDisplayed(), "Sub menu should not be visible initially");
        
        Actions actions = new Actions(getDriver());
        actions.moveToElement(mainItem).perform();
        
        // After hover, sub menu should be visible
        WaitUtils.waitForElementToBeVisible(By.xpath("//ul[@id='nav']/li[2]/ul"));
        Assert.assertTrue(subList.isDisplayed(), "Sub menu should be visible after hover");
        
        logger.info("Hover action test passed");
    }
    
    @Test(priority = 2, description = "Test drag and drop functionality")
    public void testDragAndDrop() {
        getDriver().get("https://demoqa.com/droppable");
        
        WebElement dragElement = WaitUtils.waitForElementToBeVisible(By.id("draggable"));
        WebElement dropElement = WaitUtils.waitForElementToBeVisible(By.id("droppable"));
        
        String initialText = dropElement.getText();
        Assert.assertEquals(initialText, "Drop here", "Initial drop area text should be 'Drop here'");
        
        Actions actions = new Actions(getDriver());
        actions.dragAndDrop(dragElement, dropElement).perform();
        
        WaitUtils.waitForTextToBePresentInElement(dropElement, "Dropped!");
        String finalText = dropElement.getText();
        Assert.assertEquals(finalText, "Dropped!", "Drop area text should change to 'Dropped!' after drag and drop");
        
        logger.info("Drag and drop test passed");
    }
    
    @Test(priority = 3, description = "Test right click context menu")
    public void testRightClick() {
        getDriver().get("https://demoqa.com/buttons");
        
        WebElement rightClickButton = WaitUtils.waitForElementToBeVisible(By.id("rightClickBtn"));
        WebElement message = getDriver().findElement(By.id("rightClickMessage"));
        
        Actions actions = new Actions(getDriver());
        actions.contextClick(rightClickButton).perform();
        
        WaitUtils.waitForElementToBeVisible(By.id("rightClickMessage"));
        String messageText = message.getText();
        Assert.assertEquals(messageText, "You have done a right click", "Right click message should be displayed");
        
        logger.info("Right click test passed");
    }
    
    @Test(priority = 4, description = "Test double click functionality")
    public void testDoubleClick() {
        getDriver().get("https://demoqa.com/buttons");
        
        WebElement doubleClickButton = WaitUtils.waitForElementToBeVisible(By.id("doubleClickBtn"));
        WebElement message = getDriver().findElement(By.id("doubleClickMessage"));
        
        Actions actions = new Actions(getDriver());
        actions.doubleClick(doubleClickButton).perform();
        
        WaitUtils.waitForElementToBeVisible(By.id("doubleClickMessage"));
        String messageText = message.getText();
        Assert.assertEquals(messageText, "You have done a double click", "Double click message should be displayed");
        
        logger.info("Double click test passed");
    }
}