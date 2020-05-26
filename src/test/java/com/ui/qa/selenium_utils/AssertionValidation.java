package com.ui.qa.selenium_utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class AssertionValidation
{
	private WebElement element;

	public Logger Log = Logger.getLogger(Logger.class.getName());

	SelectElementByType selectTypeObj = new SelectElementByType();



	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public AssertionValidation(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}
	/** Method to get page title
	 * @return String
	 * */
	public String getPageTitle()
	{
		return this.driver.getTitle();
	}


	/** Method to verify page title
	* @param title : String : expected title
	* @param testCase : Boolean : test case [true or false]
	*/
	public void checkTitle(String title,boolean testCase) throws FailedTestCases
	{
		String pageTitle = getPageTitle();

		if(testCase)
		{
			if(!pageTitle.equals(title))
				throw new FailedTestCases("Page Title Not Matched, Actual Page Title : "+pageTitle);
		}
		else
		{
			if(pageTitle.equals(title))
				throw new FailedTestCases("Page Title Matched, Actual Page Title : "+pageTitle);
		}
	}

	/** Method to verify partial page title
	* @param partialTitle : String : partial title string
	* @param testCase : Boolean : test case [true or false]
	*/
	public void checkPartialTitle(String partialTitle,boolean testCase) throws FailedTestCases
	{
		String pageTitle = getPageTitle();
		if(testCase)
		{
			if(!pageTitle.contains(partialTitle))
				throw new FailedTestCases("Partial Page Title Not Present, Actual Page Title : "+pageTitle);
		}
		else
		{
			if(pageTitle.contains(partialTitle))
				throw new FailedTestCases("Partial Page Title Present, Actual Page Title : "+pageTitle);
		}
	}

	/** Method to get element text
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector : String : Locator value
	 * @return String
	 */
	public String getElementText(String selectorType, String selector)
	{
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		return element.getText();

	}

	/** Method to check element text
//	* @param selectorType : String : Locator type (id, name, class, xpath, css)
//	* @param actualValue : String : Expected element text
//	* @param selector : String : Locator value
//	* @param testCase : Boolean : test case [true or false]
//	*/
	 public void checkElementSize(int expectedValue,int actualValue) throws FailedTestCases
	 {
				if (expectedValue != actualValue){

				Log.info("Actual Value Doesn't matches with Expected value");
				System.out.println("Actual Number '" + expectedValue + "'  matches with Expected Number '" + actualValue + "'");
				throw new FailedTestCases("Number Are Not Equals!!!");
//				scenario.write("Actual Number '"+ expectedValue +"'  matches with Expected Number '"+actualValue+"'");
			}
				else if (expectedValue == actualValue){
				Log.info("Actual Value matches with Expected value");
				System.out.println("Actual Number '"+ expectedValue +"' Doesn't matches with Expected Number '"+actualValue+"'");
//				scenario.write("Actual Value '"+ expectedValue +"' Doesn't matches with Expected value '"+actualValue+"'");
			}
		}

		public boolean checkElementText(String selectorType,String expectedValue,String selector) throws FailedTestCases
		{
			String elementText = getElementText(selectorType, selector);
			boolean bool = false;
				if (!elementText.contains(expectedValue))
				{
					Log.info("Actual Value Doesn't matches with Expected value");
					System.out.println("Actual Value '"+ elementText +"' Doesn't matches with Expected value '"+expectedValue+"'");
					return bool = false;
				}
				else if(elementText.contains(expectedValue))
				{
//					throw new FailedTestCases("Text not Matched");
				Log.info("Actual Value matches with Expected value");
				System.out.println("Actual Value '"+ elementText +"' matches with Expected value '"+expectedValue+"'");
//				scenario.write("Actual Value '"+ elementText +"' Doesn't matches with Expected value '"+expectedValue+"'");
					return bool = true;
				}
				return bool;
			}


	/** Method to check partial element text
	* @param selectorType : String : Locator type (id, name, class, xpath, css)
	* @param actualValue : String : Expected element text
	* @param selector : String : Locator value
	* @param testCase : Boolean : test case [true or false]
	*/
	public void checkElementPartialText(String selectorType,String actualValue,String selector,boolean testCase) throws FailedTestCases
	{
		String elementText = getElementText(selectorType, selector);

	    if (testCase)
	    {
	    	if (!elementText.contains(actualValue))
	    		throw new FailedTestCases("Text Not Matched");
	    }
	    else
	    {
	    	if (elementText.contains(actualValue))
	    		throw new FailedTestCases("Text Matched");
	    }
	}
	/** Method to return element status - enabled?
	* @param selectorType : String : Locator type (id, name, class, xpath, css)
	* @param selector : String : Locator value
	* @return Boolean
	*/
	public boolean isElementEnabled(String selectorType, String selector)
	{
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		return element.isEnabled();
	}

	/** Element enabled checking
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	@param testCase : Boolean : test case [true or false]
	*/
	public void checkElementEnable(String selectorType, String selector, boolean testCase) throws FailedTestCases
	{
		boolean result=isElementEnabled(selectorType,selector);
		if(testCase)
		{
			if (!result)
				throw new FailedTestCases("Element Not Enabled");
		}
		else
		{
			 if(result)
				 throw new FailedTestCases("Element Enabled");
		}
	}


	/** method to get attribute value
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	@param attributeName : String : attribute name
	@return String
	*/
	public String getElementAttribute(String selectorType,String selector,String attributeName)
	{
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		return element.getAttribute(attributeName);
	}
	/** method to check attribute value
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param attributeName : String : attribute name
	@param attributeValue : String : attribute value
	@param selector : String : Locator value
	*/
	public void checkElementAttribute(String selectorType, String attributeName, String attributeValue, String selector) throws FailedTestCases
	{
		String attrVal = getElementAttribute(selectorType, selector, attributeName);

			if(!attrVal.equals(attributeValue)){
				throw new FailedTestCases("Attribute Value Not Matched");
		}
			else if(attrVal.equals(attributeValue))
				throw new FailedTestCases("Attribute Value Matched");
		}

	/** method to get element status - displayed?
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	@return Boolean
	*/
	public boolean isElementDisplayed(String selectorType,String selector)
	{
		try {

		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		return element.isDisplayed();

		}catch(Exception e) {
			return false;
		}
	}
	/** method to check element presence
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/

	public boolean checkElementPresence(String selectorType , String selector) {
		boolean bool;
		try {
			this.driver.findElement(selectTypeObj.getelementbytype(selectorType, selector));
			bool = true;
		} catch (NoSuchElementException e) {
			bool =  false;
		}
		return bool;
	}
	/** method to assert checkbox check/uncheck
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value

	*/
	public void isCheckboxChecked(String selectorType,String selector) throws FailedTestCases
	{
		WebElement checkbox = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		if((!checkbox.isSelected()) )
			throw new FailedTestCases("Checkbox is not checked");
		else if(checkbox.isSelected() )
			throw new FailedTestCases("Checkbox is checked");
	}



	/** method to assert radio button selected/unselected
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void isRadioButtonSelected(String selectorType,String selector,boolean shouldBeSelected) throws FailedTestCases
	{
		WebElement radioButton = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		if((!radioButton.isSelected()) && shouldBeSelected)
			throw new FailedTestCases("Radio Button not selected");
		else if(radioButton.isSelected() && !shouldBeSelected)
			throw new FailedTestCases("Radio Button is selected");
	}

	//method to assert option from radio button group is selected/unselected
	public void isOptionFromRadioButtonGroupSelected(String selectorType,String by,String option,String selector,boolean shouldBeSelected) throws FailedTestCases
	{
		List<WebElement> radioButtonGroup = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectTypeObj.getelementbytype(selectorType, selector)));

		for (WebElement rb : radioButtonGroup) {
			if(by.equals("value"))
			{
				if(rb.getAttribute("value").equals(option))
				{
					if((!rb.isSelected()) && shouldBeSelected)
						throw new FailedTestCases("Radio Button not selected");
					else if(rb.isSelected() && !shouldBeSelected)
						throw new FailedTestCases("Radio Button is selected");
				}
			}
			else if(rb.getText().equals(option))
			{
				if((!rb.isSelected()) && shouldBeSelected)
					throw new FailedTestCases("Radio Button not selected");
				else if(rb.isSelected() && !shouldBeSelected)
					throw new FailedTestCases("Radio Button is selected");
			}
		}
	}

	/** method to get javascript pop-up alert text
	 * @return String
	 */
	public String getAlertText()
	{
		return this.driver.switchTo().alert().getText();
	}

	/**method to check javascript pop-up alert text
	 * @param text : String : Text to verify in Alert
//	 * @throws TestCaseFailed
	 */
	public void checkAlertText(String text) throws FailedTestCases
	{
		if(!getAlertText().equals(text))
			throw new FailedTestCases("Text on alert pop up not matched");
	}

	/** Method to verify if the particular option is Selected from Dropdown
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param by : String : Select element from dropdown by text or value
	 * @param option : String : Element to select from dropdown
	 * @param selector : String : Locator value
	 * @param shouldBeSelected : Boolean : test case [true or false]
//	 * @throws TestCaseFailed
	 */
	public void isOptionFromDropdownSelected(String selectorType,String by,String option,String selector,boolean shouldBeSelected) throws FailedTestCases
	{
		Select selectList;
		WebElement dropdown = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		selectList = new Select(dropdown);

		String actualValue="";
		if(by.equals("text"))
			actualValue = selectList.getFirstSelectedOption().getText();
		else
			actualValue = selectList.getFirstSelectedOption().getAttribute("value");

		if((!actualValue.equals(option))&&(shouldBeSelected))
			throw new FailedTestCases("Option Not Selected From Dropwdown");
		else if ((actualValue.equals(option))&&(!shouldBeSelected))
			throw new FailedTestCases("Option Selected From Dropwdown");
	}

	public void isImageLoad(String selectorType, String selector)
	{
		WebElement ImageFile = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));

		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)this.driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
        if (!ImagePresent)
        {
             System.out.println("Image not displayed.");
        }
        else
        {
            System.out.println("Image displayed.");
        }
	}

		
		
	}



