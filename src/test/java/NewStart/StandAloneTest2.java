package NewStart;



import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.CartPage;
import PageObject.CheckoutPage;
import PageObject.LandingPage;
import PageObject.OrderPage;
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

public class StandAloneTest2 extends BaseTest {
	String productn = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException{
		ProductCatalogue pc=lp.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>productname=pc.getProductList();
		
		pc.getproduct(input.get("product"));
		CartPage cp=pc.gotoCart();
		
		Assert.assertTrue(cp.cartProductMatch(input.get("product")));
		CheckoutPage checkoutP=cp.checkout();
		
		checkoutP.entrycountryName("India");
		checkoutP.selectCountry();
		orderConformPage op=checkoutP.submitClick();

		String suceessMsg=op.successMsg();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", suceessMsg);
		}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryValidation() {
		ProductCatalogue pc=lp.loginApplication("soumyaadhikary2018@gmail.com", "Soumya123@");
		OrderPage op=pc.gotoorderHistory();
		boolean match=op.orderPageProductMatch(productn);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
	/*	HashMap<String,String>map=new HashMap<String,String>();
		map.put("email", "soumyaadhikary2018@gmail.com");
		map.put("password", "Soumya123@");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String>map1=new HashMap<String,String>();
		map1.put("email", "soumyaadhikary23@gmail.com");
		map1.put("password", "Soumya123@");
		map1.put("product", "ADIDAS ORIGINAL");*/
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\New\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
