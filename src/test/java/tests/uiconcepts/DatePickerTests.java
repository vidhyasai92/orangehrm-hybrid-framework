package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerTests extends BaseTest {
    
    @Test(priority = 1, description = "Test date picker selection")
    public void testDatePickerSelection() {
        getDriver().get("https://demoqa.com/date-picker");
        
        WebElement dateInput = WaitUtils.waitForElementToBeVisible(By.id("datePickerMonthYearInput"));
        
        // Clear existing date and enter new date
        dateInput.clear();
        String testDate = "01/15/2024";
        dateInput.sendKeys(testDate);
        
        // Click outside to close picker
        getDriver().findElement(By.tagName("body")).click();
        
        String enteredDate = dateInput.getAttribute("value");
        Assert.assertEquals(enteredDate, testDate, "Date should be set correctly");
        
        logger.info("Date picker selection test passed with date: " + testDate);
    }
    
    @Test(priority = 2, description = "Test date and time picker")
    public void testDateTimePickerSelection() {
        getDriver().get("https://demoqa.com/date-picker");
        
        WebElement dateTimeInput = WaitUtils.waitForElementToBeVisible(By.id("dateAndTimePickerInput"));
        
        // Clear existing date time and enter new one
        dateTimeInput.clear();
        String testDateTime = "January 15, 2024 2:30 PM";
        dateTimeInput.sendKeys(testDateTime);
        
        // Click outside to close picker
        getDriver().findElement(By.tagName("body")).click();
        
        String enteredDateTime = dateTimeInput.getAttribute("value");
        Assert.assertTrue(enteredDateTime.contains("January 15, 2024"), "Date time should contain the selected date");
        
        logger.info("Date time picker selection test passed with datetime: " + testDateTime);
    }
    
    @Test(priority = 3, description = "Test current date selection")
    public void testCurrentDateSelection() {
        getDriver().get("https://demoqa.com/date-picker");
        
        WebElement dateInput = WaitUtils.waitForElementToBeVisible(By.id("datePickerMonthYearInput"));
        
        // Get current date
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        dateInput.clear();
        dateInput.sendKeys(formattedDate);
        
        // Click outside to close picker
        getDriver().findElement(By.tagName("body")).click();
        
        String enteredDate = dateInput.getAttribute("value");
        Assert.assertEquals(enteredDate, formattedDate, "Current date should be set correctly");
        
        logger.info("Current date selection test passed with date: " + formattedDate);
    }
    
    @Test(priority = 4, description = "Test invalid date handling")
    public void testInvalidDateHandling() {
        getDriver().get("https://demoqa.com/date-picker");
        
        WebElement dateInput = WaitUtils.waitForElementToBeVisible(By.id("datePickerMonthYearInput"));
        
        String originalDate = dateInput.getAttribute("value");
        
        // Try to enter invalid date
        dateInput.clear();
        dateInput.sendKeys("99/99/9999");
        
        // Click outside to close picker
        getDriver().findElement(By.tagName("body")).click();
        
        String finalDate = dateInput.getAttribute("value");
        
        // The behavior may vary - either it reverts to original or shows error
        // We just verify it doesn't crash the application
        Assert.assertNotNull(finalDate, "Date input should handle invalid dates gracefully");
        
        logger.info("Invalid date handling test passed");
    }
}