package com.ui.qa.selenium_utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class GetElements {
	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public GetElements(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}

	SelectElementByType selectTypeObj = new SelectElementByType();
	WebElement element;
	List<WebElement> eleList;

	/**
	 * Method to return an element
	 *
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 */
	public WebElement getElement(String selectorType, String selector) {
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		return element;
	}

	/**
	 * Method to return an element
	 *
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 */
	public WebElement getElementIfPresent(String selectorType, String selector) {

		try {
			element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
			return element;
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * Method to return an element
	 *
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 */
	public Select getDropdown(String selectorType, String selector) {
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		return new Select(element);
	}

	/**
	 * Method to return the shadow-root of an element
	 *
	 * @param  element : WebElement : the WebElement from which the shadow root is to be extracted
	 *
	 */
	public WebElement getShadowRoot(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) this.driver)
				.executeScript("return arguments[0].shadowRoot",element);
		return ele;
	}

	/**
	 * Method to return a list of elements
	 *
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 */
	public List<WebElement> getElementList(String selectorType, String selector) {
		eleList = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectTypeObj.getelementbytype(selectorType, selector)));
		return eleList;
	}

	/**
	 * Method to return a list of elements which is inside an element
	 *
	 * @param ele          : the parent element from which the new elements are to be extracted
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 */
	public List<WebElement> getElementListFrom(WebElement ele, String selectorType, String selector) {
		return ele.findElements(selectTypeObj.getelementbytype(selectorType, selector));
	}

	/**
	 * Method to return an element which is inside another element
	 *
	 * @param ele          : the parent element from which the new element is to be extracted
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 */
	public WebElement getElementFrom(WebElement ele, String selectorType, String selector) {
		return ele.findElement(selectTypeObj.getelementbytype(selectorType, selector));

	}


	/**
	 * Method to return an element from a List of elements based on the text of a child element
	 *
	 * @param eles         : the list from which the expected element is to be extracted
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector     : String : Locator value
	 * @param eleText      : the text of the expected element
	 */
	public WebElement getElementFromListByChildText(List<WebElement> eles, String selectorType, String selector, String eleText) {

		for (WebElement we : eles){

			if (we.findElement(selectTypeObj.getelementbytype(selectorType, selector)).getText().equalsIgnoreCase(eleText))
				return we;
		}

		return null;
	}

	/**
	 * Method to return an element from a List of elements based on the element's text
	 *
	 * @param eles         : the list from which the expected element is to be extracted
	 * @param eleText      : the text of the expected element
	 */
	public WebElement getElementFromListByText(List<WebElement> eles, String eleText) {

		for (WebElement we : eles){

			if (we.getText().equalsIgnoreCase(eleText))
				return we;

		}

		return null;
	}

	/**
	 * Method to return an element from a List of elements based on the element's attibute
	 *
	 * @param eles         : the list from which the expected element is to be extracted
	 * @param eleAttribute    : the attibute of the expected element
	 * @param attributeValue  : the value of the attibute of the expected element
	 */
	public WebElement getElementFromListByAttributeValue(List<WebElement> eles, String eleAttribute, String attributeValue) {

		for (WebElement we : eles){

			if (we.getAttribute(eleAttribute).equals(attributeValue))
				return we;

		}

		return null;
	}
	/**
	 * Method to return an element from a List of elements based on the element's attibute
	 * @param ele         : the WebElement from which the expected element is to be extracted
	 * @param tagName    : the tagName of the expected element
	 */
	public List<WebElement> getElementByTagName(WebElement ele, String tagName){

		List<WebElement> we = ele.findElements(By.tagName(tagName));

		return we;
	}
}