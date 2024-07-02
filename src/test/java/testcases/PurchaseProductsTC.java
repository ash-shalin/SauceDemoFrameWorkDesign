package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.objects.CartPage;
import page.objects.CheckoutCompletionPage;
import page.objects.CheckoutInformationPage;
import page.objects.CheckoutOverviewPage;
import page.objects.LoginPage;
import page.objects.ProductPage;

public class PurchaseProductsTC extends BaseTest {
	LoginPage login;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutInformationPage checkoutInformationPage;
	CheckoutOverviewPage checkoutOverviewPage;
	CheckoutCompletionPage checkoutCompletion;

	@Test(retryAnalyzer=extentreporter.Retry.class)
	public void placeOrder() throws InterruptedException {
		login = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutInformationPage = new CheckoutInformationPage(driver);
		checkoutOverviewPage = new CheckoutOverviewPage(driver);
		checkoutCompletion = new CheckoutCompletionPage(driver);

		login.loginSuccess("standard_user", "secret_sauce");

		productPage.addProduct();
		productPage.goToCartPage();

		cartPage.removeProductFromCart();
		cartPage.goToCheckoutPage();

		checkoutInformationPage.submitCheckoutDetails();
		checkoutOverviewPage.confirmCheckoutDetails();

		checkoutCompletion.orderPlaced();
		String successMessage = checkoutCompletion.successMessage();
		Assert.assertEquals(successMessage, "Thank you for your order!");

	}
}
