package ru.stqa.pft.addressbook.tests;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

    app.getContactHelper().createContact(new ContactData("Svetlana", "Noskova", "snoskova", "Plesk", "Novosibirsk", "snoskova07@gmail.com", "test1"), true);
    app.getNavigationHelper().gotoHomePage();
  }

}
