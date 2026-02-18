package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.util.List;

public class DropdownTests extends BaseTest {
    
    @Test(priority = 1, description = "Test standard dropdown selection by visible text")
    public void testDropdownByVisibleText() {
        getDriver().get("https://demoqa.com/select-menu");
        
        WebElement dropdown = WaitUtils.waitForElementToBeVisible(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        
        // Select by visible text
        select.selectByVisibleText("Purple");
        
        WebElement selectedOption = select.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getText(), "Purple", "Selected option should be Purple");
        
        logger.info("Dropdown selection by visible text test passed");
    }
    
    @Test(priority = 2, description = "Test dropdown selection by value")
    public void testDropdownByValue() {
        getDriver().get("https://demoqa.com/select-menu");
        
        WebElement dropdown = WaitUtils.waitForElementToBeVisible(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        
        // Select by value
        select.selectByValue("3");
        
        WebElement selectedOption = select.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getAttribute("value"), "3", "Selected option value should be 3");
        
        logger.info("Dropdown selection by value test passed");
    }
    
    @Test(priority = 3, description = "Test dropdown selection by index")
    public void testDropdownByIndex() {
        getDriver().get("https://demoqa.com/select-menu");
        
        WebElement dropdown = WaitUtils.waitForElementToBeVisible(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        
        // Select by index (0-based)
        select.selectByIndex(2);
        
        WebElement selectedOption = select.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getText(), "Yellow", "Selected option should be Yellow (index 2)");
        
        logger.info("Dropdown selection by index test passed");
    }
    
    @Test(priority = 4, description = "Test multi-select dropdown")
    public void testMultiSelectDropdown() {
        getDriver().get("https://demoqa.com/select-menu");
        
        WebElement multiSelectDropdown = WaitUtils.waitForElementToBeVisible(By.xpath("//select[@id='cars']"));
        Select multiSelect = new Select(multiSelectDropdown);
        
        Assert.assertTrue(multiSelect.isMultiple(), "Dropdown should support multiple selection");
        
        // Select multiple options
        multiSelect.selectByVisibleText("Volvo");
        multiSelect.selectByVisibleText("Audi");
        
        List<WebElement> selectedOptions = multiSelect.getAllSelectedOptions();
        Assert.assertEquals(selectedOptions.size(), 2, "Should have 2 selected options");
        
        // Deselect one option
        multiSelect.deselectByVisibleText("Volvo");
        selectedOptions = multiSelect.getAllSelectedOptions();
        Assert.assertEquals(selectedOptions.size(), 1, "Should have 1 selected option after deselection");
        Assert.assertEquals(selectedOptions.get(0).getText(), "Audi", "Remaining selected option should be Audi");
        
        logger.info("Multi-select dropdown test passed");
    }
    
    @Test(priority = 5, description = "Test getting all dropdown options")
    public void testGetAllDropdownOptions() {
        getDriver().get("https://demoqa.com/select-menu");
        
        WebElement dropdown = WaitUtils.waitForElementToBeVisible(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        
        List<WebElement> allOptions = select.getOptions();
        Assert.assertTrue(allOptions.size() > 0, "Dropdown should have options");
        
        // Verify first option is the default
        Assert.assertEquals(allOptions.get(0).getText(), "Red", "First option should be Red");
        
        logger.info("Get all dropdown options test passed with " + allOptions.size() + " options found");
    }
}