package com.ui.qa.selenium_utils;

import com.google.common.io.Files;
import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    public DriverManager(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    public void maximizeWindow(WebDriver driver) throws Exception {
        driver.manage().window().maximize();
    }

    //public static void defaultWindow() throws Exception {
    //	driver.manage().window().setSize(new Dimension(300, 300));
    //}

    public  void customizeWindow() throws Exception
    {
        driver.manage().window().fullscreen();
    }

    public  void implicitWait(int time) throws Exception
    {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public  void explicitWait(WebElement element) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public  void pageLoad(int time) throws Exception
    {
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    public  void deleteAllCookies() throws Exception
    {
        driver.manage().deleteAllCookies();
    }


    /** Method to Double click on an element
     @param path : String : ScreenShot storage Location
     */
    public void takeScreenshot(String path) throws Throwable
    {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File trgtFile = new File(System.getProperty("user.dir") + "//screenshots//"+path+"//"+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+"image.png");
        trgtFile.getParentFile().mkdir();
        trgtFile.createNewFile();
        Files.copy(scrFile, trgtFile);

    }

    public void endTestScreenshots(Scenario scenario){
        if (scenario.isFailed()) {

            try {
//				scenario.write(scenario.getName() + " is Failed");
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png"); // ... and embed it in
            } catch (WebDriverException e) {
                e.printStackTrace();
            }

        } else {
            try {
//				scenario.write(scenario.getName() + " is pass");
                scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  void addImageFile(ITestResult result) throws Throwable
    {
        if (result.isSuccess()) {
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String failureImageFileName = result.getMethod().getMethodName()
                    + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
            File failureImageFile = new File(System.getProperty("user.dir") + "//screenshots//" + failureImageFileName);
            failureImageFile.getParentFile().mkdir();
            failureImageFile.createNewFile();
            Files.copy(imageFile, failureImageFile);
        }
    }

}