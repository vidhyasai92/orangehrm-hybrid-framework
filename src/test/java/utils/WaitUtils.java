package utils;

import base.DriverFactory;
import config.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    
    private static WebDriverWait getWait() {
        WebDriver driver = DriverFactory.getDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getExplicitWait()));
    }
    
    public static void waitForElementVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }
    
    public static void waitForElementClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static void waitForElementInvisible(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }
    
    public static void waitForUrlContains(String urlFragment) {
        getWait().until(ExpectedConditions.urlContains(urlFragment));
    }
    
    public static void waitForTitleContains(String title) {
        getWait().until(ExpectedConditions.titleContains(title));
    }
}
