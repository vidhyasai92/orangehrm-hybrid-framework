# OrangeHRM Hybrid Automation Framework

A comprehensive Selenium WebDriver automation framework built with Java, TestNG, and Maven. This framework demonstrates both real-world application testing (OrangeHRM) and complete Selenium WebDriver concepts coverage.

## Framework Architecture

**Hybrid Framework** = Page Object Model + Data-Driven + Configuration-Driven + Reusable Utilities

## Tech Stack

- **Language**: Java 11
- **Automation Tool**: Selenium WebDriver 4.16.1
- **Test Framework**: TestNG 7.9.0
- **Build Tool**: Maven
- **Driver Management**: WebDriverManager
- **Reporting**: ExtentReports 5.1.1
- **Logging**: Log4j2
- **Data Handling**: Apache POI (Excel), Jackson (JSON)

## Project Structure

```
src/test/java/
├── base/                 # Base classes and driver management
├── config/               # Configuration management
├── pages/                # Page Object Model classes
├── tests/
│   ├── orangehrm/        # Real application tests
│   └── uiconcepts/       # Selenium concepts demonstration
├── utils/                # Reusable utility classes
└── data/                 # Test data providers

src/test/resources/
├── config.properties     # Application configuration
├── log4j2.xml           # Logging configuration
├── testng.xml           # TestNG suite configuration
└── testdata/            # Test data files
```

## Test Suites

### 1. OrangeHRM Suite
Tests real-world scenarios on OrangeHRM demo application:
- Login/Logout functionality
- User authentication validation
- Dashboard navigation

### 2. UI Concepts Suite
Comprehensive Selenium WebDriver concepts:
- **Alerts**: Simple, Confirmation, Prompt alerts
- **Frames**: Single and nested iframe handling
- **Web Tables**: Data extraction and validation
- **Actions Class**: Hover, Drag-drop, Right-click, Double-click
- **JavaScript Executor**: Element interaction, scrolling, page manipulation
- **Windows/Tabs**: Multi-window handling and switching
- **Form Elements**: Checkboxes, Radio buttons, Dropdowns
- **File Operations**: Upload and Download functionality
- **Date Picker**: Date selection and validation
- **Sliders/Progress**: Interactive element handling

## Configuration

The framework supports multiple execution modes through `config.properties`:

```properties
# Application URLs
baseUrlOrangeHRM=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
baseUrlDemoQA=https://demoqa.com

# Browser Configuration
browser=chrome
headless=false

# Wait Configuration
implicitWaitSeconds=10
explicitWaitSeconds=15

# Test Data
defaultUsername=Admin
defaultPassword=admin123
```

## Running Tests

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Execution Commands

```bash
# Run all tests
mvn clean test

# Run specific suite
mvn clean test -Dtest=tests.orangehrm.*
mvn clean test -Dtest=tests.uiconcepts.*

# Run with different browser
mvn clean test -Dbrowser=firefox

# Run in headless mode
mvn clean test -Dheadless=true
```

### Eclipse Execution
1. Import project as Maven project
2. Right-click on `testng.xml` → Run As → TestNG Suite
3. Or run individual test classes directly

## Reports

- **ExtentReports**: Generated in `reports/extent-report.html`
- **Screenshots**: Captured on test failures in `reports/screenshots/`
- **Logs**: Detailed execution logs in `logs/` directory

## Key Features

- **Cross-browser Support**: Chrome, Firefox, Edge
- **Headless Execution**: For CI/CD pipelines
- **Automatic Screenshots**: On test failures
- **Retry Mechanism**: Configurable test retry on failure
- **Parallel Execution**: TestNG parallel execution support
- **Data-Driven**: Excel and JSON data support
- **Comprehensive Logging**: Log4j2 integration
- **CI/CD Ready**: Maven Surefire plugin configuration

## Framework Highlights

- **Robust Wait Strategy**: Explicit waits with custom utility methods
- **Modular Design**: Separate utilities for different functionalities
- **Clean Code**: Following Java best practices and naming conventions
- **Scalable Architecture**: Easy to extend for new test scenarios
- **Professional Reporting**: Detailed test execution reports with screenshots

## Author

Built as part of automation testing portfolio demonstrating advanced Selenium WebDriver concepts and real-world application testing scenarios.