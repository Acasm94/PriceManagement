package com.sep.pricemanagement;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PriceManagementLogoutSuccessScenario {
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
  public void testPriceManagementLoginSuccessScenario() throws Exception {
    // open | / | 
    driver.get(baseUrl + "/");
    
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("b@b.com");
    // type | id=password | b
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("b");
    // click | id=kc-login | 
    driver.findElement(By.id("kc-login")).click();
    
    
    
    // click | css=button.btn.btn-default | 
    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
    // assertTitle | Log in to AuthorizationProviderKeycloak | 
    assertEquals("Log in to AuthorizationProviderKeycloak", driver.getTitle());
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
