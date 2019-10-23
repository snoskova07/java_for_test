package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  public void fillAddAddressForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
;   type(By.name("address"), contactData.getCity());
    type(By.name("email"), contactData.getEmail());
  }

  public void submitAddAdderssForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.xpath("//input[@name='selected[]']"));
  }

  public void initContactModification() {
    click(By.xpath("//img[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

}
