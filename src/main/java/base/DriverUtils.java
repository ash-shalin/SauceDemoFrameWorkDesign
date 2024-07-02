package base;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utiliies.FileIO;

public class DriverUtils {

	public WebDriver driver;
	public static Properties prop;
	public static String browserChoice;

	/***************** invoke browser **************/
	public WebDriver invokebrowser() {
		browserChoice = prop.getProperty("browserName");
		if (browserChoice.equalsIgnoreCase("chrome")) {
			driver = BrowserConfig.getchromebrowser();
		} else if (browserChoice.equalsIgnoreCase("edge")) {
			driver = BrowserConfig.getedgebrowser();

		}
		openBrowser();
		return driver;
	}

	public DriverUtils() {
		prop = FileIO.initProperties();
	}

	/***************** open website url **************/
	public void openBrowser() {
		String url = BaseUtils.getUrl();
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/************** Send text of element ****************/
	public static void sendtext(WebElement element, String text) {
		SeleniumHelper.sendKeys(element, text);
	}

	/************** For clicking an element ****************/
	public static void clickOn(WebElement element) {
		SeleniumHelper.click(element);
	}

	/*********** Click at an particular method ********/
	public void jsClick(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);

			Thread.sleep(1000); // Optional: Add a small delay to allow scrolling to complete
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isElementPresent(WebDriver driver, WebElement element) {
		try {
			//driver.findElement(by);
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/******* Retrieving error msg *******/
	public static String rettext(WebElement element) {
		SeleniumHelper.waitForElement(element);
		return element.getText();
	}

	public static void clearAndTypeText(WebElement element, String text) {
		SeleniumHelper.waitForElement(element);
		element.clear();
		element.sendKeys(text);
	}

	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		// Perform double-click action on the element
		actions.doubleClick(element).perform();
	}

	/*********** Scroll down to an particular element *********/
	public void scrollDownToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000); // Optional: Add a small delay to allow scrolling to
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("window.scrollBy(0, 5000)"); // Scroll down by 500 pixels vertically
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delay(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void clearValue(WebElement element) {
		SeleniumHelper.waitForElement(element);
		element.clear();
	}

	public static String getText(WebElement element) {
		SeleniumHelper.waitForElement(element);
		return element.getText();
	}

	public void mousehover(WebElement element) {
		SeleniumHelper.waitForElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public static String getValue(WebElement manipulatedstring) {
		return manipulatedstring.getText();
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}


}
