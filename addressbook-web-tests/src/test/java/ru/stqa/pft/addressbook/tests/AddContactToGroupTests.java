package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    Groups groups = app.db().groups();
    if (app.db().contacts().size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      if (groups.size() == 0) {
        app.group().create(new GroupData().withName("test 0"));
      }
      app.goTo().homePage();
      ContactData contact = new ContactData()
              .withFirstName("Svetlana").withLastName("AddToGroup");
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts contactBefore = app.db().contacts();
    Groups group = app.db().groups();
    ContactData selectedContact = contactBefore.iterator().next();
    GroupData selectedGroup = group.iterator().next();
    app.goTo().homePage();
    app.contact().addContactToGroup(selectedContact.getId(), selectedGroup.getId());
    Contacts contactAfter = app.db().contacts();
    ContactData contactWithGroup = contactAfter.iterator().next();
//    assertThat(contactWithGroup, equalTo(selectedContact.withGroups(group)));
    assertThat(contactWithGroup, equalTo(selectedContact.inGroup(group.iterator().next())));
  }
}
