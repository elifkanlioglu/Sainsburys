package com.sainsburys.stepDefs;

import com.sainsburys.pages.addItemPage;
import com.sainsburys.utilities.BrowserUtils;
import com.sainsburys.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class UI_stepDefs {

	WebDriver driver;
	addItemPage addItemPage = new addItemPage();

	@Given("the user is on the Sainsbury login page")
	public void the_user_is_on_the_sainsbury_login_page() {
		addItemPage.handleCookies();
		boolean isCorrect = addItemPage.isUrlCorrect(ConfigurationReader.getProperty("loginUrl"));
		Assert.assertTrue("The user is not on the expected login page!", isCorrect);
	}
	@When("the user enters valid credentials")
	public void the_user_enters_valid_credentials() {

		addItemPage.login();
		addItemPage.handleCookies();
		boolean loginSuccess = addItemPage.isLoginSuccessful();
		Assert.assertTrue("Login failed!",loginSuccess );

	}

	@Then("the user should be redirected to the home page")
	public void the_user_should_be_redirected_to_the_home_page() {

		boolean isCorrect = addItemPage.isUrlCorrect(ConfigurationReader.getProperty("homeUrl"));
		Assert.assertTrue("The user is not on the expected home page!", isCorrect);
	}

	@When("the user search {string}")
	public void the_user_search(String searchItem) {
		addItemPage.handleCookies();
		addItemPage.setSearch(searchItem);
	}
	@When("adds the first item to the trolley")
	public void adds_the_first_item_to_the_trolley() {

		addItemPage.clickFirstProduct();
		addItemPage.addProduct();
	}
	@Then("the user should see the item in the trolley")
	public void the_user_should_see_the_item_in_the_trolley() {

		addItemPage.clickTrolley();
		BrowserUtils.waitFor(3);
		addItemPage.trolleyVerification();

	}


}