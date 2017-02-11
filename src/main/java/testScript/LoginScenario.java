package testScript;

import genericLibraries.BaseClass;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import pageModulesGeneric.LoginGeneric;

public class LoginScenario extends BaseClass {
	//qtpworld2008@gmail.com	learn123	qtpworld2008 g
	Logger log = Logger.getLogger(LoginScenario.class);
	
	@Test(dataProvider = "DataProvider_Excel",dataProviderClass=dataProvider.GenericDataProvider.class)
	public void validLoginScenario(Map<String,String> map) throws Exception
	{
		/*driver.findElement(By.xpath("//span[@id='sigin_info']/a[1]")).click();
		driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
		driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
		driver.findElement(By.cssSelector("td.sb1>input")).click();
		String actual = driver.findElement(By.xpath("//span[@id='username']/a")).getText();
		Assert.assertEquals(map.get("AccountID"), actual);*/
		log.info("User in Home Page");
		LoginGeneric loginGeneric = new LoginGeneric(driver);
		loginGeneric.loginModule(map);
		log.info("User has successfully logged in");
		
	}
	
	/*@Test(dataProvider = "DataProvider_Excel",dataProviderClass=dataProvider.GenericDataProvider.class)
	public void invalidLoginScenario(Map<String,String> map)
	{
		driver.findElement(By.xpath("//span[@id='sigin_info']/a[1]")).click();
		driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
		driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
		driver.findElement(By.cssSelector("td.sb1>input")).click();
		//String actual = driver.findElement(By.xpath("//b[contains(text(),'Sorry we')]")).getText();
		int size = driver.findElements(By.xpath("//b[contains(text(),'Sorry we')]")).size();
		if(size>0)
		{
			System.out.println("Test case has passed");
		}
		else
		{
			System.out.println("Test case has failed");
		}
		//Assert.assertEquals("Sorry we were unable to complete this step because :", actual);
	
		LoginGeneric loginGeneric = new LoginGeneric(driver);
		loginGeneric.loginModule(map);
	}
*/
}
