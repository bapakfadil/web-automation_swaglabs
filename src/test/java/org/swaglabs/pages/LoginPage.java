package org.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.swaglabs.utilities.KeyWords;

import java.time.Duration;

/**
 * @author abifadila on 08/12/2023
 * @project Challenge Chapter 7 - Web Automation
 * @for SYNRGY Academy Batch VI x BCA Group
 */

public class LoginPage {
    protected WebDriver webDriver;

    public LoginPage(WebDriver driver){
        this.webDriver = driver;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement passWord;

    @FindBy(xpath = "//*[@id='header_container']/div[2]/span")
    private WebElement productTitle;

    @FindBy(className = "error-button")
    private WebElement errorBtn;

    public void verifyLoginPage(){
        KeyWords.waitElementToBeDisplayed(loginLogo);
        KeyWords.waitElementToBeDisplayed(userName);
        KeyWords.waitElementToBeDisplayed(passWord);
    }

    public void loginScenario(String username, String password){
        KeyWords.inputText(userName, username);
        KeyWords.inputText(passWord, password);
    }

    public void clickLoginBtn(){
        KeyWords.clickElement(loginBtn);
    }

    public void loginVerified(){
        KeyWords.isElementDisplayed(productTitle);
    }

    public void loginError(){
        KeyWords.isElementDisplayed(errorBtn);
    }
}
