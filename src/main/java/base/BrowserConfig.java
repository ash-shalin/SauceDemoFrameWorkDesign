package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class BrowserConfig {
	public static WebDriver getchromebrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	/**
	 * Initializes and configures an Edge WebDriver instance with specific options.
	 * The options include disabling notifications and allowing remote origins.
	 *
	 * @return An Edge WebDriver instance.
	 */
	public static WebDriver getedgebrowser() {
		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		edgeOptions.addArguments("--guest");
		WebDriver driver=new EdgeDriver(edgeOptions);
		driver.manage().window().maximize();
		return driver;

	}
}
