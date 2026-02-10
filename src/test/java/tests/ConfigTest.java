package tests;

import org.testng.annotations.Test;
import config.ConfigLoader;

public class ConfigTest {
    
    @Test
    public void testConfigLoading() {
        System.out.println("Browser: " + ConfigLoader.getBrowser());
        System.out.println("Base URL: " + ConfigLoader.getBaseUrlOrangeHRM());
        System.out.println("Username: " + ConfigLoader.getDefaultUsername());
        System.out.println("Password: " + ConfigLoader.getDefaultPassword());
        System.out.println("Headless: " + ConfigLoader.isHeadless());
        System.out.println("Config loaded successfully!");
    }
}
