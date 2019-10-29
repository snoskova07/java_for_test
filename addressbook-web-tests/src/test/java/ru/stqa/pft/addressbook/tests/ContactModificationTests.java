package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (app.getContactHelper().isThereAContact()) {
        app.getContactHelper().modifyContact(new ContactData("SvetlanaEdit", "NoskovaEdit", "snoskovaedit", "PleskEdit", "NovosibirskEdit", "snoskova07e@gmail.com", null), false);

    } else {
      app.getNavigationHelper().gotoGroupPage();

      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com", "test1"), true);
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().modifyContact(new ContactData("SvetlanaEdit", "NoskovaEdit", "snoskovaedit", "PleskEdit", "NovosibirskEdit", "snoskova07e@gmail.com", null), false);
      }
      app.getNavigationHelper().gotoHomePage();
   }
}
