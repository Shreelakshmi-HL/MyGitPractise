package Training_FW.SeleniumFramework.testComponents;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Training_FW.SeleniumFramework.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicTest 
{
	public WebDriver driver;
	public LoginPage lp;
	
	public Properties prop=new Properties();
    public  void startBrowser() throws IOException
    {
    	
    	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Training_FW\\SeleniumFramework\\resources\\GlobalData.properties");
    	
    	prop.load(fis);
    	
    	String browserName;
    	if(System.getProperty("browser")!=null)
    	{
    		 browserName=System.getProperty("browser");
    	}
    	else
    		browserName=prop.getProperty("browser");
    	
    	//String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
    	
    
    	if(browserName.contains("chrome"))
    	{
    		//WebDriverManager.chromedriver().setup();
    		ChromeOptions options=new ChromeOptions();
    		
    		if(browserName.contains("headless"))
    		{
    		options.addArguments("headless");
    		System.out.println("Browser chrome - in headless mode");
    		driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
    		}
    		else {
    		driver=new ChromeDriver();
    		System.out.println("Browser chrome opened");
    		}
    	}
    	
    	else if(browserName.equalsIgnoreCase("FF"))
    	{
    		WebDriverManager.firefoxdriver().setup();
    		driver=new FirefoxDriver();
    		System.out.println("Browser FF opened");
    	}
    	
    	else if(browserName.equalsIgnoreCase("Edge"))
    	{
    		//launch edge 
    	}
    	else if(browserName.equalsIgnoreCase("Safari"))
    	{
    		//launch safari
    	}
    		
			driver.manage().window().maximize();
			//driver.manage().window().fullscreen();
			//driver.manage().deleteAllCookies();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
    }
		@BeforeMethod
		public LoginPage launchApplication() throws IOException
		{
			//starts browser, launches url, returns login page object 
			startBrowser();
    	
			String url=prop.getProperty("URL");    	
			driver.get(url);
			System.out.println("Title of the page is- "+driver.getTitle());
			System.out.println("CurrentUrl of the browser is "+driver.getCurrentUrl());
			System.out.println("URL launched");
		
			lp=new LoginPage(driver);
			return lp;
		
    }
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
			//read json to string
		String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		
		//String to HashMap- Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
		}
		
		//{map, map}
		
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
			
			
		}


		@AfterMethod
		public void tearDown()
		{
			driver.close();
			System.out.println("Browser closed");
		}
		
		
    
}
