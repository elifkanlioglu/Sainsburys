package com.sainsburys.pages;

import com.sainsburys.utilities.ConfigurationReader;
import com.sainsburys.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class addItemPage {

	public addItemPage(){
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(css = "[data-testid='username']")
	private WebElement emailBox;

	@FindBy(css = "[data-testid='password']")
	private WebElement passwordBox;

	@FindBy(css = "[data-testid='log-in']")
	private WebElement loginBtn;

	@FindBy(css = "[data-testid='log-in']")
	private WebElement usernameValidation;

	@FindBy(xpath = "//a[text()='Forgot your password?']")
	private WebElement forgotPasswordBtn;

	@FindBy(css = "[data-testid='my-account-link']")
	private WebElement myAccountBtn;

	@FindBy(id = "onetrust-reject-all-handler")
	public WebElement continueWithoutAcceptingButton;

	@FindBy(css = "[data-testid='search-bar-input']")
	public WebElement searchBarInput;

	@FindBy(xpath = "(//div[contains(@class, 'SRF__tileList')]//a)[1]")
	public WebElement firstProductLink;

	@FindBy( css = "[data-testid='add-button']")
	public WebElement addProductLink;

	@FindBy(css = "[data-testid='header-trolley_button']")
	private WebElement trolleyBtn;

	@FindBy(css = "[data-testid='pt-button-quantity']")
    public WebElement itemQty;


	public void login(){
		String username = ConfigurationReader.getProperty("userName");
		String password = ConfigurationReader.getProperty("password");
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(emailBox));
        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginBtn.click();
    }

	public boolean isLoginSuccessful() {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(myAccountBtn));
			return myAccountBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isUrlCorrect(String expectedUrl) {
		return Driver.getDriver().getCurrentUrl().equals(expectedUrl);
	}

	public void handleCookies () {
		continueWithoutAcceptingButton.click();
    }

	public void setSearch(String searchItem) {
		searchBarInput.click();
		searchBarInput.sendKeys(searchItem);
		searchBarInput.submit();
	}

	public void clickFirstProduct() {
        firstProductLink.click();
    }

	public void addProduct() {
		addProductLink.click();
	}

	public void clickTrolley() {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
		System.out.println("Before clicking trolley: " + Driver.getDriver().getCurrentUrl());
		wait.until(ExpectedConditions.elementToBeClickable(trolleyBtn)).click();
		String currentUrl = Driver.getDriver().getCurrentUrl();
		System.out.println("After clicking trolley: " + currentUrl);
		if (!currentUrl.contains("trolley")) {
			throw new RuntimeException("Trolley page did not load. Current URL: " + currentUrl);
		}
	}

	public void trolleyVerification(){
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(itemQty));
        assert itemQty.isDisplayed() : "Trolley is empty";
		String quantityText = itemQty.getText();
		assert quantityText.equals("1");
	}

}
