package org.swaglabs.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.swaglabs.factories.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

/**
 * @author abifadila on 08/12/2023
 * @project Challenge Chapter 7 - Web Automation
 * @for SYNRGY Academy Batch VI x BCA Group
 */

public class KeyWords {
    public static void navigateToUrl(String url){
        DriverFactory.getInstance().getDriver().get(url);
    }

    public static void waitElementToBeDisplayed(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void validateElementIsVisibleAndEnable(WebElement webElement){
         WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOf(webElement));

        if (!webElement.isDisplayed() || !webElement.isEnabled()){
            System.out.println("Element is not visible nor enable");
            throw new ElementNotInteractableException(webElement.toString());
        }
    }

    public static boolean isElementDisplayed(WebElement webElement){
        return webElement.isDisplayed();
    }

    public static void clickElement(WebElement webElement){
        waitElementToBeDisplayed(webElement);
        webElement.click();
    }

    public static void clearForm(WebElement webElement){
        waitElementToBeDisplayed(webElement);
        webElement.clear();
    }

    public static void inputText(WebElement webElement, String value){
        waitElementToBeDisplayed(webElement);
        webElement.sendKeys(value);
    }

    public static void uploadFile(WebElement fileInput, String filePath){
        waitElementToBeDisplayed(fileInput);
        fileInput.sendKeys(filePath);
    }

    public static void selectOption(WebElement webElement, String value){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
        Select dropdown = new Select(webElement);
        dropdown.selectByValue(value);
    }

    public static void takeScreenshot(String fileName){
        try {
            TakesScreenshot screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver());
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("src/reports/screenshots" + File.separator + fileName + "." + FileType.PNG.name());

            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
