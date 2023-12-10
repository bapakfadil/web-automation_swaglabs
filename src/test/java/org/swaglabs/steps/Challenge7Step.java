package org.swaglabs.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.swaglabs.factories.DriverFactory;

import org.swaglabs.pages.CheckoutPage;
import org.swaglabs.pages.LoginPage;
import org.swaglabs.pages.ProductPage;
import org.swaglabs.utilities.KeyWords;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

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
            KeyWords.takeScreenshot("login-success");
        } else if (result.equals("failed")) {
            if (KeyWords.isElementDisplayed(loginPage.errorBtn)){
                loginPage.loginError();
            } else {
                System.out.println("There's an error with error logic :| ");
            }
        } else {
            System.out.println("There's must be an error with the step logic.");
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
        String productName = webDriver.findElement(By.xpath("//*[@class='inventory_list']/div/div/div/a/div")).getText();
        List<WebElement> itemPrices = webDriver.findElements(By.className("inventory_item_price"));

        if (value.equals("az")){
            if (productName.equals("Sauce Labs Backpack")){
                System.out.println("First product in list is equal to 'Sauce Labs Backpack'");
            } else {
                System.out.println("^Sort by (.*) logic with value (.*) failed.$");
            }
        } else if (value.equals("za")) {
            if (productName.equals("Test.allTheThings() T-Shirt (Red)")){
                System.out.println("First product in list is equal to 'Test.allTheThings() T-Shirt (Red)'");
            } else {
                System.out.println("^Sort by (.*) logic with value (.*) failed.$");
            }
        } else if (value.equals("lohi")) {
            boolean isSorted = true;
            for (int i = 0; i < itemPrices.size() - 1; i++) {
                String price1 = itemPrices.get(i).getText().replace("$", "");
                String price2 = itemPrices.get(i + 1).getText().replace("$", "");
                double value1 = Double.parseDouble(price1);
                double value2 = Double.parseDouble(price2);

                if (value1 > value2) {
                    isSorted = false;
                    break;
                }
            }
            System.out.println("Products are sorted by Price Low to High");
            Assert.assertTrue(isSorted, "Items are not sorted by price (low to high)");

        } else if (value.equals("hilo")) {
            boolean isSorted = true;
            for (int i = 0; i < itemPrices.size() - 1; i++) {
                String price1 = itemPrices.get(i).getText().replace("$", "");
                String price2 = itemPrices.get(i + 1).getText().replace("$", "");
                double value1 = Double.parseDouble(price1);
                double value2 = Double.parseDouble(price2);

                if (value1 < value2) {
                    isSorted = false;
                    break;
                }
            }
            System.out.println("Products are sorted by Price High to Low");
            Assert.assertTrue(isSorted, "Items are not sorted by price (high to low)");
        }
    }

    @Then("^the products should be sorted by (.*)$")
    public void theProductsShouldBeSortedBySortName(String sortName) {
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
