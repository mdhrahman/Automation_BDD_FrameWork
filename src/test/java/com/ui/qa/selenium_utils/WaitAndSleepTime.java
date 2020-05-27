package com.ui.qa.selenium_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WaitAndSleepTime
{
	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public WaitAndSleepTime(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}

	SelectElementByType selectTypeObj = new SelectElementByType();
	/** Method to wait
	 * @param time : String : Time to wait
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 */
	public void Thread(int time) throws NumberFormatException, InterruptedException
	{
		//sleep method takes parameter in milliseconds
		Thread.sleep(time);
	}

	/**Method to implicitly wait
	 * @param Seconds : integer : time to wait */
	public void implicitlyWait(int Seconds)
	{
		this.driver.manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);

	}

	/**Method to Explicitly wait for element to be displayed
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector : String : Locator value
	 * @param duration : String : Time to wait for element to be displayed
	 */
	public void waitForElementToDisplay(String selectorType,String selector,String duration)
	{
		By byEle = selectTypeObj.getelementbytype(selectorType, selector);
		WebDriverWait wait = (new WebDriverWait(this.driver,Integer.parseInt(duration)*1000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
	}

	/** Method to Explicitly wait for element to be enabled=click
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector : String : Locator value
	 * @param duration : String : Time to wait for element to be clickable
	 */
	public void waitForElementToClick(String selectorType,String selector,String duration)
	{
		By byEle =selectTypeObj.getelementbytype(selectorType, selector);
		WebDriverWait wait = (new WebDriverWait(this.driver,Integer.parseInt(duration)*1000));
		wait.until(ExpectedConditions.elementToBeClickable(byEle));
	}

	public  void explicitWait(WebElement element) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(this.driver, 3000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}


}
