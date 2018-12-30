//Faisel Farooq A
package RHTestNGMavenJenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RHMvnJnkns {
	
static WebDriver driver;
String URLname, chromeDriver, chromeDriverPathSys, loginURL, loginLnk, username, password, loginClkButton;

@Parameters({"URL", "Chromedriver", "ChromeDriverPath", "loginURL", "loginLink", "uname", "password", "loginClickButton"})
@DataProvider
public void getData(String URL, String chromedriver, String chromeDriverPath, String logURL, String loginLink, String uname, String pwd, String loginClickButton) {
	URLname=URL;
	chromeDriver=chromedriver;
	chromeDriverPathSys=chromeDriverPath;
	loginURL=logURL;
	loginLnk=loginLink;
	username=uname;
	password=pwd;
	loginClkButton=loginClickButton;
}
	
//@Parameters({"URL", "ChromeDriver", "ChromeDriverPath"})
@BeforeClass
	//public void initializeBrowser(String URL, String chromeDriver, String chromeDriverPath) {
	public void initializeBrowser() {
		System.out.println("Initialize Browser in progress...");
		System.setProperty(chromeDriver, chromeDriverPathSys);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URLname);
		System.out.println("Initialize Browser Completed");
		//return driver;
	}

//@Parameters({"loginURL", "loginLink", "username", "password", "loginClickButton"})
  @Test
  //public void login(String logURL, String loginLink, String username, String pwd, String loginClickButton) throws InterruptedException {
  public void login() throws InterruptedException {
	  System.out.println("Login in progress...");
	  if (driver.findElement(By.linkText(loginLnk)).isDisplayed()) {
			driver.findElement(By.linkText(loginLnk)).click();
		}
		String currentURL = driver.getCurrentUrl();
		if (currentURL.equals(loginURL)) {
			Thread.sleep(500);
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.className(loginClkButton)).click();
		}
			Thread.sleep(2000);
		if (driver.findElement(By.linkText("Home")).isDisplayed()) {
			System.out.println("Log in Successful");
		}
  }


	@Test(dependsOnMethods="login", priority=1)
	public static void findStockValue() throws InterruptedException {
		//CLDR
		System.out.println("Find Stock Value in progress...");
		Thread.sleep(2000);
		String stockName1 = driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div/div/section[1]/a[1]/div[1]/h4")).getText();
		//System.out.println(stockName1);
		System.out.println("Value of " + stockName1 + " " + driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div/div/section[1]/a[1]/div[1]/div")).getText() + " stocks is: " + driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div/div/section[1]/a/h3")).getText());
		System.out.println("Find Stock Value Completed");
	}
	
	@Test(dependsOnMethods="login", priority=2)
	public static void findCurrentBuyingPower() {
		System.out.println("Find Current Buying Power in progress...");
	driver.findElement(By.linkText("Account")).click();
	System.out.println("Current Buying Power: " +driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/header/div/div[2]/div/div[1]/h3")).getText());
	System.out.println("Find Current Buying Power Completed");
	}
  
  @AfterClass
  public void closeBrowser() {
	  driver.close();
	  System.out.println("Closing Browser...");
  }
}
