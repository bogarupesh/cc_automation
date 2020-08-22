package utilityFunctions;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InitializeWebDriver {
public static WebDriver driver;

public WebDriver getDriver() throws IOException{
	
		PropertiesReader pr=new PropertiesReader();
		Properties p=pr.getProerty();
			// TODO Auto-generated method stub
		// Get the browser property from .properties file	
		    String strBrowser=p.getProperty("browser");
		    
		  
			if(strBrowser.equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver",p.getProperty("IEDriverPath"));
				driver=new InternetExplorerDriver();
			}else if (strBrowser.equalsIgnoreCase("CHROME")){
				System.setProperty("webdriver.chrome.driver",p.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome(); 
				LoggingPreferences logPrefs = new LoggingPreferences(); 
				logPrefs.enable(LogType.PERFORMANCE, Level.INFO); 
				caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs); 
	            driver = new ChromeDriver(caps);
			}else if (strBrowser.equalsIgnoreCase("FIREFOX")){
				System.setProperty("webdriver.gecko.driver",p.getProperty("GeckoDriverPath"));
				 driver=new FirefoxDriver();
			}
			// to maximize the window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			
			
			return driver;
}
}
