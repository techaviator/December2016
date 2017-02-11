package pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory {
	
public HomePageFactory(WebDriver driver) {
	PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//span[@id='sigin_info']/a[1]")
	public WebElement signIn;
	
	@FindBy(xpath="//span[@id='username']/a")
	public WebElement signInName;
	
	@FindBy(id="srchword")
	public WebElement searchKeyWord;
	
	@FindBy(css=".newsrchbtn")
	public WebElement searchButton;
	
	@FindBy(id="find")
	public WebElement searchResultCount;
	
	
	@FindBy(css=".sorrymsg")
	public List<WebElement> searchResultInvalidMessage;
	
	
	
}
