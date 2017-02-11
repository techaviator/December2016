package pageObjectModel;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
	
	/*driver.findElement(By.xpath("//input[@name = 'logid']")).sendKeys(map.get("UserName"));
	driver.findElement(By.xpath("//input[@name = 'pswd']")).sendKeys(map.get("Password"));
	driver.findElement(By.cssSelector("td.sb1>input")).click();
	int size = driver.findElements(By.xpath("//b[contains(text(),'Sorry we')]")).size();
*/
	
	public LoginPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
			
		}
	@FindBy(xpath="//input[@name = 'logid']")
	public WebElement userName;
	
	@FindBy (xpath = "//input[@name = 'pswd']")
	public WebElement passWord;
	
	@FindBy (css = "td.sb1>input")
	public WebElement loginButton;
	
	@FindBy (xpath = "//b[contains(text(),'Sorry we')]")
	public List<WebElement> invalidLoginMessage;
	
	
}
