package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.contact().list().size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      if (app.group().list().size() == 0) {
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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().initContactModification(index);
    ContactData contact = new ContactData()
            .withId(before.get(before.size() - 1).getId()).withFirstName("Svetlana").withLastName("Modify").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withPhone("7654321");
    app.contact().modify(contact, false);
    app.goTo().homePage();

    //Проверка
    List<ContactData> after = app.contact().list();
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
