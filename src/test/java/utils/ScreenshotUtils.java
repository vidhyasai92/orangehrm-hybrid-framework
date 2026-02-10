package utils;

import base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    
    public static String captureScreenshot(String testName) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver == null) {
                return null;
            }
            
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String destination = "reports/screenshots/" + fileName;
            
            File destFile = new File(destination);
            FileUtils.copyFile(source, destFile);
            
            return destination;
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
