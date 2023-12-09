package org.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.swaglabs.utilities.KeyWords;

import java.util.concurrent.TimeUnit;

/**
 * @author abifadila on 08/12/2023
 * @project Challenge Chapter 7 - Web Automation
 * @for SYNRGY Academy Batch VI x BCA Group
 */

public class ProductPage {
    WebDriver driver;
    Boolean isExist;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='header_container']/div[2]/span")
    private WebElement productPageLogo;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/div/span/select")
    private WebElement productSortBtn;

    public void verifyProductPage(){
        KeyWords.isElementDisplayed(productPageLogo);
        KeyWords.isElementDisplayed(productSortBtn);
    }

    public void clickSortBtn(){
        productSortBtn.click();
    }

    public void optionSelect(String value){
        KeyWords.selectOption(productSortBtn, value);
    }
}
