package com.qaprosoft.zafira.tests.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.zafira.tests.gui.AbstractUIObject;
import com.qaprosoft.zafira.tests.gui.components.modals.CreateTestRunViewModalWindow;
import com.qaprosoft.zafira.tests.gui.pages.IntegrationsPage;
import com.qaprosoft.zafira.tests.gui.pages.MonitorPage;
import com.qaprosoft.zafira.tests.gui.pages.TestCasePage;
import com.qaprosoft.zafira.tests.gui.pages.TestRunPage;
import com.qaprosoft.zafira.tests.gui.pages.TestRunViewPage;
import com.qaprosoft.zafira.tests.gui.pages.UserPage;

public class Navbar extends AbstractUIObject
{

	@FindBy(xpath = ".//ul[preceding-sibling::a[.//*[text() = 'Boards']]]")
	private DashboardTabMenu dashboardTabMenu;

	@FindBy(xpath = ".//ul[preceding-sibling::a[.//*[text() = 'Runs']]]")
	private TestRunTabMenu testRunTabMenu;

	@FindBy(xpath = "//i[following-sibling::*[text() = 'Boards']]")
	private WebElement dashboardsTab;

	@FindBy(xpath = ".//i[following-sibling::*[text() = 'Runs']]")
	private WebElement testRunsTab;

	@FindBy(xpath = "//i[following-sibling::*[text() = 'Cases']]")
	private WebElement testCasesTab;

	@FindBy(xpath = "//i[following-sibling::*[text() = 'Users']]")
	private WebElement usersTab;

	@FindBy(xpath = "//i[following-sibling::*[text() = 'Monitors']]")
	private WebElement monitorsTab;
	
	@FindBy(xpath = "//i[following-sibling::*[text() = 'Integrations']]")
	private WebElement integrationsTab;

	@FindBy(xpath = "//*[contains(@class, 'menu-btn')]")
	private WebElement mobileMenuButton;

	public Navbar(WebDriver driver, SearchContext context)
	{
		super(driver, context);
	}

	public DashboardTabMenu hoverOnDashboardTab()
	{
		super.hoverOnElement(this.dashboardsTab);
		super.waitUntilElementIsPresent(getRootElement(), 10);
		LOGGER.info("Dashboard tab was hovered");
		return this.dashboardTabMenu;
	}

	public DashboardTabMenu clickOnDashboardTab()
	{
		dashboardsTab.click();
		super.waitUntilElementIsPresent(getRootElement(), 10);
		LOGGER.info("Dashboard tab was clicked");
		return this.dashboardTabMenu;
	}

	public TestRunTabMenu hoverOnTestRunTab()
	{
		super.hoverOnElement(this.testRunsTab);
		super.waitUntilElementIsPresent(this.testRunTabMenu.getRootElement(), 10);
		pause(0.2);
		LOGGER.info("Test run tab menu was hovered");
		return this.testRunTabMenu;
	}

	public TestRunPage clickOnTestRunTab()
	{
		testRunsTab.click();
		super.waitUntilElementIsPresent(this.testRunTabMenu.getRootElement(), 10);
		pause(0.2);
		LOGGER.info("Test run tab menu was clicked");
		return new TestRunPage(driver);
	}

	public TestRunPage goToTestRunPage()
	{
		/*TestRunTabMenu testRunTabMenu = hoverOnTestRunTab();
		TestRunPage testRunPage = testRunTabMenu.clickShowRunsButton();
		LOGGER.info("Test runs button was clicked");
		return testRunPage;*/
		return clickOnTestRunTab();
	}

	public TestRunViewPage goToTestRunView(String name)
	{
		TestRunTabMenu testRunTabMenu = hoverOnTestRunTab();
		TestRunViewPage testRunViewPage = testRunTabMenu.clickTestRunViewByName(name);
		LOGGER.info("Test run view page was clicked");
		return testRunViewPage;
	}

	public CreateTestRunViewModalWindow goToCreateTestRunViewModal()
	{
		return hoverOnTestRunTab().clickCreateTestRunViewButton();
	}

	public WebElement getMobileMenuButton() {
		return mobileMenuButton;
	}

	public TestCasePage clickTestCasesTab()
	{
		this.testCasesTab.click();
		return new TestCasePage(driver);
	}

	public UserPage clickUsersTab()
	{
		this.usersTab.click();
		return new UserPage(driver);
	}

	public MonitorPage clickMonitorsTab()
	{
		this.monitorsTab.click();
		return new MonitorPage(driver);
	}
	
	public IntegrationsPage clickIntegrationsTab()
	{
		this.integrationsTab.click();
		return new IntegrationsPage(driver);
	}

	public DashboardTabMenu getDashboardTabMenu()
	{
		return dashboardTabMenu;
	}

	public TestRunTabMenu getTestRunTabMenu()
	{
		return testRunTabMenu;
	}

	public WebElement getDashboardsTab()
	{
		return dashboardsTab;
	}

	public WebElement getTestRunsTab()
	{
		return testRunsTab;
	}

	public WebElement getTestCasesTab()
	{
		return testCasesTab;
	}

	public WebElement getUsersTab()
	{
		return usersTab;
	}

	public WebElement getMonitorsTab()
	{
		return monitorsTab;
	}
}
