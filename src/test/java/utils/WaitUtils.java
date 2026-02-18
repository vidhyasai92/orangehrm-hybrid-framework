package utils;

import base.DriverFactory;
import config.ConfigLoader;
import org.openqa.selenium.By;
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
    
    public static WebElement waitForElementToBeVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public static WebElement waitForElementToBeClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static WebElement waitForElementToBePresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
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
    
    public static void waitForTextToBePresentInElement(WebElement element, String text) {
        getWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    
    public static void waitForNumberOfWindowsToBe(int numberOfWindows) {
        getWait().until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }
}
