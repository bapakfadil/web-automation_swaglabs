package org.swaglabs.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.swaglabs.factories.DriverFactory;

import org.swaglabs.pages.CheckoutPage;
import org.swaglabs.pages.LoginPage;
import org.swaglabs.pages.ProductPage;
import org.swaglabs.utilities.KeyWords;

import java.security.Key;
import java.time.Duration;

/**
 * @author abifadila on 08/12/2023
 * @project Challenge Chapter 7 - Web Automation
 * @for SYNRGY Academy Batch VI x BCA Group
 */

public class Challenge7Step {
    WebDriver webDriver = DriverFactory.getInstance().getDriver();
    LoginPage loginPage = new LoginPage(DriverFactory.getInstance().getDriver());
    ProductPage productPage = new ProductPage(webDriver);
    CheckoutPage checkoutPage = new CheckoutPage(webDriver);
    private Duration duration = Duration.ofSeconds(10);
    private WebDriverWait wait = new WebDriverWait(webDriver, duration);

    // Login Scenario
    @Given("user is at loginPage")
    public void userIsAtLoginPage() {
        loginPage.verifyLoginPage();
    }

    @When("^user fill (.*) and (.*)$")
    public void userFillUsernameAndPassword(String username, String password) {
        loginPage.loginScenario(username, password);
    }

    @And("user click loginButton")
    public void userClickLoginButton() {
        loginPage.clickLoginBtn();
    }

    @Then("^user verify login (.*)$")
    public void userVerifyLoginResult(String result) {
        if (result.equals("success")){
            loginPage.loginVerified();
            System.out.println("Login Success!");
        } else if (result.equals("failed")) {
            loginPage.loginError();
            System.out.println("Login Failed!");
        } else {
            System.out.println("Ada yg error pasti :(");
        }
    }

    // Product Sort Scenario
    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        String username = "standard_user";
        String password = "secret_sauce";
        userIsAtLoginPage();
        userFillUsernameAndPassword(username, password);
        userClickLoginButton();
        productPage.verifyProductPage();
    }

    @When("^I chose (.*) and clicked option (.*)$")
    public void iChoseSortNameAndClickedOptionValue(String sortName, String value) {
        productPage.optionSelect(value);
    }

    @Then("^the products should be sorted by (.*) ascending order$")
    public void theProductsShouldBeSortedBySortNameAscendingOrder(String sortName) {
        KeyWords.takeScreenshot(sortName);
    }

    @Then("^the products should be sorted by (.*) descending order$")
    public void theProductsShouldBeSortedBySortNameDescendingOrder(String sortName) {
        KeyWords.takeScreenshot(sortName);
    }

    // Product Checkout Scenario
    @When("I click add to cart button on products")
    public void iClickAddToCartButtonOnProducts() { checkoutPage.addProductToCart(); }

    @And("I click the shopping cart icon")
    public void iClickTheShoppingCartIcon() { checkoutPage.openCartPage(); }

    @Then("I should be redirected to the saucedemo.com cart page")
    public void iShouldBeRedirectedToTheSaucedemoComCartPage() { checkoutPage.verifyCartItem(); }

    @When("I click the checkout button")
    public void iClickTheCheckoutButton() { checkoutPage.clickCheckoutBtn(); }

    @And("I enter my personal information")
    public void iEnterMyPersonalInformation() {
        checkoutPage.fillInformation("Abi", "Fadila", "28");
        KeyWords.takeScreenshot("personal-information");
    }

    @And("I click the continue button")
    public void iClickTheContinueButton() { checkoutPage.clickContinueBtn(); }

    @And("I confirm my order")
    public void iConfirmMyOrder() { checkoutPage.confirmOrder(); }

    @Then("I should see the confirmation page")
    public void iShouldSeeTheConfirmationPage() {
        checkoutPage.confirmationPage();
        KeyWords.takeScreenshot("order-confirmed");
    }
}
