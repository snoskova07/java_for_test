package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
  private WebDriver dw;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    dw = new FirefoxDriver();
    dw.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    dw.get("http://localhost/addressbook/group.php");
    login("admin", "secret");
  }

  @Test
  public void testContactCreation() throws Exception {
    gotoAddNewPage();
    fillAddAddressForm(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com"));
    submitAddAdderssForm();
    gotoHomePage();
    logout();
  }

  private void logout() {
    dw.findElement(By.linkText("Logout")).click();
  }

  private void gotoHomePage() {
    dw.findElement(By.linkText("home page")).click();
  }

  private void submitAddAdderssForm() {
    dw.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillAddAddressForm(ContactData contactData) {
    dw.findElement(By.name("firstname")).clear();
    dw.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    dw.findElement(By.name("lastname")).clear();
    dw.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    dw.findElement(By.name("nickname")).clear();
    dw.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
    dw.findElement(By.name("company")).clear();
    dw.findElement(By.name("company")).sendKeys(contactData.getCompany());
    dw.findElement(By.name("address")).clear();
    dw.findElement(By.name("address")).sendKeys(contactData.getCity());
    dw.findElement(By.name("email")).clear();
    dw.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void gotoAddNewPage() {
    dw.findElement(By.linkText("add new")).click();
  }

  private void login(String username, String password) {
    dw.findElement(By.name("user")).clear();
    dw.findElement(By.name("user")).sendKeys(username);
    dw.findElement(By.name("pass")).clear();
    dw.findElement(By.name("pass")).sendKeys(password);
    dw.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    dw.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      dw.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      dw.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
