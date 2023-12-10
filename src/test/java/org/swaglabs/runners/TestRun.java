package org.swaglabs.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.swaglabs.factories.BrowserFactory;
import org.swaglabs.factories.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

/**
 * @author abifadila on 08/12/2023
 * @project Challenge Chapter 7 - Web Automation
 * @for SYNRGY Academy Batch VI x BCA Group
 */

import java.time.Duration;

// Test Run
@CucumberOptions(
        features = "src/test/java/org/swaglabs/features",
        glue = "org.swaglabs.steps",
        tags = "@Test",
        plugin = {
                "pretty",
                "html:src/reports/cucumber-reports/test-report.html",
                //"json:src/reports/cucumber-reports/test-report.json",
                //"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)

public class TestRun extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeMethod
    public void setupBrowser() {
        String url = "https://www.saucedemo.com/";
        WebDriver webDriver = new BrowserFactory().launchBrowser("headless chrome");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        DriverFactory.getInstance().setDriver(webDriver);
        webDriver.navigate().to(url);
    }

    @AfterMethod
    public void teardownBrowser() {
        DriverFactory.getInstance().getDriver().close();
    }
}
