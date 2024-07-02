package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.DriverUtils;

public class LoginPage extends DriverUtils {
	WebDriver driver;

	@FindBy(id = "user-name")
	WebElement usernameInput;

	@FindBy(id = "password")
	WebElement passwordInput;

	@FindBy(id = "login-button")
	WebElement loginButton;

	@FindBy(xpath = "//h3")
	WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		// super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginFailure(String username, String password) {

		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		clickOn(loginButton);

	}

	public void loginSuccess(String username, String password) {

//		usernameInput.sendKeys(username);
//		passwordInput.sendKeys(password);
//		loginButton.click();
		sendtext(usernameInput, username);
		sendtext(passwordInput, password);
		clickOn(loginButton);
	}

	public String errorMessageText() {
		return errorMessage.getText();
	}
}
