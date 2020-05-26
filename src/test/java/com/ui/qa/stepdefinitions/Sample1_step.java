package com.ui.qa.stepdefinitions;

import com.ui.qa.helper.BaseTest;
import com.ui.qa.properties.Sample1_Props;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class Sample1_step extends BaseTest implements Sample1_Props {
    @Given("^User navigate to google page$")
     public void UserNavigateToGooglePage(){
        navigationObj.navigateTo(GOOGLE_URL);
    }


    @And("^User search for FDA$")
    public void userSearchForFDA() {
        inputObj.enterText(searchBoxSelectorType,"FDA",searchBoxSelector);

    }
}
