package RHTestNGMavenJenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RHMvnJnkns {
	
static WebDriver driver;
	
@Parameters({"URL", "ChromeDriver", "ChromeDriverPath"})
@BeforeClass
	public void initializeBrowser(String URL, String chromeDriver, String chromeDriverPath) {
		System.setProperty(chromeDriver, chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		//return driver;
	}

@Parameters({"loginURL", "loginLink", "username", "password", "loginClickButton"})
  @Test
  public void login(String logURL, String loginLink, String username, String password, String loginClickButton) throws InterruptedException {
	  if (driver.findElement(By.linkText(loginLink)).isDisplayed()) {
			driver.findElement(By.linkText(loginLink)).click();
		}
		String currentURL = driver.getCurrentUrl();
		if (currentURL.equals(logURL)) {
			Thread.sleep(500);
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.className(loginClickButton)).click();
		}
			Thread.sleep(2000);
		if (driver.findElement(By.linkText("Home")).isDisplayed()) {
			System.out.println("Log in Successful");
		}
  }


	@Test(dependsOnMethods="login", priority=1)
	public static void findStockValue() throws InterruptedException {
		//CLDR
		Thread.sleep(2000);
		String stockName1 = driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div/div/section[1]/a[1]/div[1]/h4")).getText();
		//System.out.println(stockName1);
		System.out.println("Value of " + stockName1 + " " + driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div/div/section[1]/a[1]/div[1]/div")).getText() + " stocks is: " + driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div/div/section[1]/a/h3")).getText());
	}
	
	@Test(dependsOnMethods="login", priority=2)
	public static void findCurrentBuyingPower() {
	driver.findElement(By.linkText("Account")).click();
	System.out.println("Current Buying Power: " +driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/header/div/div[2]/div/div[1]/h3")).getText());
	}
  
  @AfterClass
  public void closeBrowser() {
	  driver.close();
  }
}
