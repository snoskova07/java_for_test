package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 0"));
      app.goTo().homePage();
    }

    if (contacts.size() == 0) {
      ContactData contact = new ContactData()
              .withFirstName("Svetlana").withLastName("RemoveFromGroup").inGroup(groups.iterator().next());
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
      GroupData selectedGroup = groups.iterator().next();
      app.contact().filterByGroup(selectedGroup.getId());
      if (app.contact().emptyGroup()) {
        app.contact().selectAllGroup();
        Contacts contactsUI = app.contact().all();
        ContactData selectedContactUI = contactsUI.iterator().next();
        app.contact().addContactToGroup(selectedContactUI.getId(), selectedGroup.getId());
        app.goTo().homePage();
      }
    }

  @Test
  public void testRemoveContactFromGroup() {
    Groups group = app.db().groups();
    GroupData selectedGroup = group.iterator().next();
    Contacts contactsUI = app.contact().all();
    Contacts contactBefore = app.db().contacts();
    ContactData contactWithGroup = contactBefore.iterator().next();
    ContactData selectedContact = contactsUI.iterator().next();
    app.contact().removeContactFromGroup(selectedContact.getId(), selectedGroup.getId());

    Contacts contactAfter = app.db().contacts();
    ContactData contactWithoutGroup = contactAfter.iterator().next();
    assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(group.iterator().next())));
  }
}
