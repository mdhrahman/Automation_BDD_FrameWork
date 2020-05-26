package com.ui.qa.selenium_utils;

import java.util.Arrays;

public class ValidateLocator 
{

	public boolean valid_locator_type(String type)
	{
		return Arrays.asList("id","class","css","name","xpath").contains(type);
	}
	
	/** Method to verify locator type
	 * @param type : String : Locator type (id, name, class, xpath, css)
	 */
	public void validateLocatorType(String type) throws Exception
	{
		if(!valid_locator_type(type))
			throw new Exception("invalid locator type - "+type);
	}
	
	// method to validate dropdown selector
	public boolean valid_dropdown_option(String dropdownOption) 
	{
		return Arrays.asList("text","value","index").contains(dropdownOption);	
		
	}
	
	/** Method to verify dropdown selector (text, value or index)
	 * @param optionBy : String : Locator type (text, value, index)
	 */
	public void validateDropdownOption(String dropdownOption) throws Exception 
	{
		if(!valid_dropdown_option(dropdownOption))
			throw new Exception("Invalid Dropdown Option - "+dropdownOption);
	}
}
