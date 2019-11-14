package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.contact().all().size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().homePage();
      ContactData contact = new ContactData()
              .withFirstName("Svetlana").withLastName("Noskova").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withPhone("7654321").withGroup("test1");
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    app.contact().initContactModificationById(modifiedContact.getId());
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Svetlana").withLastName("Modify").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withPhone("7654321");
    app.contact().modify(contact, false);
    app.goTo().homePage();

    //Проверка
    Contacts after = app.contact().all();
//    before.remove(modifiedContact);
//    before.add(contact);
//    Assert.assertEquals(before, after);

    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
