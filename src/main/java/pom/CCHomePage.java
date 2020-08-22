package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CCHomePage {
	WebDriver driver;

	private final By link_Shows=By.xpath("//a[@data-link='shows']");
	private final By link_TheDailyShow=By.xpath("(//div[@class='menu_dropdown shows']//div[@class='column']//a)[1]");
	

	public CCHomePage(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickonShowsLink(){
		Actions actions=new Actions(driver);
		WebElement element=driver.findElement(link_Shows);
		actions.moveToElement(element).build().perform();
	}
	
	public void clickOnTheDailyShowLink() throws InterruptedException{		
		WebElement element1=driver.findElement(link_TheDailyShow);		
		element1.click();	
		Thread.sleep(5000);
	}

	
	

}
