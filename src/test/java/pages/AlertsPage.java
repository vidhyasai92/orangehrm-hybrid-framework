package pages;

import base.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JSUtils;

import java.time.Duration;

public class AlertsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "alertButton")
    private WebElement simpleAlertButton;
    
    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;
    
    @FindBy(id = "confirmButton")
    private WebElement confirmAlertButton;
    
    @FindBy(id = "promtButton")
    private WebElement promptAlertButton;
    
    @FindBy(id = "confirmResult")
    private WebElement confirmResult;
    
    @FindBy(id = "promptResult")
    private WebElement promptResult;
    
    public AlertsPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void clickSimpleAlert() {
        try {
            simpleAlertButton.click();
        } catch (Exception e) {
            // If ad blocks click, use JavaScript executor
            JSUtils.clickElement(simpleAlertButton);
        }
    }
    
    public void clickTimerAlert() {
        try {
            timerAlertButton.click();
        } catch (Exception e) {
            // If ad blocks click, use JavaScript executor
            JSUtils.clickElement(timerAlertButton);
        }
    }
    
    public void clickConfirmAlert() {
        try {
            confirmAlertButton.click();
        } catch (Exception e) {
            // If ad blocks click, use JavaScript executor
            JSUtils.clickElement(confirmAlertButton);
        }
    }
    
    public void clickPromptAlert() {
        try {
            promptAlertButton.click();
        } catch (Exception e) {
            // If ad blocks click, use JavaScript executor
            JSUtils.clickElement(promptAlertButton);
        }
    }
    
    public String acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
    
    public String dismissAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.dismiss();
        return alertText;
    }
    
    public String sendKeysToAlert(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        return text;
    }
    
    public String getConfirmResult() {
        return confirmResult.getText();
    }
    
    public String getPromptResult() {
        return promptResult.getText();
    }
}
