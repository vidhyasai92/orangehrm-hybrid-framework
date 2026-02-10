# OrangeHRM Hybrid Automation Framework

A comprehensive Java + Selenium Hybrid Framework demonstrating real-world automation with OrangeHRM application and complete Selenium concepts coverage.

## Framework Type: Hybrid
- **POM (Page Object Model)**: Maintainable page classes with reusable methods
- **Data-driven**: Configuration-based execution with external data support
- **Config-driven**: Environment settings via properties file
- **Modular**: Reusable utility classes for common operations

## Tech Stack
- **Language**: Java 11
- **Automation**: Selenium WebDriver 4.16.1
- **Test Runner**: TestNG 7.9.0
- **Build Tool**: Maven
- **Driver Manager**: WebDriverManager 5.6.3
- **Reporting**: ExtentReports 5.1.1
- **Logging**: Log4j2 2.22.1
- **Data Handling**: Jackson (JSON), Apache POI (Excel)
- **Utilities**: Commons IO

## Project Structure
```
OrangeHRM-HybridFramework/
├── src/test/java/
│   ├── base/                    # Base classes
│   │   ├── BaseTest.java        # Test setup/teardown
│   │   ├── DriverFactory.java   # WebDriver initialization
│   │   └── TestListener.java    # ExtentReports + screenshots
│   ├── config/                  # Configuration loader
│   │   └── ConfigLoader.java
│   ├── pages/                   # Page Object classes
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   ├── AlertsPage.java
│   │   ├── FramesPage.java
│   │   └── WebTablesPage.java
│   ├── tests/
│   │   ├── orangehrm/          # OrangeHRM functional tests
│   │   │   └── LoginTests.java
│   │   └── uiconcepts/         # Selenium concepts tests
│   │       ├── AlertsTests.java
│   │       ├── FramesTests.java
│   │       └── WebTablesTests.java
│   └── utils/                   # Utility classes
│       ├── WaitUtils.java
│       ├── JSUtils.java
│       ├── WindowUtils.java
│       ├── FrameUtils.java
│       ├── ElementUtils.java
│       └── ScreenshotUtils.java
├── src/test/resources/
│   ├── config.properties        # Configuration settings
│   ├── log4j2.xml              # Logging configuration
│   └── testng.xml              # TestNG suite configuration
├── reports/                     # Test reports (auto-generated)
└── pom.xml                     # Maven dependencies
```

## Features

### Two Test Suites

#### 1. OrangeHRM Suite (Real Application)
- Login with valid/invalid credentials
- Logout functionality
- Dashboard navigation
- PIM, Leave, Admin modules (expandable)

#### 2. UI Concepts Suite (Selenium Concepts)
- **Alerts**: Simple, Timer, Confirm, Prompt alerts
- **Frames**: Single and nested iframe handling
- **Web Tables**: Add, edit, delete table records
- **Actions Class**: Hover, drag-drop, right-click (expandable)
- **JavaScript Executor**: Scroll, click, highlight (expandable)
- **Windows/Tabs**: Multiple window handling (expandable)

### Framework Capabilities
- **Explicit Waits**: WebDriverWait for dynamic elements
- **Screenshot on Failure**: Auto-capture and attach to report
- **ExtentReports**: HTML reports with test steps and logs
- **Log4j2**: Console and file logging
- **Browser Support**: Chrome, Firefox, Edge
- **Headless Mode**: CI/CD ready
- **ThreadLocal Driver**: Parallel execution support

## Configuration

Edit `src/test/resources/config.properties`:

```properties
# Application URLs
baseUrlOrangeHRM=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
baseUrlDemoQA=https://demoqa.com

# Browser Configuration
browser=chrome
headless=false

# Wait Configuration (in seconds)
implicitWaitSeconds=10
explicitWaitSeconds=15
pageLoadTimeout=30

# Screenshot Configuration
screenshotOnFailure=true

# Test Data
defaultUsername=Admin
defaultPassword=admin123
```

## How to Run

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser

### Run from Command Line

**Run all tests:**
```bash
mvn clean test
```

**Run with specific browser:**
```bash
mvn clean test -Dbrowser=chrome
```

**Run in headless mode:**
```bash
mvn clean test -Dheadless=true
```

**Run specific suite (edit testng.xml to enable/disable tests):**
- Enable/disable tests by changing `enabled="true"` or `enabled="false"` in testng.xml

### Run from Eclipse

1. Right-click on `testng.xml`
2. Select **Run As > TestNG Suite**

Or run individual test classes:
1. Right-click on test class (e.g., `LoginTests.java`)
2. Select **Run As > TestNG Test**

## Reports

After test execution:
- **ExtentReports**: `reports/ExtentReport_<timestamp>.html`
- **Screenshots**: `reports/screenshots/` (on failure)
- **Logs**: `logs/automation.log`

Open the HTML report in any browser to view:
- Test execution summary
- Pass/Fail status
- Test steps with logs
- Screenshots for failed tests
- Execution time

## Key Design Patterns

### Page Object Model (POM)
```java
public class LoginPage {
    @FindBy(name = "username")
    private WebElement usernameField;
    
    public void enterUsername(String username) {
        WaitUtils.waitForElementVisible(usernameField);
        usernameField.sendKeys(username);
    }
}
```

### Explicit Waits
```java
public static void waitForElementVisible(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.visibilityOf(element));
}
```

### Screenshot on Failure
```java
@Override
public void onTestFailure(ITestResult result) {
    String screenshotPath = ScreenshotUtils.captureScreenshot(testName);
    extentTest.addScreenCaptureFromPath(screenshotPath);
}
```

## Selenium Concepts Covered

✅ Alerts (Simple, Timer, Confirm, Prompt)  
✅ Frames/iFrames (Single, Nested)  
✅ Web Tables (Read, Add, Delete)  
✅ Explicit Waits (WebDriverWait)  
✅ Page Object Model  
✅ JavaScript Executor  
✅ Window Handling  
✅ Screenshot Capture  
✅ ExtentReports Integration  
✅ Log4j2 Logging  
✅ Configuration Management  

## Future Enhancements
- Data-driven testing with Excel/JSON
- Parallel test execution
- CI/CD integration (GitHub Actions)
- Docker support
- More UI concepts (drag-drop, file upload/download, date pickers)
- API testing integration

## Author
Vidhya Sai  
GitHub: [@vidhyasai92](https://github.com/vidhyasai92)

## License
This project is for educational and portfolio purposes.
