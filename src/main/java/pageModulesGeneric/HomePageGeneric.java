package pageModulesGeneric;

import genericLibraries.BaseClass;

import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjectModel.HomePageFactory;

public class HomePageGeneric {
	
	WebDriver driver = null;
	HomePageFactory homePageFactory = null;
	Logger log =  Logger.getLogger(HomePageGeneric.class);
	public HomePageGeneric(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void getSearchBookCount(Map<String,String> map)
	{
		homePageFactory = new HomePageFactory(driver);
		log.info("Start book search in home page");
		homePageFactory.searchKeyWord.sendKeys(map.get("SearchKeyword"));
		homePageFactory.searchButton.click();
		log.info("user has enter the book "+map.get("SearchKeyword")+"and clicked on the search button");
		if(homePageFactory.searchResultInvalidMessage.size()>0)
		{
			System.out.println("Incorrect book detail given");
			
			log.warn("User has entered incorrect book name"+map.get("SearchKeyword"));
			BaseClass.skipExecution("Incorrect book detail given");
		}
		else
		{
			String actual = homePageFactory.searchResultCount.getText();
			Assert.assertEquals(map.get("BookNo.").replace(".0", ""), actual);
			log.info("The book count is "+actual);
		}
		
		
	}

}
