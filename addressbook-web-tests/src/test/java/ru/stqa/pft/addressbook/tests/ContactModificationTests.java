package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (app.getContactHelper().isThereAContact()) {
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactModification(before.size() - 1);
      ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Svetlana", "EditExist", "NovosibirskEdit", "snoskova07e@gmail.com", "7654321", null);
      app.getContactHelper().modifyContact(contact, false);
      app.goTo().gotoHomePage();

      //Проверка
      List<ContactData> after = app.getContactHelper().getContactList();
      before.remove(before.size() - 1);
      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);

    } else {
      app.goTo().groupPage();
      if (!app.group().isThereAGroup()) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().gotoHomePage();
      app.getContactHelper().createContact(new ContactData("Svetlana", "Noskova", "Novosibirsk", "snoskova07@gmail.com", "1234567", "test1"), true);
      app.goTo().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactModification(before.size() - 1);
      ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Svetlana", "EditCreated", "NovosibirskEdit", "snoskova07e@gmail.com", "7654321", null);
      app.getContactHelper().modifyContact(contact, false);
      app.goTo().gotoHomePage();

      //Проверка
      List<ContactData> after = app.getContactHelper().getContactList();
      before.remove(before.size() - 1);
      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
    }
  }
}
