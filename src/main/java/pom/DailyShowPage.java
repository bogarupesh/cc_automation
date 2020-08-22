package pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

public class DailyShowPage {
	WebDriver driver;

	private final By footerPageUl=By.xpath("//ul[@class='list s_layouts_footerList s_layouts_footerList_1']");
		

	public DailyShowPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void printFooterLinksText(){
		Actions actions=new Actions(driver);
		WebElement footerPage=driver.findElement(footerPageUl);
		actions.moveToElement(footerPage).build().perform();
		List<WebElement> list=footerPage.findElements(By.tagName("a"));		
		Reporter.log("Bottom page Footer links..actions... ");
		for(WebElement eachElement:list){
			Reporter.log(eachElement.getText());
			System.out.println(eachElement.getText());
		}
		
	}
	

}
