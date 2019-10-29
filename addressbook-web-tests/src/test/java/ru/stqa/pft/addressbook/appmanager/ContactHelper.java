package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getCity());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

  public void createContact(ContactData contactData, boolean b) {
      gotoAddNewPage();
      fillContactForm(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com", "test1"), true);
      submitAddAdderssForm();
  }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void modifyContact(ContactData contactData, boolean b) {
        initContactModification();
        fillContactForm(new ContactData("SvetlanaEdit", "NoskovaEdit", "snoskovaedit", "PleskEdit", "NovosibirskEdit", "snoskova07e@gmail.com", null), false);
        submitContactModification();
    }
}
