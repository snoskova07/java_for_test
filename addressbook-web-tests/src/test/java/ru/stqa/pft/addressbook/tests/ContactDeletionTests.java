package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.contact().list().size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      //если гуппы нет - создаем:
      if (app.group().list().size() == 0) {
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
    List<ContactData> before = app.contact().list();
    //удаляем последний элемент из списка
    int index = before.size() - 1;
    app.contact().delete(index);

    //проверка
    List<ContactData> after = app.contact().list();
    before.remove(index);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
