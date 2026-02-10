package tests.orangehrm;

import base.BaseTest;
import base.TestListener;
import com.aventstack.extentreports.Status;
import config.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    
    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        TestListener.getExtentTest().log(Status.INFO, "Starting valid login test");
        
        getDriver().get(ConfigLoader.getBaseUrlOrangeHRM());
        logger.info("Navigated to OrangeHRM login page");
        
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        
        loginPage.login(ConfigLoader.getDefaultUsername(), ConfigLoader.getDefaultPassword());
        
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after login");
        
        TestListener.getExtentTest().log(Status.PASS, "Valid login successful");
        logger.info("Valid login test passed");
    }
    
    @Test(priority = 2, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        TestListener.getExtentTest().log(Status.INFO, "Starting invalid login test");
        
        getDriver().get(ConfigLoader.getBaseUrlOrangeHRM());
        logger.info("Navigated to OrangeHRM login page");
        
        LoginPage loginPage = new LoginPage();
        loginPage.login("InvalidUser", "InvalidPass");
        
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Invalid credentials"), 
                         "Error message should contain 'Invalid credentials'");
        
        TestListener.getExtentTest().log(Status.PASS, "Invalid login test passed with error: " + errorMsg);
        logger.info("Invalid login test passed");
    }
    
    @Test(priority = 3, description = "Verify login and logout functionality")
    public void testLoginAndLogout() {
        TestListener.getExtentTest().log(Status.INFO, "Starting login and logout test");
        
        getDriver().get(ConfigLoader.getBaseUrlOrangeHRM());
        logger.info("Navigated to OrangeHRM login page");
        
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigLoader.getDefaultUsername(), ConfigLoader.getDefaultPassword());
        
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed");
        
        dashboardPage.logout();
        
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed after logout");
        
        TestListener.getExtentTest().log(Status.PASS, "Login and logout test passed");
        logger.info("Login and logout test passed");
    }
}
