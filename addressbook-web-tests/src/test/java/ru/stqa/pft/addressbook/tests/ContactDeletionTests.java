package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws InterruptedException {

    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContacts();
    } else {
      app.getNavigationHelper().gotoGroupPage();

      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }

      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().createContact(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com", "test1"), true);
      app.getNavigationHelper().gotoHomePage();

      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContacts();
    }
  }

}
