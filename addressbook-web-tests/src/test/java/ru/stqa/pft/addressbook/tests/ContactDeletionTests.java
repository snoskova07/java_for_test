package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.contact().all().size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      //если гуппы нет - создаем:
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      //создаем контакт
      app.goTo().homePage();
      ContactData contact = new ContactData()
              .withFirstName("Svetlana").withLastName("Delete").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withPhone("7654321").withGroup("test1");
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    //формируем список before до удаления
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    //проверка
    Set<ContactData> after = app.contact().all();
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
