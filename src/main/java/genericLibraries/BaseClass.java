package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public WebDriver driver =null;
	String browserType = null;
	public static ExtentReports extentsReport = null;
	public static ExtentTest startTest = null;
	private String path = System.getProperty("user.dir")+"/extentReport.html";
	
	private String TC_ID = null;
	private String Run = null;
	private String Functionality = null;
	
	@BeforeSuite
	public void intializeVariables()
	{
		extentsReport = new ExtentReports(path,true);
	}
		
	@BeforeMethod
	@Parameters("browsertype")
	public void intializeBrowser(@Optional ("chrome") String browser,Object[] obj)
	{
		Map<String, String> map = getTestInfo(obj);
		
		browserType = browser;
		 TC_ID = map.get("TC_ID");
		 Run = map.get("Run");
		 Functionality = map.get("Functionality");
		
		startTest=extentsReport.startTest(TC_ID+"_"+Run+"_"+Functionality);
		startTest.assignCategory(browserType);
		startTest.log(LogStatus.INFO, "Initializing the browser","Successfull");
		if(browserType.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(browserType.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
			driver= new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(UtilityClass.getPropertyFile("ImplicitWait")), TimeUnit.SECONDS);
		driver.get(UtilityClass.getPropertyFile("URL"));
		startTest.log(LogStatus.INFO, "Launched the URL"+UtilityClass.getPropertyFile("URL"),"Successfull");
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot = getScreenshot();			
			startTest.log(LogStatus.FAIL, "THe test case has failed to execute",startTest.addScreenCapture(screenshot));
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			startTest.log(LogStatus.PASS, "THe test case has completed as expected","Successfull");
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			startTest.log(LogStatus.PASS, "THe test case has completed as expected","Successfull");
		}
		driver.quit();
		extentsReport.endTest(startTest);		 
	}
	
	@AfterSuite
	public void endSuite()
	{
		extentsReport.flush();
	}
	
	public static void skipExecution(String message)
	{
		throw new SkipException(message);
	}
	
	public static void forceStopExecution(String message) throws Exception
	{
		throw new Exception(message);
	}
	
	public String getScreenshot()
	{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		String timeStamp = simpleDateFormat.format(date);
		String path = System.getProperty("user.dir")+"/test-output/extentReport/"+TC_ID+"_"+Run+"_"+Functionality+"_"+timeStamp+".png";
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(screenshotAs, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return path;
	}
	
	public Map<String, String> getTestInfo(Object[] obj)
	{
		@SuppressWarnings("unchecked")
		Map<String,String> map =(Map<String, String>) obj[0];
		return map;
	}
	
	
}
