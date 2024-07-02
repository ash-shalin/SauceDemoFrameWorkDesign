package base;

import org.openqa.selenium.WebElement;

public class SeleniumHelper {
	private final static long maxWaitTime = 20000;
	private final static long pollingTime = 500;
	
	public static void waitForElement(WebElement element) {
		boolean found = false;
		long millis = 0;
		while (millis <= maxWaitTime && !found) {
			try {
				Thread.sleep(pollingTime);
				millis += pollingTime;
				element.isDisplayed();
				found = true;
			} catch (Exception e) {}
		}
	}
	
	public static void click(WebElement element) {
		waitForElement(element);
		element.click();
	}
	
	public static void sendKeys(WebElement element, String text) {
		waitForElement(element);
		element.sendKeys(text);
	}
}
