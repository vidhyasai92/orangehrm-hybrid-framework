package pages;

import base.DriverFactory;
import base.TestListener;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class DashboardPage {
    
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);
    private WebDriver driver;
    
    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;
    
    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement userDropdown;
    
    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;
    
    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;
    
    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;
    
    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenu;
    
    public DashboardPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }
    
    public boolean isDashboardDisplayed() {
        try {
            WaitUtils.waitForElementVisible(dashboardHeader);
            boolean displayed = dashboardHeader.isDisplayed();
            logger.info("Dashboard displayed: {}", displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Dashboard not displayed", e);
            return false;
        }
    }
    
    public String getDashboardTitle() {
        WaitUtils.waitForElementVisible(dashboardHeader);
        String title = dashboardHeader.getText();
        logger.info("Dashboard title: {}", title);
        return title;
    }
    
    public void logout() {
        WaitUtils.waitForElementClickable(userDropdown);
        userDropdown.click();
        logger.info("Clicked user dropdown");
        TestListener.getExtentTest().log(Status.INFO, "Clicked user dropdown");
        
        WaitUtils.waitForElementClickable(logoutLink);
        logoutLink.click();
        logger.info("Clicked logout");
        TestListener.getExtentTest().log(Status.INFO, "Clicked logout");
    }
    
    public void navigateToPIM() {
        WaitUtils.waitForElementClickable(pimMenu);
        pimMenu.click();
        logger.info("Navigated to PIM");
        TestListener.getExtentTest().log(Status.INFO, "Navigated to PIM");
    }
    
    public void navigateToLeave() {
        WaitUtils.waitForElementClickable(leaveMenu);
        leaveMenu.click();
        logger.info("Navigated to Leave");
        TestListener.getExtentTest().log(Status.INFO, "Navigated to Leave");
    }
    
    public void navigateToAdmin() {
        WaitUtils.waitForElementClickable(adminMenu);
        adminMenu.click();
        logger.info("Navigated to Admin");
        TestListener.getExtentTest().log(Status.INFO, "Navigated to Admin");
    }
}
