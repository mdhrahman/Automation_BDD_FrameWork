package com.ui.qa.selenium_utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClickElements{

	SelectElementByType selectTypeObj = new SelectElementByType();
//	WebdriverWait wait= new WebdriverWait(driver, 30);
	WebElement element;

	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public ClickElements(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}
	/** Method to click on an element Using SelectorType and SelectorName
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/

	public void click(String selectorType, String selector)
	{
		 element = wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		element.click();
	}

	/** Method to click on an element Using WebElement
	 *
//	@param selector : WebElement : Locator value
	*/

	public void click(WebElement ele)
	{
		ele.click();
	}

	/** Method to forcefully click on an element
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/

	public void clickAfterScrollToView(String selectorType, String selector)
	{

		element = wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

	}

	public void clickElementForcefully(WebElement ele)
	{

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();
	}

	/** Method to Double click on an element
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/

	public void doubleClick(String selectorType, String selector)
	{
	element = wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));

	Actions action = new Actions(driver);
	action.moveToElement(element).doubleClick().perform();
	}

}