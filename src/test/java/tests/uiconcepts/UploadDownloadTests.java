package tests.uiconcepts;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadDownloadTests extends BaseTest {
    
    @Test(priority = 1, description = "Test file upload functionality")
    public void testFileUpload() throws IOException {
        getDriver().get("https://demoqa.com/upload-download");
        
        // Create a test file to upload
        String fileName = "test-upload.txt";
        String fileContent = "This is a test file for upload functionality";
        Path testFile = Paths.get(System.getProperty("user.dir"), fileName);
        Files.write(testFile, fileContent.getBytes());
        
        try {
            WebElement uploadInput = WaitUtils.waitForElementToBePresent(By.id("uploadFile"));
            uploadInput.sendKeys(testFile.toAbsolutePath().toString());
            
            // Verify file path is displayed
            WebElement uploadedFilePath = WaitUtils.waitForElementToBeVisible(By.id("uploadedFilePath"));
            String displayedPath = uploadedFilePath.getText();
            Assert.assertTrue(displayedPath.contains(fileName), "Uploaded file path should contain the file name");
            
            logger.info("File upload test passed with file: " + fileName);
        } finally {
            // Clean up test file
            Files.deleteIfExists(testFile);
        }
    }
    
    @Test(priority = 2, description = "Test file download functionality")
    public void testFileDownload() throws InterruptedException {
        getDriver().get("https://demoqa.com/upload-download");
        
        // Set download directory
        String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";
        File downloadDirectory = new File(downloadDir);
        if (!downloadDirectory.exists()) {
            downloadDirectory.mkdirs();
        }
        
        WebElement downloadButton = WaitUtils.waitForElementToBeClickable(By.id("downloadButton"));
        downloadButton.click();
        
        // Wait for download to complete (simple wait approach)
        Thread.sleep(3000);
        
        // Verify file was downloaded
        File[] downloadedFiles = downloadDirectory.listFiles((dir, name) -> name.contains("sampleFile"));
        Assert.assertTrue(downloadedFiles != null && downloadedFiles.length > 0, 
                         "Downloaded file should exist in downloads directory");
        
        // Clean up downloaded file
        if (downloadedFiles != null) {
            for (File file : downloadedFiles) {
                file.delete();
            }
        }
        
        logger.info("File download test passed");
    }
    
    @Test(priority = 3, description = "Test upload with invalid file type")
    public void testInvalidFileUpload() throws IOException {
        getDriver().get("https://demoqa.com/upload-download");
        
        // Create a test file with different extension
        String fileName = "test-upload.exe";
        Path testFile = Paths.get(System.getProperty("user.dir"), fileName);
        Files.write(testFile, "test content".getBytes());
        
        try {
            WebElement uploadInput = WaitUtils.waitForElementToBePresent(By.id("uploadFile"));
            uploadInput.sendKeys(testFile.toAbsolutePath().toString());
            
            // For this demo site, it accepts any file type, so we just verify the upload works
            WebElement uploadedFilePath = WaitUtils.waitForElementToBeVisible(By.id("uploadedFilePath"));
            String displayedPath = uploadedFilePath.getText();
            Assert.assertTrue(displayedPath.contains(fileName), "File path should be displayed even for .exe files");
            
            logger.info("Invalid file type upload test passed (site accepts all file types)");
        } finally {
            // Clean up test file
            Files.deleteIfExists(testFile);
        }
    }
    
    @Test(priority = 4, description = "Test upload with large file name")
    public void testLongFileNameUpload() throws IOException {
        getDriver().get("https://demoqa.com/upload-download");
        
        // Create a test file with very long name
        String longFileName = "this_is_a_very_long_file_name_that_might_cause_issues_in_some_systems_test_file.txt";
        Path testFile = Paths.get(System.getProperty("user.dir"), longFileName);
        Files.write(testFile, "test content for long file name".getBytes());
        
        try {
            WebElement uploadInput = WaitUtils.waitForElementToBePresent(By.id("uploadFile"));
            uploadInput.sendKeys(testFile.toAbsolutePath().toString());
            
            WebElement uploadedFilePath = WaitUtils.waitForElementToBeVisible(By.id("uploadedFilePath"));
            String displayedPath = uploadedFilePath.getText();
            Assert.assertTrue(displayedPath.contains(longFileName), "Long file name should be handled correctly");
            
            logger.info("Long file name upload test passed");
        } finally {
            // Clean up test file
            Files.deleteIfExists(testFile);
        }
    }
}