package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utiliies.FileIO;

public class BaseTest {
	public WebDriver driver;
	public static Properties prop;
	public static String browserChoice;
	public static Properties SERV_PROP_FILE;
	private DriverUtils driverUtils;
//	private BaseUtils baseUtils;
	
	
	
	@BeforeSuite
	public void loadData() {
		BaseUtils.loadTestData();
	}

	public BaseTest() {
		SERV_PROP_FILE = FileIO.initProperties();
		driverUtils = new DriverUtils();
		//baseUtils=new BaseUtils(driver);
	}

	@BeforeMethod
	public void setup() {
		driver = driverUtils.invokebrowser();
	}

	@AfterMethod
	public void tearDownDriver() {
		driver.close(); // close the default window
		driver.quit(); // close the session
	}
}
