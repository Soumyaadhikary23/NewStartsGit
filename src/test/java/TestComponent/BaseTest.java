package TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver initializeDriver() throws IOException {
	
		// properties file
		Properties prob = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//selenium//resources//GlobalValue.properties");
		prob.load(file);
		String browserName = prob.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}else if(browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
        } else {
            System.out.println("Browser not supported.");
        }
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
		
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(FilePath),
				StandardCharsets.UTF_8);
		
		//String to HashMap  ->dependency need->JackSon DataBind
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent,  new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage appLaunch() throws IOException {
		driver =initializeDriver();
		lp=new LandingPage(driver);
		lp.Goto();
		return lp;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
