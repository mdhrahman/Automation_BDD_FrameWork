package com.ui.qa.selenium_utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


public class NavigateMethods {

	SelectElementByType selectTypeObj = new SelectElementByType();
	private WebElement element;
	private String old_win;
	private String lastWinHandle;
	private RemoteWebDriver driver;
	private WebDriverWait wait;

	public NavigateMethods(RemoteWebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}

	/** Method to open link
	 * @param url : String : URL for navigation
	 */
	public void navigateTo(String url)
	{
		this.driver.manage().deleteAllCookies();
		this.driver.get(url);
	}

	/** Method to Scroll
	 * @param ele : WebElement : Webelement for Scroll
	 */
	public void  scrollToView(WebElement ele)
	{
		((JavascriptExecutor)this.driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void openNewTabWindow() {
		((JavascriptExecutor)this.driver).executeScript("window.open('about:blank','_blank');");
	}

	/** Method to navigate back & forward
	 * @param direction : String : Navigate to forward or backward
	 */
	public void navigate(String direction)
	{
		if (direction.equals("back"))
			this.driver.navigate().back();
		else
			this.driver.navigate().forward();
	}

	/** Method to quite webdriver instance */
	public void closedriver()
	{
		this.driver.close();
	}

	/** Method to return key by OS wise
	 * @return Keys : Return control or command key as per OS
	 */
	public Keys getKey()
	{
		String os = System.getProperty("os.name").toLowerCase();
		if(os.contains("win"))
			return Keys.CONTROL;
		else if (os.contains("nux") || os.contains("nix"))
			return Keys.CONTROL;
		else if (os.contains("mac"))
			return Keys.COMMAND;
		else
			return null;

	}

	/** Method to zoom in/out page
	 * @param inOut : String : Zoom in or out
	 */
	public void zoomInOut(String inOut)
	{
		WebElement Sel= this.driver.findElement(selectTypeObj.getelementbytype("tagName","html"));
		if(inOut.equals("ADD"))
			Sel.sendKeys(Keys.chord(getKey(), Keys.ADD));
		else if(inOut.equals("SUBTRACT"))
			Sel.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
		else if(inOut.equals("reset"))
			Sel.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
	}

	/** Method to zoom in/out web page until web element displays
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param inOut : String : Zoom in or out
	 * @param selector : String : Locator value
	 */
	public void zoomInOutTillElementDisplay(String selectorType,String inOut,String selector)
	{
		Actions action = new Actions(this.driver);
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		while(true)
		{
			if (element.isDisplayed())
				break;
			else
				action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
		}
	}



	/** Method to resize browser
	 * @param width : int : Width for browser resize
	 * @param height : int : Height for browser resize
	 */
	public void resizeBrowser(int width, int height)
	{
		this.driver.manage().window().setSize(new Dimension(width,height));
	}

	/** Method to maximize browser	 */
	public void maximizeBrowser()
	{
		this.driver.manage().window().maximize();
	}

	/** Method to hover on element
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector : String : Locator value
	*/
	public void hoverOverElement(String selectorType, String selector)
	{
		Actions action = new Actions(this.driver);
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		action.moveToElement(element).click().build().perform();;
	}

	/** Method to scroll page to particular element
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param selector : String : Locator value
	*/
	public void scrollToElement(String selectorType, String selector)
	{
		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
		JavascriptExecutor executor = (JavascriptExecutor)this.driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}



	/** Method to scroll page to top or end
	 * @param to : String : Scroll page to Top or End
	 * @throws Exception
	 */
	public void scrollPage(String to) throws Exception
	{
		JavascriptExecutor executor = (JavascriptExecutor)this.driver;
		if (to.equals("end"))
			executor.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		else if (to.equals("top"))
            executor.executeScript("window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);");
		else
			throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
	}

	/**Method to switch to new window */
    public void switchToNewWindow()
    {
    	old_win = this.driver.getWindowHandle();
    	for(String winHandle : this.driver.getWindowHandles())
    		lastWinHandle = winHandle;
    	this.driver.switchTo().window(lastWinHandle);
    }

    /** Method to switch to old window */
    public void switchToOldWindow()
    {
    	this.driver.switchTo().window(old_win);
    }

    /** Method to switch to a window */
    public void switchToWindow(String handle)
    {
    	this.driver.switchTo().window(handle);
    }

    /** Method to switch to window by title
     * @param windowTitle : String : Name of window title to switch
     * @throws Exception */
    public void switchToWindowByTitle(String windowTitle) throws Exception
    {
    	//System.out.println("++"+windowTitle+"++");
    	old_win = this.driver.getWindowHandle();
    	boolean winFound = false;
    	for(String winHandle : this.driver.getWindowHandles())
    	{
    		String str = this.driver.switchTo().window(winHandle).getTitle();
    		//System.out.println("**"+str+"**");
    		if (str.equals(windowTitle))
    		{
    			winFound = true;
    			break;
    		}
    	}
    	if (!winFound)
    		throw new Exception("Window having title "+windowTitle+" not found");
    }

    /**Method to close new window*/
    public void closeNewWindow()
    {
    	this.driver.close();
    }

    /** Method to switch frame using web element frame
     * @param selectorType : String : Locator type (index, id, name, class, xpath, css)
	 * @param selector : String : Locator value
     *
     * */
    public void switchFrame(String selectorType, String selector)
    {
    	if(selectorType.equalsIgnoreCase("index"))
    		this.driver.switchTo().frame(selector);
    	else
    	{
    		element = this.wait.until(ExpectedConditions.presenceOfElementLocated(selectTypeObj.getelementbytype(selectorType, selector)));
    		this.driver.switchTo().frame(element);
    	}
    }



    /** method to switch to default content*/
    public void switchToDefaultContent()
    {
    	this.driver.switchTo().defaultContent();
    }

    public String getCurrentWindowHandle() {
    	return this.driver.getWindowHandle();
    }

    public Set<String> getAllWindowHandles(){
    	return this.driver.getWindowHandles();
    }

    public void acceptAlert() {
    	this.driver.switchTo().alert().accept();
    }

    public void refreshPage() {
    	this.driver.navigate().refresh();
    }
    
}
	
