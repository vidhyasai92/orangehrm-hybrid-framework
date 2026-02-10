package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "/config.properties";
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ConfigLoader.class.getResourceAsStream(CONFIG_FILE_PATH)) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in resources folder");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
        }
    }
    
    public static String getBaseUrlOrangeHRM() {
        return properties.getProperty("baseUrlOrangeHRM");
    }
    
    public static String getBaseUrlDemoQA() {
        return properties.getProperty("baseUrlDemoQA");
    }
    
    public static String getBrowser() {
        String browser = System.getProperty("browser");
        if (browser != null && !browser.trim().isEmpty()) {
            return browser;
        }
        String propBrowser = properties.getProperty("browser", "chrome");
        if (propBrowser == null || propBrowser.trim().isEmpty()) {
            return "chrome"; // default fallback
        }
        return propBrowser;
    }
    
    public static boolean isHeadless() {
        String headless = System.getProperty("headless");
        if (headless != null) {
            return Boolean.parseBoolean(headless);
        }
        String propHeadless = properties.getProperty("headless", "false");
        return Boolean.parseBoolean(propHeadless);
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWaitSeconds"));
    }
    
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicitWaitSeconds"));
    }
    
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("pageLoadTimeout"));
    }
    
    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(properties.getProperty("screenshotOnFailure"));
    }
    
    public static int getRetryCount() {
        return Integer.parseInt(properties.getProperty("retryCount"));
    }
    
    public static String getDownloadPath() {
        return properties.getProperty("downloadPath");
    }
    
    public static String getDefaultUsername() {
        return properties.getProperty("defaultUsername");
    }
    
    public static String getDefaultPassword() {
        return properties.getProperty("defaultPassword");
    }
}
