package baseClass;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pageObjects.LoginPage;



public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	
	public Properties properties;
	
	@BeforeClass(groups="Master", alwaysRun = true)
	public void setup() throws IOException, InterruptedException {
		FileReader file = new FileReader("./src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);
		logger= LogManager.getLogger(getClass());
		System.setProperty("WebDriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		ChromeOptions handlingSSL = new ChromeOptions();
		handlingSSL.setAcceptInsecureCerts(true);
		driver.get(properties.getProperty("appURL"));
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver);
		lp.login(properties.getProperty("username"), properties.getProperty("password"));
		
	}

	@AfterClass(groups="Master", alwaysRun = true)
	public void tearDown() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		//lp.clickLogoutLink();
		lp.logout();
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
