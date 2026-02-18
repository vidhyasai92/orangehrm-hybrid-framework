package utils;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Set;

public class WindowUtils {
    
    public static void switchToWindow(int index) {
        WebDriver driver = DriverFactory.getDriver();
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(index));
    }
    
    public static void switchToWindowByTitle(String title) {
        WebDriver driver = DriverFactory.getDriver();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(title)) {
                break;
            }
        }
    }
    
    public static void closeCurrentWindow() {
        DriverFactory.getDriver().close();
    }
    
    public static void switchToParentWindow() {
        switchToWindow(0);
    }
    
    public static int getWindowCount() {
        return DriverFactory.getDriver().getWindowHandles().size();
    }
    
    public static void switchToNewWindow(String originalWindow) {
        WebDriver driver = DriverFactory.getDriver();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}
