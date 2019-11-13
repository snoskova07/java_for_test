package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
       app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstName("Svetlana").withLastName("Noskova").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withPhone("7654321").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    //проверка
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
