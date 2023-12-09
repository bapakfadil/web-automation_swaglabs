package org.swaglabs.factories;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static DriverFactory instance = new DriverFactory();
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static DriverFactory getInstance(){ return instance; }

    public WebDriver getDriver(){ return driver.get(); }

    public void setDriver(WebDriver webDriver){ driver.set(webDriver); }
}
