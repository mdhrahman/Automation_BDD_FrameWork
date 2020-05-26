package com.ui.qa.runner;

import com.ui.qa.helper.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.testng.annotations.*;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;


@CucumberOptions (monochrome = false,
		features = "src/test/resources/features",

		glue = {"com.ui.qa.commons","com.ui.qa.helper","com.ui.qa.pageObject","com.ui.qa.properties",
				"com.ui.qa.runner","com.ui.qa.stepdefinitions","com.ui.qa.selenium_utils"},

		plugin = {"pretty",
				"json:target/json-reports/CucumberTestReport.json"},

		tags = {"@googleSearch"}
)

public class CucumberRunnerHooks {
	public TestNGCucumberRunner testNGCucumberRunner;

	@BeforeTest(alwaysRun = true)
	public void setUpClass() {
		WebDriverFactory.initiateDriver();
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "Sample", description = "sample1 feature", dataProvider = "features")
	public void authenticationFeature(CucumberFeatureWrapper cucumberFeature)  {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@After
	public void endTest(Scenario scenario) throws Throwable {
		WebDriverFactory.endTestScreenshots(scenario);
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass(){
		testNGCucumberRunner.finish();
	}

	@AfterClass(alwaysRun = true)
	public void quit(){
		WebDriverFactory.quitDriver();
	}

}
