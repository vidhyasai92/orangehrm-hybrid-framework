package utils;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameUtils {
    
    public static void switchToFrame(int index) {
        DriverFactory.getDriver().switchTo().frame(index);
    }
    
    public static void switchToFrame(String nameOrId) {
        DriverFactory.getDriver().switchTo().frame(nameOrId);
    }
    
    public static void switchToFrame(WebElement frameElement) {
        DriverFactory.getDriver().switchTo().frame(frameElement);
    }
    
    public static void switchToParentFrame() {
        DriverFactory.getDriver().switchTo().parentFrame();
    }
    
    public static void switchToDefaultContent() {
        DriverFactory.getDriver().switchTo().defaultContent();
    }
}
