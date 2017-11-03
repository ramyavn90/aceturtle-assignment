package com.aceturtle.assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class Assignment {

	public WebDriver driver;

	public String productName;
	public String productPrice;
	public String productSize;
	public String productQuantity;

	private void setupProperties(String browser) {
		String driverName;
		String driverType;

		// Setup driver executable name
		switch (browser) {
		case "firefox":
			driverName = "geckodriver";
			driverType = "gecko";
			break;
		case "chrome":
		default:
			driverName = "chromedriver";
			driverType = "chrome";
		}

		// For compatibility between Mac and windows
		String extension = System.getProperty("os.name").toLowerCase()
				.indexOf("mac") >= 0 ? "" : "exe";

		// Update system property with driver executable path
		System.setProperty("webdriver." + driverType + ".driver",
				System.getProperty("user.dir") + "/libs/" + driverName
						+ extension);
	}

	private WebDriverWait getWebDriverWait() {
		return new WebDriverWait(driver, SiteConfig.MAX_TIMEOUT);
	}

	private JavascriptExecutor getJavascriptExecutor() {
		return (JavascriptExecutor) driver;
	}

	private WebElement getElementByXPath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) {

		// setup driver executable path
		setupProperties(browser);

		// Initialize driver based on browser name
		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
		default:
			driver = new ChromeDriver();
		}

		driver.manage().timeouts()
				.implicitlyWait(SiteConfig.MAX_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void launchSite() {
		driver.get(SiteConfig.URL);
	}

	@Test(priority = 2)
	public void assertPageTitle() {
		Assert.assertEquals(SiteConfig.TITLE, driver.getTitle());
	}

	@Test(priority = 3)
	public void goToMenRunningShoes() {
		// Hover `Men` menu
		Actions action = new Actions(driver);
		WebElement linkMenEl = getElementByXPath(SiteConfig.LINK_MEN);
		action.moveToElement(linkMenEl).build().perform();

		// Wait and click on `Running` sub menu
		WebElement linkRunningEl = getElementByXPath(SiteConfig.LINK_RUNNING);
		getWebDriverWait().until(
				ExpectedConditions.elementToBeClickable(linkRunningEl));
		linkRunningEl.click();
	}

	@Test(priority = 4)
	@Parameters("shoeIndex")
	public void selectShoe(String shoeIndex) {

		WebElement shoeEl = getElementByXPath(String.format(
				SiteConfig.ITEM_SHOE, shoeIndex));

		// Scroll to given shoe index in list
		getJavascriptExecutor().executeScript("arguments[0].scrollIntoView();",
				shoeEl);

		// Go to given shoe's detail page
		getElementByXPath(String.format(SiteConfig.LINK_SHOE, shoeIndex))
				.click();
	}

	@Test(priority = 5)
	public void addToCart() {
		// open size drop-down
		getElementByXPath(SiteConfig.TRIGGER_PRODUCT_SIZE).click();

		// choose a size
		WebElement dropdownItemSizeEl = getElementByXPath(SiteConfig.BUTTON_PRODUCT_SIZE_10);
		getWebDriverWait().until(
				ExpectedConditions.elementToBeClickable(dropdownItemSizeEl));
		dropdownItemSizeEl.click();

		/**
		 * Expected Product Name (get non-rendered text from element without CSS
		 * text-transform)
		 */
		productName = getJavascriptExecutor().executeScript(
				"return arguments[0].textContent;",
				getElementByXPath(SiteConfig.TEXT_PRODUCT_NAME)).toString();

		/**
		 * Expected Product Price
		 */
		productPrice = getElementByXPath(SiteConfig.TEXT_PRODUCT_PRICE)
				.getText();

		/**
		 * Expected Product Size
		 */
		productSize = getElementByXPath(SiteConfig.TEXT_PRODUCT_SIZE).getText();

		/**
		 * Expected Product Quantity
		 */
		productQuantity = new Select(
				getElementByXPath(SiteConfig.DROPDOWN_PRODUCT_QUANTITY))
				.getFirstSelectedOption().getAttribute("value");

		// Wait for size drop-down to be hidden
		getWebDriverWait().until(
				ExpectedConditions.invisibilityOf(dropdownItemSizeEl));

		// Add to cart
		getElementByXPath(SiteConfig.BUTTON_ADD_TO_CART).click();
	}

	@Test(priority = 6)
	public void verifyCart() {
		// Assert Product Name
		Assert.assertEquals(productName,
				getElementByXPath(SiteConfig.TEXT_CART_PRODUCT_NAME).getText());

		// Assert Product Price
		Assert.assertEquals(productPrice,
				getElementByXPath(SiteConfig.TEXT_CART_PRODUCT_PRICE).getText());

		// Assert Product Size
		Assert.assertEquals(productSize,
				getElementByXPath(SiteConfig.TEXT_CART_PRODUCT_SIZE).getText());

		// Assert Product Quantity
		Assert.assertEquals(productQuantity, new Select(
				getElementByXPath(SiteConfig.DROPDOWN_CART_PRODUCT_QUANTITY))
				.getFirstSelectedOption().getAttribute("value"));
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
