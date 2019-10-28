package ru.stqa.pft.addressbook.tests;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com", "test1"), true);
    app.getContactHelper().submitAddAdderssForm();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }

}
