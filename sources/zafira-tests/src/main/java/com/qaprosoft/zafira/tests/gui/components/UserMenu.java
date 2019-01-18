package com.qaprosoft.zafira.tests.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.zafira.tests.gui.AbstractUIObject;

public class UserMenu extends AbstractUIObject
{

	private static final String CONTAINER_LOCATOR = "//div[preceding-sibling::header]/md-menu-content[.//*[text() = 'Profile']]";

	@FindBy(xpath = CONTAINER_LOCATOR)
	private WebElement container;

	@FindBy(xpath = "//header//button//img")
	private WebElement userProfilePhoto;

	@FindBy(xpath = "//header//button[.//img]/small")
	private WebElement userProfileName;

	@FindBy(xpath = "//md-menu-item[descendant::a[contains(@href,'profile')]]")
	private WebElement userProfileButton;

	@FindBy(xpath = "//a[./*[text() = 'Performance']]")
	private WebElement userPerformanceButton;

	@FindBy(xpath = "//a[./*[text() = 'Integrations']]")
	private WebElement integrationsButton;

	@FindBy(xpath = "//a[./*[text() = 'Logout']]")
	private WebElement logoutButton;

	public UserMenu(WebDriver driver, SearchContext context)
	{
		super(driver, context);
	}

	public WebElement getUserProfilePhoto()
	{
		return userProfilePhoto;
	}

	public WebElement getUserProfileName()
	{
		return userProfileName;
	}

	public WebElement getUserProfileButton()
	{
		return userProfileButton;
	}

	public WebElement getUserPerformanceButton()
	{
		return userPerformanceButton;
	}

	public WebElement getIntegrationsButton()
	{
		return integrationsButton;
	}

	public WebElement getLogoutButton()
	{
		return logoutButton;
	}
}
