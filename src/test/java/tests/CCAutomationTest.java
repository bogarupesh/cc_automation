package tests;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import pom.CCHomePage;
import pom.DailyShowPage;
import utilityFunctions.InitializeWebDriver;
import utilityFunctions.PropertiesReader;

public class CCAutomationTest {
	WebDriver driver;
	
	@BeforeTest
	public void setup() throws IOException{		
		PropertiesReader pc=new PropertiesReader();
		Properties p=pc.getProerty();
		String url=p.getProperty("url");
		
		InitializeWebDriver id=new InitializeWebDriver();		
		driver=id.getDriver();
		driver.get(url);
		
		Reporter.log(url+ "is opened in browser");
		
	}
	
	@Test
	public void book() throws IOException, InterruptedException{
// CC Home page initialize
		CCHomePage homepage=new CCHomePage(driver);
		
		//2. Expend the "SHOWS” menu, and click on the first option in the expanded list (highlighted in the screenshot below) 
		// Navigate to Shows Link
		homepage.clickonShowsLink();
		
		//Click on Daily show link
		homepage.clickOnTheDailyShowLink();
       
		// 3.	Verify the clicked show page is opened by checking the title
		String title=driver.getTitle();
		Assert.assertTrue(title.contains("The Daily Show with Trevor Noah"));
		Reporter.log("the clicked show page is opened");
		
		// 4.	Scroll to the bottom of the page, and print out all the footer link texts
		DailyShowPage dsp=new DailyShowPage(driver);
		dsp.printFooterLinksText();

		
		//5.	Check the web traffic captured by the proxy, print out the first call’s full URL that has domain http://sc.cc.com
		//6.	From the call above, print out the value of query parameter “c9”.

		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll(); 
		for (LogEntry entry : entries) { 
		    if(entry.getMessage().contains("responseReceivedExtraInfo") && entry.getMessage().contains("http://sc.cc.com") && entry.getMessage().contains("c9=")){
			    System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());	
			    
			    JsonPath jp=new JsonPath(entry.getMessage());
			    
			    String domain= jp.getString("message.params.headers.location");
			    
			    Assert.assertTrue(domain.contains("http://sc.cc.com"));
			    Reporter.log("First Url : "+ domain);
			    System.out.println("First Url : "+ domain);
			    String[] splitDomain=domain.split("&");
			    
			    for(String s:splitDomain){
			    	if(s.contains("c9")){
			    		String content=s.split("=")[1];
			    		System.out.println("c9 parameter value is : "+content);
			    		Reporter.log("c9 parameter value is : "+content);
			    	}
			    }
			    
			    break;
		   
		    }
		    
		  
		} 	
	}
	

	@AfterTest
	public void cleanup(){
		driver.close();		
	}

	

}
