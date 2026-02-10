package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    @BeforeMethod
    public void setUp() {
        logger.info("========== Test Started ==========");
        DriverFactory.initializeDriver();
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("========== Test Completed ==========");
        DriverFactory.quitDriver();
    }
    
    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
