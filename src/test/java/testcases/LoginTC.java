package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.JsonObject;

import base.BaseTest;
import base.BaseUtils;
import page.objects.LoginPage;



//@Listeners(extentreporter.Listeners.class)
public class LoginTC extends BaseTest {

	LoginPage loginPage;

	// for sending only one userCredentials to the method getUserCredentials

	/*
	 * @DataProvider(name = "userCredentials") public Object[][]
	 * getUserCredentials() {
	 * 
	 * JsonObject jsonObject = BaseUtils.getTestData(); JsonObject appData =
	 * jsonObject.getAsJsonObject("User1"); Object[][] testData = new Object[1][2];
	 * testData[0][0] = appData.get("Username").getAsString(); testData[0][1] =
	 * appData.get("Password").getAsString(); return testData; }
	 */

	// for sending multiple usercredentials from json to method getUserCredentials
	@DataProvider(name = "userCredentials")
	public static Object[][] getUserCredentials() {
		JsonObject jsonObject = BaseUtils.getTestData();
//"User2", "User3", "User4"
		String[] users = { "User1" };
		Object[][] testData = new Object[users.length][2];

		for (int i = 0; i < users.length; i++) {

			JsonObject userData = jsonObject.getAsJsonObject(users[i]);

			testData[i][0] = userData.get("Username").getAsString();
			testData[i][1] = userData.get("Password").getAsString();
		}
		return testData;

	}

	// validLogin
	@Test(dataProvider = "userCredentials", dataProviderClass = testcases.LoginTC.class)
	public void validLogin(String username, String password) {
		loginPage = new LoginPage(driver);
		loginPage.loginSuccess(username, password);

	}

	// Invalid login with incorrect username and password
	@Test(enabled = false)
	public void invalidLogin() {
		loginPage = new LoginPage(driver);
		loginPage.loginFailure("agent_alan", "Cobra007");

		// Assert Title of the page
		SoftAssert softAssert = new SoftAssert();
		String actualTitle = driver.getTitle();
		String expectedTitle = "Swag Labs";
		softAssert.assertEquals(actualTitle, expectedTitle, "Title Does Not Match");
		softAssert.assertEquals(driver.findElement(By.id("login-button")).isDisplayed(), true,
				"Login Button Not Found");
		softAssert.assertAll();

		// Assert error message
		String errorMessage = loginPage.errorMessageText();
		Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
	}
}
