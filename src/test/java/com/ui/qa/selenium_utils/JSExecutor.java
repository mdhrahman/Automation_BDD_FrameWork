package com.ui.qa.selenium_utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSExecutor{
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    public JSExecutor(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }
    public Object executeJSCode(String script, Object arg ){

        System.out.println("Executing JS Code");

        System.out.println("Script to be executed \n" +script);

        JavascriptExecutor js = (JavascriptExecutor) this.driver;

       return js.executeScript(script,arg);
    }


    public WebElement getShadowRoot(WebElement element) {

        WebElement shadowElement = (WebElement) executeJSCode("return arguments[0].shadowRoot", element);

        return shadowElement;

    }


}