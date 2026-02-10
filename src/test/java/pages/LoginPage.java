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

public class LoginPage {
    
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;
    
    @FindBy(name = "username")
    private WebElement usernameField;
    
    @FindBy(name = "password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginHeader;
    
    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }
    
    public void enterUsername(String username) {
        WaitUtils.waitForElementVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        logger.info("Entered username: {}", username);
        TestListener.getExtentTest().log(Status.INFO, "Entered username: " + username);
    }
    
    public void enterPassword(String password) {
        WaitUtils.waitForElementVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        logger.info("Entered password");
        TestListener.getExtentTest().log(Status.INFO, "Entered password");
    }
    
    public void clickLoginButton() {
        WaitUtils.waitForElementClickable(loginButton);
        loginButton.click();
        logger.info("Clicked login button");
        TestListener.getExtentTest().log(Status.INFO, "Clicked login button");
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    public String getErrorMessage() {
        WaitUtils.waitForElementVisible(errorMessage);
        String error = errorMessage.getText();
        logger.info("Error message: {}", error);
        return error;
    }
    
    public boolean isLoginPageDisplayed() {
        try {
            WaitUtils.waitForElementVisible(loginHeader);
            return loginHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
