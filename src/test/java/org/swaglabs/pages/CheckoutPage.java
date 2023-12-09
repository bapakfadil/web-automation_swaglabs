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

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    // Verifying Web Element
    @FindBy(xpath = "//*[@id='shopping_cart_container']/a")
    private WebElement cartIcon;
    @FindBy(xpath = "//*[@id='header_container']/div[2]/span")
    private WebElement yourCartTitle;
    @FindBy(xpath = "//*[@id='cart_contents_container']/div/div[1]/*[@class='cart_item']")
    private WebElement cartItems;
    @FindBy(xpath = "//*[@id='checkout_info_container']/div/form/div[1]")
    private WebElement informationForm;
    @FindBy(xpath = "//*[@id='header_container']/div[2]/span")
    private WebElement checkoutOverViewTitle;
    @FindBy(id = "checkout_complete_container")
    private WebElement checkoutComplete;

    // Products to be added into cart
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement productBag;
    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement productTShirt;

    // Button Collections
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;
    @FindBy(id = "continue")
    private WebElement continueBtn;
    @FindBy(id = "finish")
    private WebElement finishBtn;

    // Checkout Information Form
    @FindBy(id = "first-name")
    private WebElement inputFirstName;
    @FindBy(id = "last-name")
    private WebElement inputLastName;
    @FindBy(id = "postal-code")
    private WebElement inputPostalCode;

    public void addProductToCart(){
        KeyWords.clickElement(productBag);
        KeyWords.clickElement(productTShirt);
    }

    public void openCartPage(){ KeyWords.clickElement(cartIcon); }

    public void verifyCartItem(){
        // Verifying Cart Title and Added Products is there
        KeyWords.isElementDisplayed(yourCartTitle);
        KeyWords.isElementDisplayed(cartItems);
    }

    public void clickCheckoutBtn(){
        KeyWords.clickElement(checkoutBtn);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        KeyWords.isElementDisplayed(informationForm);
        KeyWords.inputText(inputFirstName, firstName);
        KeyWords.inputText(inputLastName, lastName);
        KeyWords.inputText(inputPostalCode, postalCode);
    }

    public void clickContinueBtn(){
        KeyWords.clickElement(continueBtn);
    }

    public void confirmOrder(){
        KeyWords.isElementDisplayed(checkoutOverViewTitle);
        KeyWords.clickElement(finishBtn);
    }

    public void confirmationPage(){ KeyWords.isElementDisplayed(checkoutComplete); }
}
