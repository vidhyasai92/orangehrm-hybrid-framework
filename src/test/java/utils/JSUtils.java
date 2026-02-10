package utils;

import base.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtils {
    
    private static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) DriverFactory.getDriver();
    }
    
    public static void clickElement(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }
    
    public static void scrollToElement(WebElement element) {
        getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public static void scrollToBottom() {
        getJSExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    public static void scrollToTop() {
        getJSExecutor().executeScript("window.scrollTo(0, 0)");
    }
    
    public static void highlightElement(WebElement element) {
        getJSExecutor().executeScript("arguments[0].style.border='3px solid red'", element);
    }
    
    public static String getPageTitle() {
        return getJSExecutor().executeScript("return document.title;").toString();
    }
    
    public static void refreshPage() {
        getJSExecutor().executeScript("history.go(0)");
    }
}
