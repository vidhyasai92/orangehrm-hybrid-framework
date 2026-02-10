package pages;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.FrameUtils;

public class FramesPage {
    
    private WebDriver driver;
    
    @FindBy(id = "frame1")
    private WebElement frame1;
    
    @FindBy(id = "frame2")
    private WebElement frame2;
    
    @FindBy(id = "sampleHeading")
    private WebElement frameHeading;
    
    public FramesPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }
    
    public void switchToFrame1() {
        FrameUtils.switchToFrame("frame1");
    }
    
    public void switchToFrame2() {
        FrameUtils.switchToFrame("frame2");
    }
    
    public String getFrameHeadingText() {
        return frameHeading.getText();
    }
    
    public void switchToDefaultContent() {
        FrameUtils.switchToDefaultContent();
    }
}
