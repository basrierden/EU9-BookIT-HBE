package com.bookit.pages;

import com.bookit.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	
	public SignInPage() {
		PageFactory.initElements(Driver.get(), this);
	}	
	
	@FindBy(xpath = "//input[@placeholder='email']")
	public WebElement email;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement password;
	
	@FindBy(xpath = "//button[@class='button is-dark']")
	public WebElement signInButton;
	
}
