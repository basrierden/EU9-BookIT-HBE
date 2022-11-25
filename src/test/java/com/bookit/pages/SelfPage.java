package com.bookit.pages;

import com.bookit.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelfPage extends TopNavigationBar{
	public SelfPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	
	
	@FindBy(xpath = "(//*[@class='title is-6'])[1]")
	public WebElement name;

	@FindBy(xpath = "(//*[@class='title is-6'])[2]")
	public WebElement role;

	@FindBy(xpath = "(//*[@class='title is-6'])[3]")
	public WebElement team;

	@FindBy(xpath = "(//*[@class='title is-6'])[4]")
	public WebElement batch;

	@FindBy(xpath = "(//*[@class='title is-6'])[5]")
	public WebElement campus;
}
