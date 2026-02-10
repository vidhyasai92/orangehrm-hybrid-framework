# How to Run OrangeHRM Hybrid Framework in Eclipse

## Step 1: Import Project into Eclipse

1. Open Eclipse
2. Go to **File > Import**
3. Select **Maven > Existing Maven Projects**
4. Click **Next**
5. Browse to: `eclipse-workspace/java-practice/OrangeHRM-HybridFramework`
6. Click **Finish**
7. Wait for Maven to download dependencies (check bottom-right progress bar)

## Step 2: Verify Project Setup

1. Right-click on project name
2. Select **Maven > Update Project**
3. Check **Force Update of Snapshots/Releases**
4. Click **OK**

## Step 3: Run Tests

### Option A: Run Complete Test Suite
1. Navigate to `src/test/resources/testng.xml`
2. Right-click on the file
3. Select **Run As > TestNG Suite**

### Option B: Run Individual Test Class
1. Navigate to any test class:
   - `tests/orangehrm/LoginTests.java`
   - `tests/uiconcepts/AlertsTests.java`
   - `tests/uiconcepts/FramesTests.java`
   - `tests/uiconcepts/WebTablesTests.java`
2. Right-click on the test class
3. Select **Run As > TestNG Test**

### Option C: Run Single Test Method
1. Open any test class
2. Right-click on a specific `@Test` method
3. Select **Run As > TestNG Test**

## Step 4: View Results

### Console Output
- Check Eclipse Console tab for logs
- You'll see:
  - Test execution logs
  - Browser actions
  - Pass/Fail status

### ExtentReports
1. After test execution completes
2. Navigate to `reports/` folder in Project Explorer
3. Find the latest `ExtentReport_<timestamp>.html`
4. Right-click > **Open With > Web Browser**
5. View detailed HTML report with:
   - Test summary
   - Pass/Fail status
   - Test steps
   - Screenshots (on failure)
   - Execution time

### Screenshots (on failure)
- Navigate to `reports/screenshots/`
- Failed test screenshots are saved here

### Logs
- Navigate to `logs/automation.log`
- View detailed execution logs

## Step 5: Change Configuration

### Change Browser
1. Open `src/test/resources/config.properties`
2. Change: `browser=chrome` to `browser=firefox` or `browser=edge`
3. Save and run tests

### Enable Headless Mode
1. Open `src/test/resources/config.properties`
2. Change: `headless=false` to `headless=true`
3. Save and run tests

### Change Test Data
1. Open `src/test/resources/config.properties`
2. Modify:
   - `defaultUsername=Admin`
   - `defaultPassword=admin123`
3. Save and run tests

## Step 6: Run Specific Test Suite

Edit `src/test/resources/testng.xml`:

### Run Only OrangeHRM Tests
```xml
<test name="All Tests" enabled="false">
<test name="OrangeHRM Tests" enabled="true">
<test name="UI Concepts Tests" enabled="false">
```

### Run Only UI Concepts Tests
```xml
<test name="All Tests" enabled="false">
<test name="OrangeHRM Tests" enabled="false">
<test name="UI Concepts Tests" enabled="true">
```

### Run All Tests
```xml
<test name="All Tests" enabled="true">
<test name="OrangeHRM Tests" enabled="false">
<test name="UI Concepts Tests" enabled="false">
```

## Troubleshooting

### Issue: Tests are skipping
**Solution**: Check console for configuration errors. Ensure @BeforeMethod is executing properly.

### Issue: Browser not launching
**Solution**: 
- Check if Chrome/Firefox is installed
- WebDriverManager will auto-download drivers
- Check internet connection

### Issue: Element not found
**Solution**: 
- Website structure may have changed
- Check if explicit waits are working
- Increase wait time in config.properties

### Issue: ExtentReport not generating
**Solution**: 
- Check if `reports/` folder exists
- Ensure TestListener is attached to tests
- Check console for errors

### Issue: Maven dependencies not downloading
**Solution**:
- Right-click project > Maven > Update Project
- Check internet connection
- Delete `.m2/repository` folder and retry

## Command Line Alternative

Open Terminal in Eclipse:
1. Right-click on project
2. Select **Show In > Terminal**
3. Run: `mvn clean test`

## Tips

1. **First Run**: First test run will be slower as WebDriverManager downloads browser drivers
2. **Parallel Execution**: Currently disabled. Can be enabled in testng.xml
3. **Debug Mode**: Right-click test > Debug As > TestNG Test
4. **View Logs**: Console shows real-time logs during execution
5. **Clean Reports**: Delete `reports/` folder before fresh run

## Expected Output

```
[RemoteTestNG] detected TestNG version 7.9.0
========== Test Started ==========
Initializing chrome driver in normal mode
Driver initialized successfully
Navigated to OrangeHRM login page
Entered username: Admin
Entered password
Clicked login button
Dashboard displayed: true
✅ Valid login test passed
========== Test Completed ==========
Browser closed successfully

PASSED: testValidLogin
PASSED: testInvalidLogin
PASSED: testLoginAndLogout

===============================================
OrangeHRM Test Suite
Total tests run: 3, Passes: 3, Failures: 0, Skips: 0
===============================================
```

## Next Steps

1. Run the tests and verify they pass
2. Check the ExtentReport
3. Explore the code structure
4. Add more test scenarios
5. Push to GitHub when ready
