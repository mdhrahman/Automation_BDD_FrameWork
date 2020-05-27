package com.ui.qa.selenium_utils;
import org.openqa.selenium.By;

public class SelectElementByType 
{

	/**Method to select element 'by' type
	 * @param selectorType : String : 'By' type
	 * @param selector : String : Locator value
	 * @return By
	 */

	public By getelementbytype(String selectorType,String selector)
	{
		switch(selectorType)

		{
		case "id" : return By.id(selector);
		case "name" : return By.name(selector);
		case "class" : return By.className(selector);
		case "xpath" : return By.xpath(selector);
		case "css" : return By.cssSelector(selector);
		case "linkText" : return By.linkText(selector);
		case "partialLinkText" : return By.partialLinkText(selector);
		case "tagName" : return By.tagName(selector);

		default : return null;

		}
	}

}
