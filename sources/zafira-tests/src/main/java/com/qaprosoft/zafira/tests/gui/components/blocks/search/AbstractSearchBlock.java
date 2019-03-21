package com.qaprosoft.zafira.tests.gui.components.blocks.search;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.zafira.tests.gui.AbstractUIObject;

public abstract class AbstractSearchBlock extends AbstractUIObject
{

	protected static final String CONTAINER_LOCATOR = "//thead";

	@FindBy(xpath = "//button[./*[text() = 'APPLY']] | //button[./*[text() = 'Search']] | //button[./*[text() = 'SEARCH']]")
	protected WebElement searchButton;

	@FindBy(xpath = "//button[./*[text() = 'RESET']] | //button[./*[text() = 'Clear']] | //button[./*[text() = 'Reset']]")
	protected WebElement clearButton;

	protected AbstractSearchBlock(WebDriver driver, SearchContext context)
	{
		super(driver, context);
	}

	public void clickSearchButton()
	{
		searchButton.click();
	}

	public void clickClearButton()
	{
		clearButton.click();
	}

	public WebElement getSearchButton()
	{
		return searchButton;
	}

	public WebElement getClearButton()
	{
		return clearButton;
	}

	protected boolean isBlank(String value)
	{
		return StringUtils.isBlank(value);
	}
}
