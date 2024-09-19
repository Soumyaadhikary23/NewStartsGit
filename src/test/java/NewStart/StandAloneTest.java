package NewStart;



import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;
import org.testng.Assert;

import PageObject.LandingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String productn = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage lp=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("soumyaadhikary2018@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Soumya123@");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));

		List<WebElement> productname = driver.findElements(By.cssSelector(".card-body"));
		WebElement pro = productname.stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equals(productn)).findFirst()
				.orElse(null);
		pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartWrap h3"));
		boolean match = cartProduct.stream().anyMatch(cartP -> cartP.getText().equals(productn));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.cssSelector(".form-group .input")).sendKeys("India");

		List<WebElement> countrynames = driver.findElements(By.cssSelector(".list-group-item"));
		countrynames.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst()
				.ifPresent(country -> country.click());
		
		driver.findElement(By.cssSelector(".action__submit")).click();

		String suceessMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", suceessMsg);
		driver.close();
		
	}
}
