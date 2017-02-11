package testScript;

import genericLibraries.BaseClass;

import java.util.Map;

import org.testng.annotations.Test;

import pageModulesGeneric.HomePageGeneric;
import pageModulesGeneric.LoginGeneric;

public class SearchScenario extends BaseClass {
	
	@Test(dataProvider = "DataProvider_Excel",dataProviderClass=dataProvider.GenericDataProvider.class)
	public void validSearchScenario(Map<String,String> map) throws Exception
	{
		/*driver.findElement(By.id("srchword")).sendKeys(map.get("SearchKeyword"));
		driver.findElement(By.cssSelector(".newsrchbtn")).click();
		String actual = driver.findElement(By.id("find")).getText();
		System.out.println(actual);
		Assert.assertEquals(map.get("BookNo.").replace(".0", ""), actual);*/
		LoginGeneric loginGeneric = new LoginGeneric(driver);
		loginGeneric.loginModule(map);
		HomePageGeneric homePageGeneric =new HomePageGeneric(driver);
		homePageGeneric.getSearchBookCount(map);
		
	}
	
	/*@Test(dataProvider = "DataProvider_Excel",dataProviderClass=dataProvider.GenericDataProvider.class)
	public void invalidSearchScenario(Map<String,String> map)
	{
		driver.findElement(By.id("srchword")).sendKeys(map.get("SearchKeyword"));
		driver.findElement(By.cssSelector(".newsrchbtn")).click();
		//String actual = driver.findElement(By.id("find")).getText();
		int size = driver.findElements(By.cssSelector(".sorrymsg")).size();
		if(size>0)
		{
			System.out.println("The test case has passed");
		}
		else
		{
			System.out.println("The test case has failed");
		}
		
		
	}*/
	

}
