package com.sep.pricemanagement;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PriceManagementLoginInvalidCredentials {
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
  public void testPriceManagementLoginInvalidCredentials() throws Exception {
    // open | /auth/realms/AuthorizationProviderKeycloak/protocol/openid-connect/auth?client_id=login-app-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2F&state=017d90bd-c8d4-44dd-8cdc-6ccde920e0fa&nonce=90137d0a-fa7c-4d38-8843-febc4c06c16e&response_mode=fragment&response_type=code&scope=openid | 
    driver.get(baseUrl + "/auth/realms/AuthorizationProviderKeycloak/protocol/openid-connect/auth?client_id=login-app-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200%2F&state=017d90bd-c8d4-44dd-8cdc-6ccde920e0fa&nonce=90137d0a-fa7c-4d38-8843-febc4c06c16e&response_mode=fragment&response_type=code&scope=openid");
    // type | id=username | wrong
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("wrong");
    // type | id=password | wrong
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("wrong");
    // click | id=kc-login | 
    driver.findElement(By.id("kc-login")).click();
    // assertText | css=span.kc-feedback-text | Invalid username or password.
    assertEquals("Invalid username or password.", driver.findElement(By.cssSelector("span.kc-feedback-text")).getText());
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
