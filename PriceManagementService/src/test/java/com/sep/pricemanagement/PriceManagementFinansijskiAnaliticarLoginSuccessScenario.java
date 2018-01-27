package com.sep.pricemanagement;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.browserlaunchers.locators.GoogleChromeLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class PriceManagementFinansijskiAnaliticarLoginSuccessScenario {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	//if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
	  System.setProperty("webdriver.gecko.driver", "src/test/java/com/sep/pricemanagement/geckodriver/geckodriver32.exe");

	  //WebDriver driver = new MarionetteDriver(capabilities); 
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:4200/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPriceManagementFinansijskiAnaliticarLoginSuccessScenario() throws Exception {
    // open | /auth/realms/AuthorizationProviderKeycloak/protocol/openid-connect/auth?client_id=login-app-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2F&state=e3d49faf-ec09-44e4-8c9d-254978653e38&nonce=b0ba6c37-80ae-4939-ab48-475e7e561c1b&response_mode=fragment&response_type=code&scope=openid | 
    driver.get(baseUrl + "/auth/realms/AuthorizationProviderKeycloak/protocol/openid-connect/auth?client_id=login-app-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2F&state=e3d49faf-ec09-44e4-8c9d-254978653e38&nonce=b0ba6c37-80ae-4939-ab48-475e7e561c1b&response_mode=fragment&response_type=code&scope=openid");
    // type | id=username | c@c.com
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("c@c.com");
    // type | id=password | c
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("c");
    // click | id=kc-login | 
    driver.findElement(By.id("kc-login")).click();
    // assertTitle | PriceManagementClient | 
    assertEquals("PriceManagementClient", driver.getTitle());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
