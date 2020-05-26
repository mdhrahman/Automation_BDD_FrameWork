package com.ui.qa.helper;

import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
    public static  RemoteWebDriver driver;

    public BaseClass() {
        driver = WebDriverFactory.driver;
        if(driver == null){
            driver = WebDriverFactory.initiateDriver();
        }


    }

}