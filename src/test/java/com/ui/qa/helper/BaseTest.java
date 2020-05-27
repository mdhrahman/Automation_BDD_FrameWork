package com.ui.qa.helper;//package com.bss.qa.framework.selenium_utils;

import com.ui.qa.selenium_utils.*;

public class BaseTest extends BaseClass{
	protected ValidateLocator validateLocatorObj = new ValidateLocator();
	protected NavigateMethods navigationObj = new NavigateMethods(driver);
//	protected AssertionValidation assertionObj = new AssertionValidation(driver);
	protected ClickElements clickObj = new ClickElements(driver);
	protected InputMethods inputObj = new InputMethods(driver);
	protected WaitAndSleepTime waitTimeObj = new WaitAndSleepTime(driver);
	protected SelectElementByType selectTypeObj = new SelectElementByType();
//	protected GetElements getElementsObj = new GetElements(driver);
//	protected GetValues getValuesobj = new GetValues(driver);
//	protected JSExecutor jsExecutor = new JSExecutor(driver);
//	protected DriverManager driverManage = new DriverManager(driver);

}
