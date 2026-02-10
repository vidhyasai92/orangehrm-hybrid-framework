package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class DriverFactory {
    
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    public static void initializeDriver() {
        String browser = ConfigLoader.getBrowser().toLowerCase();
        boolean headless = ConfigLoader.isHeadless();
        
        logger.info("Initializing {} driver in {} mode", browser, headless ? "headless" : "normal");
        
        WebDriver webDriver = null;
        
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--start-maximized");
                webDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                webDriver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigLoader.getImplicitWait()));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigLoader.getPageLoadTimeout()));
        webDriver.manage().window().maximize();
        
        driver.set(webDriver);
        logger.info("Driver initialized successfully");
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Closing browser");
            driver.get().quit();
            driver.remove();
        }
    }
}
