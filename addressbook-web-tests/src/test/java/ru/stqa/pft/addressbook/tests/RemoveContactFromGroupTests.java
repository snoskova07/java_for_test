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
    if (contacts.size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      if (groups.size() == 0) {
        app.group().create(new GroupData().withName("test 0"));
      }
      app.goTo().homePage();
      ContactData contact = new ContactData()
              .withFirstName("Svetlana").withLastName("RemoveFromGroup").inGroup(groups.iterator().next());
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testRemoveContactFromGroup() {
    Contacts contactWithGroup = app.db().contacts();
    Groups group = app.db().groups();
    ContactData selectedContact = contactWithGroup.iterator().next();
    GroupData selectedGroup = group.iterator().next();
    app.goTo().homePage();
    app.contact().removeContactFromGroup(selectedContact.getId(), selectedGroup.getId());
    Contacts contactAfter = app.db().contacts();
    ContactData contactWithoutGroup = contactAfter.iterator().next();
    assertThat(contactWithoutGroup, equalTo(selectedContact.removeGroup(group.iterator().next())));
  }
}
