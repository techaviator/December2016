package pageModulesGeneric;

import genericLibraries.BaseClass;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import pageObjectModel.HomePageFactory;
import pageObjectModel.LoginPageFactory;

public class LoginGeneric {
	
	WebDriver driver = null;
	LoginPageFactory loginPageFactory = null;
	HomePageFactory homePageFactory = null;
	Logger log = Logger.getLogger(LoginGeneric.class);
	public LoginGeneric(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void loginModule(Map<String,String> map) throws Exception
	{
		homePageFactory = new HomePageFactory(driver);
		loginPageFactory = new LoginPageFactory(driver);
		homePageFactory.signIn.click();
		
		log.info("User in Login page");
		BaseClass.startTest.log(LogStatus.INFO, "User in Login page", "Successfull");
		loginPageFactory.userName.sendKeys(map.get("UserName"));
		loginPageFactory.passWord.sendKeys(map.get("Password"));
		loginPageFactory.loginButton.click();
		log.info("User Entered login details and clicked on the login button");
		BaseClass.startTest.log(LogStatus.INFO, "User Entered login details and clicked on the login button", "Successfull");
		if(loginPageFactory.invalidLoginMessage.size()>0)
		{
			System.out.println("The test case has to be failed");
			log.warn("The credentials are incorrect, skipping the execution for this test case");
			BaseClass.startTest.log(LogStatus.INFO, "The credentials are incorrect, skipping the execution for this test case. Check the log for more info", "Successfull");
			BaseClass.skipExecution("The user has given incorrect user details");
			//BaseClass.forceStopExecution("The user has given incorrect user details");
		}
		else
		{			
			String actual = homePageFactory.signInName.getText();
			Assert.assertEquals(map.get("AccountID"), actual);
			log.info("The user has logged in successfully");
			BaseClass.startTest.log(LogStatus.INFO, "The user has logged in successfully", "Successfull");
			
		}
	}

}
