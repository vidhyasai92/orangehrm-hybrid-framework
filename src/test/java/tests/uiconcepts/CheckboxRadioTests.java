package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.util.List;

public class CheckboxRadioTests extends BaseTest {
    
    @Test(priority = 1, description = "Test checkbox selection and deselection")
    public void testCheckboxSelection() {
        getDriver().get("https://demoqa.com/checkbox");
        
        WebElement homeCheckbox = WaitUtils.waitForElementToBeClickable(By.xpath("//span[@class='rct-checkbox']"));
        
        // Initially checkbox should not be selected
        WebElement homeCheckboxInput = getDriver().findElement(By.xpath("//input[@id='tree-node-home']"));
        Assert.assertFalse(homeCheckboxInput.isSelected(), "Home checkbox should not be selected initially");
        
        // Click to select
        homeCheckbox.click();
        Assert.assertTrue(homeCheckboxInput.isSelected(), "Home checkbox should be selected after click");
        
        // Click to deselect
        homeCheckbox.click();
        Assert.assertFalse(homeCheckboxInput.isSelected(), "Home checkbox should be deselected after second click");
        
        logger.info("Checkbox selection test passed");
    }
    
    @Test(priority = 2, description = "Test multiple checkbox selection")
    public void testMultipleCheckboxSelection() {
        getDriver().get("https://demoqa.com/checkbox");
        
        // Expand the tree first
        WebElement expandButton = WaitUtils.waitForElementToBeClickable(By.xpath("//button[@title='Expand all']"));
        expandButton.click();
        
        // Select multiple checkboxes
        List<WebElement> checkboxes = getDriver().findElements(By.xpath("//span[@class='rct-checkbox']"));
        
        int selectedCount = 0;
        for (int i = 0; i < Math.min(3, checkboxes.size()); i++) {
            checkboxes.get(i).click();
            selectedCount++;
        }
        
        // Verify result text shows selected items
        WebElement result = WaitUtils.waitForElementToBeVisible(By.id("result"));
        String resultText = result.getText();
        Assert.assertTrue(resultText.contains("You have selected"), "Result should show selected items");
        
        logger.info("Multiple checkbox selection test passed with " + selectedCount + " items selected");
    }
    
    @Test(priority = 3, description = "Test radio button selection")
    public void testRadioButtonSelection() {
        getDriver().get("https://demoqa.com/radio-button");
        
        WebElement yesRadio = WaitUtils.waitForElementToBeClickable(By.xpath("//label[@for='yesRadio']"));
        WebElement impressiveRadio = WaitUtils.waitForElementToBeClickable(By.xpath("//label[@for='impressiveRadio']"));
        
        // Select Yes radio button
        yesRadio.click();
        WebElement result = WaitUtils.waitForElementToBeVisible(By.xpath("//span[@class='text-success']"));
        Assert.assertEquals(result.getText(), "Yes", "Result should show 'Yes' when Yes radio is selected");
        
        // Select Impressive radio button
        impressiveRadio.click();
        WaitUtils.waitForTextToBePresentInElement(result, "Impressive");
        Assert.assertEquals(result.getText(), "Impressive", "Result should show 'Impressive' when Impressive radio is selected");
        
        logger.info("Radio button selection test passed");
    }
    
    @Test(priority = 4, description = "Test disabled radio button")
    public void testDisabledRadioButton() {
        getDriver().get("https://demoqa.com/radio-button");
        
        WebElement noRadioInput = getDriver().findElement(By.id("noRadio"));
        Assert.assertFalse(noRadioInput.isEnabled(), "No radio button should be disabled");
        
        logger.info("Disabled radio button test passed");
    }
}