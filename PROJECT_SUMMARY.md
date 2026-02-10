# Project 3: OrangeHRM Hybrid Framework - Interview Summary

## Quick Overview
**Project Name**: OrangeHRM Hybrid Automation Framework  
**Framework Type**: Hybrid (POM + Data-driven + Config-driven)  
**Application**: OrangeHRM (HR Management System) + DemoQA (UI Concepts)  
**Duration**: 2-3 weeks  
**Team Size**: Individual project  
**Role**: Automation Test Engineer  

## What is a Hybrid Framework?
A Hybrid Framework combines multiple framework types:
- **POM (Page Object Model)**: For maintainability
- **Data-driven**: For testing with multiple data sets
- **Config-driven**: For environment configuration
- **Keyword-driven**: For reusable actions (via utilities)

## Tech Stack
- **Language**: Java 11
- **Automation Tool**: Selenium WebDriver 4.16.1
- **Test Framework**: TestNG 7.9.0
- **Build Tool**: Maven
- **Reporting**: ExtentReports 5.1.1
- **Logging**: Log4j2 2.22.1
- **Driver Management**: WebDriverManager
- **Data Handling**: Jackson (JSON), Apache POI (Excel)

## Project Architecture

### 3-Layer Architecture
1. **Base Layer**: DriverFactory, BaseTest, TestListener
2. **Page Layer**: Page Object classes with locators and actions
3. **Test Layer**: Test classes with test scenarios

### Key Components
- **config/**: Configuration loader for properties
- **pages/**: Page Object Model classes
- **tests/**: Test classes (orangehrm + uiconcepts)
- **utils/**: Reusable utility classes
- **base/**: Base setup and listener classes

## What Did We Automate?

### Suite 1: OrangeHRM Application
Real-world HR application automation:
- **Login Module**: Valid/invalid login, logout
- **Dashboard**: Navigation verification
- **PIM Module**: Employee management (expandable)
- **Leave Module**: Leave application (expandable)

### Suite 2: UI Concepts (Selenium Skills)
Comprehensive Selenium concepts:
- **Alerts**: Simple, Timer, Confirm, Prompt
- **Frames**: Single and nested iframe handling
- **Web Tables**: Read, add, delete table records
- **JavaScript Executor**: Scroll, click, highlight
- **Window Handling**: Multiple windows/tabs
- **Waits**: Explicit waits with WebDriverWait

## Key Features

### 1. Page Object Model (POM)
```java
public class LoginPage {
    @FindBy(name = "username")
    private WebElement usernameField;
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
```
**Why POM?**
- Separates test logic from page structure
- Easy maintenance when UI changes
- Reusable page methods

### 2. Explicit Waits (No Thread.sleep)
```java
public static void waitForElementVisible(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.visibilityOf(element));
}
```
**Why Explicit Waits?**
- Handles dynamic elements
- Reduces flakiness
- Faster than implicit waits

### 3. ExtentReports Integration
- HTML reports with test steps
- Screenshots on failure
- Execution time tracking
- Pass/Fail status with logs

### 4. Screenshot on Failure
```java
@Override
public void onTestFailure(ITestResult result) {
    String screenshot = ScreenshotUtils.captureScreenshot(testName);
    extentTest.addScreenCaptureFromPath(screenshot);
}
```

### 5. Configuration Management
```properties
browser=chrome
headless=false
baseUrlOrangeHRM=https://opensource-demo.orangehrmlive.com
explicitWaitSeconds=15
```
**Benefits**:
- Easy environment switching
- No code changes for different configs
- CI/CD ready

### 6. Logging with Log4j2
- Console and file logging
- Different log levels (INFO, ERROR, DEBUG)
- Helps in debugging failures

## Challenges Faced & Solutions

### Challenge 1: Dynamic Elements Not Loading
**Problem**: Elements were not found immediately after page load  
**Solution**: Implemented explicit waits (WebDriverWait) instead of Thread.sleep  
**Learning**: Explicit waits are more reliable for dynamic content

### Challenge 2: Stale Element Reference
**Problem**: Elements became stale after page refresh  
**Solution**: Re-initialized PageFactory elements and added retry logic  
**Learning**: Always re-locate elements after page changes

### Challenge 3: Screenshot Not Attaching to Report
**Problem**: Screenshots were captured but not showing in ExtentReport  
**Solution**: Used relative path instead of absolute path for screenshots  
**Learning**: Path handling is crucial for CI/CD compatibility

### Challenge 5: Ad Overlays Blocking Elements
**Problem**: DemoQA website ads were blocking button clicks (ElementClickInterceptedException)  
**Solution**: Implemented try-catch with JavaScript executor fallback in page objects  
**Learning**: Real-world websites have ads and overlays - JS executor is essential for handling them

## Framework Advantages

1. **Maintainability**: POM makes updates easy
2. **Reusability**: Utility classes reduce code duplication
3. **Scalability**: Easy to add new tests and pages
4. **Reporting**: Clear HTML reports with screenshots
5. **Configuration**: Easy environment switching
6. **Logging**: Detailed logs for debugging
7. **CI/CD Ready**: Headless mode and Maven support

## How to Run

### Local Execution
```bash
mvn clean test
```

### With Parameters
```bash
mvn clean test -Dbrowser=chrome -Dheadless=false
```

### From Eclipse
Right-click `testng.xml` > Run As > TestNG Suite

## Test Results
- **Total Tests**: 12 (3 OrangeHRM + 9 UI Concepts)
- **Pass Rate**: 100%
- **Execution Time**: ~1.5 minutes
- **Report**: HTML ExtentReport with screenshots

## Real-Time Scenarios Covered

1. **Login Validation**: Valid/invalid credentials
2. **Session Management**: Login and logout flow
3. **Alert Handling**: All types of JavaScript alerts
4. **Frame Switching**: Single and nested frames
5. **Table Operations**: Dynamic table manipulation
6. **Wait Strategies**: Handling dynamic elements
7. **Screenshot Capture**: Evidence for failures

## Interview Questions You Can Answer

### Q1: What framework did you use?
**A**: Hybrid framework combining POM, data-driven, and config-driven approaches.

### Q2: Why Hybrid Framework?
**A**: Combines best practices from multiple frameworks - POM for maintainability, data-driven for multiple test data, config-driven for environment management.

### Q3: How do you handle dynamic elements?
**A**: Using explicit waits (WebDriverWait) with ExpectedConditions like visibilityOf, elementToBeClickable.

### Q4: How do you handle alerts?
**A**: Using Alert interface - switchTo().alert(), then accept(), dismiss(), or sendKeys() for prompt alerts.

### Q5: How do you handle frames?
**A**: Using switchTo().frame() with index, name/id, or WebElement, then switchTo().defaultContent() to come back.

### Q6: How do you generate reports?
**A**: Using ExtentReports with TestListener - creates HTML reports with test steps, logs, and screenshots on failure.

### Q7: How do you capture screenshots?
**A**: Using TakesScreenshot interface in TestListener's onTestFailure method, then attach to ExtentReport.

### Q8: How do you manage test data?
**A**: Using config.properties for environment data, with support for JSON and Excel via Jackson and Apache POI.

### Q9: What design patterns did you use?
**A**: Page Object Model, Singleton (DriverFactory), Factory Pattern (browser initialization).

### Q10: How do you handle waits?
**A**: Explicit waits only via WaitUtils class - no Thread.sleep except in rare justified cases.

## GitHub Repository Structure
```
OrangeHRM-HybridFramework/
├── src/test/java/          # All Java code
├── src/test/resources/     # Config files
├── reports/                # Test reports (gitignored)
├── pom.xml                 # Maven dependencies
├── README.md               # Project documentation
└── .gitignore              # Ignore reports, logs, IDE files
```

## Key Takeaways

1. **Hybrid Framework** = POM + Data-driven + Config-driven
2. **Explicit Waits** are better than implicit waits or Thread.sleep
3. **POM** separates test logic from page structure
4. **ExtentReports** provides professional HTML reports
5. **TestListener** enables screenshot on failure
6. **Utility Classes** promote code reusability
7. **Configuration Management** enables easy environment switching
8. **Log4j2** helps in debugging and tracking execution

## Next Steps (Future Enhancements)

1. Add more OrangeHRM modules (PIM, Leave, Admin)
2. Implement data-driven testing with Excel
3. Add parallel execution support
4. Integrate with CI/CD (GitHub Actions)
5. Add Docker support
6. Implement retry mechanism for flaky tests
7. Add more UI concepts (drag-drop, file upload/download)

## Portfolio Value

This project demonstrates:
- ✅ Real-world application automation
- ✅ Complete Selenium concepts knowledge
- ✅ Framework design skills
- ✅ Best practices implementation
- ✅ Professional reporting
- ✅ Clean code structure
- ✅ GitHub repository management

Perfect for interviews at companies looking for experienced automation engineers!
