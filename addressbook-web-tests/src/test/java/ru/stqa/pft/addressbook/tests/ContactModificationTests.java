package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillAddAddressForm(new ContactData("SvetlanaEdit", "NoskovaEdit", "snoskovaedit", "PleskEdit", "NovosibirskEdit", "snoskova07e@gmail.com"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }
}
