package com.ui.qa.helper;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class WebDriverFactory {

	public  static RemoteWebDriver driver, pralDriver;
	public  static Properties config ;
	public static final String downloadFilepath = System.getProperty("user.dir") + "/src/test/resources/downloads/";

	public static void LoadConfigProperty() throws IOException {
		config = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//config//config.properties");

		System.out.println("path of user.dir "+System.getProperty("user.dir"));
		config.load(ip);
	}


	public static void configureDriverPath() throws IOException {

		if(System.getProperty("os.name").startsWith("Linux")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/linux/geckodriver";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/linux/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
		if(System.getProperty("os.name").startsWith("Mac")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/geckodriver";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
		if(System.getProperty("os.name").startsWith("Windows")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}

	}

	public  static void setUpParallelBrowser(String browser) throws Throwable {
		if (browser.equalsIgnoreCase("firefox")){

			configureDriverPath();

			pralDriver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("chrome")) {
			configureDriverPath();

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+ downloadFilepath +"'}");

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			if(System.getProperty("os.name").startsWith("Linux"))
				options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);
			options.setExperimentalOption("useAutomationExtension", false);

			pralDriver = new ChromeDriver(options);
		}
	}
	public static  RemoteWebDriver initiateDriver()
	{
		try {
			LoadConfigProperty();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//		 configures the driver path

		if (config.getProperty("browserType").equalsIgnoreCase("firefox")){
			try {

				configureDriverPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new FirefoxDriver();

		} else if (config.getProperty("browserType").equalsIgnoreCase("chrome")){
			try {
				configureDriverPath();
			} catch (IOException e) {
				e.printStackTrace();
			}

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+ downloadFilepath +"'}");

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			if(System.getProperty("os.name").startsWith("Linux"))
				options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);
			options.setExperimentalOption("useAutomationExtension", false);

			driver = new ChromeDriver(options);

		}else if (config.getProperty("browserType").equalsIgnoreCase("CrossBrowser")) {

			driver = pralDriver;

		}

		return driver;
	}

	public static  void quitDriver()
	{
		driver.quit();
	}

	public static  void closeDriver()
	{
		driver.close();
	}

	public static void endTestScreenshots(Scenario scenario){
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


}