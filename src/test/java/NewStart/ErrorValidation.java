package NewStart;



import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.CheckoutPage;
import PageObject.LandingPage;
import PageObject.ProductCatalogue;
import PageObject.orderConformPage;
import TestComponent.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest {

	@Test
	public void loginErrorValidation() throws IOException{

		String productn = "ZARA COAT 3";
		lp.loginApplication("soumyaadhikary201@gmail.com", "Soumya12@");
	
		Assert.assertEquals("Incorrect email or password.", lp.getErorMsg());
		}
	
	@Test
	public void productErrorValidation() throws IOException{

		String productn = "ZARA COAT 3";
		ProductCatalogue pc=lp.loginApplication("soumyaadhikary23@gmail.com", "Soumya123@");
		
		List<WebElement>productname=pc.getProductList();
		
		pc.getproduct(productn);
		CartPage cp=pc.gotoCart();
		
		Assert.assertFalse(cp.cartProductMatch("ZARA COAT 33"));
		
		
	}
}

