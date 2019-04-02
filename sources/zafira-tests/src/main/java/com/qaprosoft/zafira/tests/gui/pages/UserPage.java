package com.qaprosoft.zafira.tests.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.zafira.tests.gui.components.blocks.pagination.PaginationBlock;
import com.qaprosoft.zafira.tests.gui.components.blocks.search.UserSearchBlock;
import com.qaprosoft.zafira.tests.gui.components.modals.ChangePasswordModalWindow;
import com.qaprosoft.zafira.tests.gui.components.modals.CreateGroupModalWindow;
import com.qaprosoft.zafira.tests.gui.components.modals.CreateUserModalWindow;
import com.qaprosoft.zafira.tests.gui.components.table.UserTable;

public class UserPage extends BasePage
{

	@FindBy(xpath = ".//thead")
	private UserSearchBlock userSearchBlock;

	@FindBy(xpath = ".//tbody[@name = 'usersTable']")
	private UserTable userTable;

	@FindBy(xpath = ".//md-table-pagination")
	private PaginationBlock paginationBlock;

	@FindBy(tagName = "md-dialog")
	private CreateUserModalWindow createUserModalWindow;

	@FindBy(tagName = "md-dialog")
	private ChangePasswordModalWindow changePasswordModalWindow;

	@FindBy(tagName = "md-dialog")
	private CreateGroupModalWindow createGroupModalWindow;

	@FindBy(xpath = "//*[contains(@class, 'lonely-fab')]//*[contains(@class, 'md-fab')]")
	private WebElement fabButton;

	public UserPage(WebDriver driver)
	{
		super(driver, "/users");
	}

	public UserSearchBlock getUserSearchBlock()
	{
		return userSearchBlock;
	}

	public UserTable getUserTable()
	{
		return userTable;
	}

	public PaginationBlock getPaginationBlock()
	{
		return paginationBlock;
	}

	public CreateUserModalWindow getCreateUserModalWindow()
	{
		return createUserModalWindow;
	}

	public ChangePasswordModalWindow getChangePasswordModalWindow()
	{
		return changePasswordModalWindow;
	}

	public CreateGroupModalWindow getCreateGroupModalWindow()
	{
		return createGroupModalWindow;
	}

	@Override
	public boolean isFabMenuPresent(int seconds) {
		return super.isElementPresent(fabButton, seconds);
	}

	@Override
	public void clickFabMenu() {
		fabButton.click();
	}
}
