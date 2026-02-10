package pages;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.JSUtils;
import utils.WaitUtils;

import java.util.List;

public class WebTablesPage {
    
    private WebDriver driver;
    
    @FindBy(id = "addNewRecordButton")
    private WebElement addButton;
    
    @FindBy(xpath = "//div[@class='rt-tbody']//div[@class='rt-tr-group']")
    private List<WebElement> tableRows;
    
    @FindBy(id = "firstName")
    private WebElement firstNameField;
    
    @FindBy(id = "lastName")
    private WebElement lastNameField;
    
    @FindBy(id = "userEmail")
    private WebElement emailField;
    
    @FindBy(id = "age")
    private WebElement ageField;
    
    @FindBy(id = "salary")
    private WebElement salaryField;
    
    @FindBy(id = "department")
    private WebElement departmentField;
    
    @FindBy(id = "submit")
    private WebElement submitButton;
    
    @FindBy(xpath = "//span[@title='Edit']")
    private List<WebElement> editButtons;
    
    @FindBy(xpath = "//span[@title='Delete']")
    private List<WebElement> deleteButtons;
    
    public WebTablesPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }
    
    public void clickAddButton() {
        WaitUtils.waitForElementClickable(addButton);
        addButton.click();
    }
    
    public void fillRegistrationForm(String firstName, String lastName, String email, 
                                     String age, String salary, String department) {
        WaitUtils.waitForElementVisible(firstNameField);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        ageField.sendKeys(age);
        salaryField.sendKeys(salary);
        departmentField.sendKeys(department);
    }
    
    public void clickSubmit() {
        WaitUtils.waitForElementClickable(submitButton);
        submitButton.click();
    }
    
    public int getTableRowCount() {
        // Count only non-empty rows (rows with actual data)
        int count = 0;
        for (WebElement row : tableRows) {
            String rowText = row.getText().trim();
            if (!rowText.isEmpty()) {
                count++;
            }
        }
        return count;
    }
    
    public void clickEditButton(int index) {
        WaitUtils.waitForElementClickable(editButtons.get(index));
        editButtons.get(index).click();
    }
    
    public void clickDeleteButton(int index) {
        WaitUtils.waitForElementClickable(deleteButtons.get(index));
        try {
            deleteButtons.get(index).click();
        } catch (Exception e) {
            // If ad blocks click, use JavaScript executor
            JSUtils.clickElement(deleteButtons.get(index));
        }
    }
}
