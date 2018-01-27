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

public class PriceManagementProdavacLoginSuccessScenario {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:4200/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPriceManagementProdavacLoginSuccessScenario() throws Exception {
    // open | /auth/realms/AuthorizationProviderKeycloak/protocol/openid-connect/auth?client_id=login-app-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2F&state=304b486a-238b-4082-829b-87d0a8cde738&nonce=6e12a0d8-cbaf-4af4-8fcd-bdc4dd8ca6a8&response_mode=fragment&response_type=code&scope=openid | 
    driver.get(baseUrl + "/auth/realms/AuthorizationProviderKeycloak/protocol/openid-connect/auth?client_id=login-app-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2F&state=304b486a-238b-4082-829b-87d0a8cde738&nonce=6e12a0d8-cbaf-4af4-8fcd-bdc4dd8ca6a8&response_mode=fragment&response_type=code&scope=openid");
    // type | id=username | b@b.com
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("b@b.com");
    // type | id=password | b
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("b");
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
