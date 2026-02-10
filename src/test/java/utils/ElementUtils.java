package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementUtils {
    
    public static void selectByVisibleText(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }
    
    public static void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }
    
    public static void selectByIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    
    public static String getSelectedOption(WebElement dropdown) {
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }
    
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }
}
