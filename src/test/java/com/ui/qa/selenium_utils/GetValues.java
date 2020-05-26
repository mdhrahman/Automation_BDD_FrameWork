package com.ui.qa.selenium_utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;


public class GetValues {

	private WebElement element;
	SelectElementByType selectTypeObj = new SelectElementByType();
	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public GetValues(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}
	/** Method to get element text
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 * @return String
	 */
	public String getElementText(String accessType, String accessName)
	{
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(accessType, accessName)));
		return element.getText();
	}

	public String getElementTextByJS(WebElement element, String accessType, String accessName){
		return (String) ((JavascriptExecutor) this.driver).executeScript("return arguments[0].innerText;", element);
	}

	public String getCurrentUrl()
	{
		String urlWithSubcID = this.driver.getCurrentUrl();
		return urlWithSubcID;
	}

	public String[] SplitText(String txtForSplit , String splitHere) {

		//	Split the url to get the Subscription id only
		String[] splits = txtForSplit.split(splitHere);
		return splits;
	}


	public String getAttributeValue(String accessType, String accessName, String value)
	{
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(accessType, accessName)));
		return element.getAttribute(value);
	}

	public String getTitle()
	{
		String title = this.driver.getTitle();
		return title;
	}

	public ArrayList<String> GetWindowHandles(){
		ArrayList<String> allWindows = new ArrayList<String> (this.driver.getWindowHandles());
		return allWindows;
	}

	public String getWindowHandle() {
		String singleWindow = this.driver.getWindowHandle();
		return singleWindow;		
	}

	public String getAttributeValueFromElement(WebElement el, String value)
	{
		String attributeValue = el.getAttribute(value);
		return attributeValue;
	}

}
