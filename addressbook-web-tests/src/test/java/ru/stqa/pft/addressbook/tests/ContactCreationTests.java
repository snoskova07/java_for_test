package ru.stqa.pft.addressbook.tests;
import model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoAddNewPage();
    app.getContactHelper().fillAddAddressForm(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com"));
    app.getContactHelper().submitAddAdderssForm();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }

}
