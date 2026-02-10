package tests.uiconcepts;

import base.BaseTest;
import base.TestListener;
import com.aventstack.extentreports.Status;
import config.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WebTablesPage;

public class WebTablesTests extends BaseTest {
    
    private WebTablesPage webTablesPage;
    
    @BeforeMethod
    public void navigateToWebTablesPage() {
        getDriver().get(ConfigLoader.getBaseUrlDemoQA() + "/webtables");
        webTablesPage = new WebTablesPage();
        logger.info("Navigated to Web Tables page");
    }
    
    @Test(priority = 1, description = "Verify adding new record to table")
    public void testAddRecord() {
        TestListener.getExtentTest().log(Status.INFO, "Testing add record to table");
        
        int initialRowCount = webTablesPage.getTableRowCount();
        logger.info("Initial row count: {}", initialRowCount);
        
        webTablesPage.clickAddButton();
        webTablesPage.fillRegistrationForm("John", "Doe", "john@test.com", "30", "50000", "QA");
        webTablesPage.clickSubmit();
        
        // Wait a moment for DOM to update
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        int finalRowCount = webTablesPage.getTableRowCount();
        logger.info("Final row count: {}", finalRowCount);
        
        Assert.assertEquals(finalRowCount, initialRowCount + 1, 
            "Row count should increase by 1 after adding record");
        
        TestListener.getExtentTest().log(Status.PASS, "Record added successfully");
        logger.info("Add record test passed");
    }
    
    @Test(priority = 2, description = "Verify deleting record from table")
    public void testDeleteRecord() {
        TestListener.getExtentTest().log(Status.INFO, "Testing delete record from table");
        
        int initialRowCount = webTablesPage.getTableRowCount();
        logger.info("Initial row count: {}", initialRowCount);
        
        if (initialRowCount > 0) {
            webTablesPage.clickDeleteButton(0);
            
            // Wait a moment for DOM to update
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            int finalRowCount = webTablesPage.getTableRowCount();
            logger.info("Final row count: {}", finalRowCount);
            
            Assert.assertEquals(finalRowCount, initialRowCount - 1, 
                "Row count should decrease by 1 after deletion");
            TestListener.getExtentTest().log(Status.PASS, "Record deleted successfully");
        } else {
            TestListener.getExtentTest().log(Status.SKIP, "No records to delete");
            Assert.fail("No records available to test deletion");
        }
        
        logger.info("Delete record test completed");
    }
}
