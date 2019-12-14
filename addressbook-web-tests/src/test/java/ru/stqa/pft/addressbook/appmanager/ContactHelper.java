package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitAddAddressForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void clickAddNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContacts() throws InterruptedException {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void create(ContactData contactData, boolean b) {
        clickAddNewContact();
        fillContactForm(contactData, true);
        submitAddAddressForm();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void modify(ContactData contactData, boolean b) {
        fillContactForm(contactData, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData contact) throws InterruptedException {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
                    .withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void addContactToGroup(int contactId, int groupId) {
        selectContactById(contactId);
        selectGroupById(groupId);
        clickByAddTo();
    }

    public void removeContactFromGroup(int contactId, int groupId) {
        filterByGroup(groupId);
        selectContactById(contactId);
        removeFromGroup();
    }

    public void removeFromGroup() {
        click(By.xpath("(//input[@name='remove'])"));
    }

    public void filterByGroup(int groupId) {
        click(By.xpath("(//select[@name='group']/option[@value='" + groupId + "'])"));
    }

    public void clickByAddTo() {
        click(By.xpath("(//input[@name='add'])"));
    }

    public void selectGroupById(int groupId) {
        click(By.xpath("(//select[@name='to_group']/option[@value='" + groupId + "'])"));
    }

    public boolean isEmptyNoneList() {
        selectNoneGroup();
        boolean bool = isEmptyGroup();
        selectAllGroup();
        return bool;
    }

    public void selectNoneGroup() {
        click(By.xpath("(//select[@name='group']/option[@value='[none]'])"));
    }

    public void selectAllGroup() {
        click(By.xpath("(//select[@name='group']/option[text()='[all]'])"));
    }

    public boolean isEmptyGroup() {
       return isElementPresent(By.xpath("//span[@id='search_count'][text()='0']"));
    }

    public ContactData findContactWithGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contInGroup = contact.getGroups();
            if (contInGroup.size() > 0) {
                return contact;
            }
        }
        return null;
    }

    public ContactData findContactWithoutGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contInGroup = contact.getGroups();
            if (contInGroup.size() == 0) {
                return contact;
            }
        }
        return null;
    }
}