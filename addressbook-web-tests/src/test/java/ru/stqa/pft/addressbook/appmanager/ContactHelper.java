package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getPhone());

        if (creation) {
          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitAddAddressForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void gotoAddNewPage() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

    public void initContactModification(int index) {
       // click(By.xpath("//img[@title='Edit']"));
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContacts() throws InterruptedException {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
      wd.findElement(By.cssSelector("div.msgbox"));
      wd.findElement(By.xpath("//table[@id='maintable']"));
    }

  public void createContact(ContactData contactData, boolean b) {
      gotoAddNewPage();
      fillContactForm(new ContactData("Svetlana", "Noskova","Novosibirsk", "snoskova07@gmail.com", "1344567", "test1"), true);
      submitAddAddressForm();
  }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void modifyContact(ContactData contactData, boolean b) {
        fillContactForm(contactData, false);
        submitContactModification();
    }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.xpath("./td[2]")).getText();
      String firstName = element.findElement(By.xpath("./td[3]")).getText();
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
