package com.ui.qa.selenium_utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InputMethods{

	SelectElementByType selectTypeObj = new SelectElementByType();
	private WebElement dropdown, element ;
	private Select selectList;


	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public InputMethods(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}

	/** Method to enter text into text field
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param text : String : Text value to enter in field
	   @param selector : String : Locator value
	 */
	public void enterText(String selectorType, String text, String selector)
	{
		this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		this.driver.findElement(selectTypeObj.getelementbytype(selectorType, selector)).sendKeys(text);
	}

	public void enterTextForcefully(String accessType, String text, String accessName) throws Exception
	{
		 element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(accessType, accessName)));
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		this.driver.findElement(selectTypeObj.getelementbytype(accessType, accessName)).sendKeys(text);
	}

	/** Method to enter text into text field
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	   @param selector : String : Locator value
	 */
	public void enterPress(String selectorType, String selector)
	{
		this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		this.driver.findElement(selectTypeObj.getelementbytype(selectorType, selector)).sendKeys(Keys.ENTER);
	}


	/** Method to clear text of text field
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void clearText(String selectorType, String selector)
	{
		this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		this.driver.findElement(selectTypeObj.getelementbytype(selectorType, selector)).clear();
	}

	/** Method to select element from Dropdown by type
	 * @param select_list : Select : Select variable
	 * @param bytype : String : Name of by type
	 * @param option : String : Option to select
	 */

	public void  selectElementFromDropdownByType(Select select_list, String bytype, String option)
	{
		if(bytype.equals("selectByIndex"))
		{
			int index = Integer.parseInt(option);
			select_list.selectByIndex(index-1);
		}
		else if (bytype.equals("value"))
			select_list.selectByValue(option);
		else if (bytype.equals("text"))
			select_list.selectByVisibleText(option);
	}

	/** Method to select option from dropdown list
	@param selectorType : String : Locator type (id, name, class, xpath, css)
//	@param by : String : Name of by type
	@param option : String : Option to select
	@param selector : String : Locator value
	*/
	public void selectOptionFromDropdownByLocator(String selectorType, String optionBy, String option, String selector)
	{
		dropdown = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		selectList = new Select(dropdown);

		if(optionBy.equals("selectByIndex"))
			selectList.selectByIndex(Integer.parseInt(option)-1);
		else if (optionBy.equals("value"))
			selectList.selectByValue(option);
		else if (optionBy.equals("text"))
			selectList.selectByVisibleText(option);
	}

	/** Method to unselect all option from dropdwon list
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void unselectAllOptionFromMultiselectDropdown(String selectorType, String selector)
	{
		dropdown = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		selectList = new Select(dropdown);
		selectList.deselectAll();
	}

	/** Method to unselect option from dropdwon list
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void deselectOptionFromDropdown(String selectorType, String optionBy, String option, String selector)
	{
		dropdown = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		selectList = new Select(dropdown);

		if(optionBy.equals("selectByIndex"))
			selectList.deselectByIndex(Integer.parseInt(option)-1);
		else if (optionBy.equals("value"))
			selectList.deselectByValue(option);
		else if (optionBy.equals("text"))
			selectList.deselectByVisibleText(option);
	}

	/** Method to check check-box
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void checkCheckbox(String selectorType, String selector)
	{
		WebElement checkbox= this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		if (!checkbox.isSelected())
			checkbox.click();
	}

	/** Method to uncheck check-box
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void uncheckCheckbox(String selectorType, String selector)
	{
		WebElement checkbox= this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		if (checkbox.isSelected())
			checkbox.click();
	}

	/** Method to select radio button
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param selector : String : Locator value
	*/
	public void selectRadioButton(String selectorType, String selector)
	{
		WebElement radioButton = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		if(!radioButton.isSelected())
			radioButton.click();
	}

	/** Method to select option from radio button group
	@param selectorType : String : Locator type (id, name, class, xpath, css)
	@param by : String : Name of by type
	@param option : String : Option to select
	@param selector : String : Locator value
//	 * @param selector2
	*/
	public void selectOptionFromRadioButtonGroup(String selectorType, String option, String by, String selector)
	{
		List<WebElement> radioButtonGroup = this.driver.findElements(selectTypeObj.getelementbytype(selectorType, selector));
		for(WebElement rb : radioButtonGroup)
		{
			if(by.equals("value"))
			{
				if(rb.getAttribute("value").equals(option) && !rb.isSelected())
					rb.click();
			}
			else if(by.equals("text"))
			{
				if(rb.getText().equals(option) && !rb.isSelected())
					rb.click();
			}
		}
	}
	
	
	

}
