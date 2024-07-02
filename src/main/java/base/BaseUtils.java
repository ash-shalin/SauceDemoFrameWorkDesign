package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BaseUtils {
	public WebDriver driver;
	private static String url;
	private static JsonObject testData;
	final static String propFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\Develop.properties";
	final static String envFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\testdata\\environments.json";

	public BaseUtils(WebDriver driver) {

		this.driver = driver;
	}

	WebDriverWait wait;
	public static String env;
	/*
	 * public void waitForElementToAppear(By findBy) {
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); }
	 */

	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementsToDissappear(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	

	// load test data from loginUsername.json
	public static void loadTestData() {
		
		// load env
				Properties properties = new Properties();
				try {
					properties.load(new FileReader(propFilePath));
					env = properties.getProperty("envWebsite");
					// System.out.println(env);
				} catch (IOException e) {
					e.printStackTrace();
				}
		try (FileReader reader = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\java\\testdata\\loginUsername.json")) {
			Gson gson = new Gson();
			testData = gson.fromJson(reader, JsonObject.class);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load url
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(envFilePath));
			JSONObject jsonObject = (JSONObject) obj;
			url = (String) jsonObject.get(env);
			// System.out.println(url);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static JsonObject getTestData() {
		return testData;
	}
	
	public static String getUrl() {
		// System.out.println(url);
		return url;
	}
	
}
