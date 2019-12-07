package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

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
              .withFirstName("Svetlana").withLastName("Noskova").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withHomePhone("7654321").inGroup(groups.iterator().next());
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.contact().initContactModificationById(modifiedContact.getId());
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Svetlana").withLastName("Modify").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withHomePhone("7654321");
    app.contact().modify(contact, false);
    app.goTo().homePage();
    assertThat(app.db().contacts().size(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
