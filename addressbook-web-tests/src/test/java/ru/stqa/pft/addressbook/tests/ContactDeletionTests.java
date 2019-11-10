package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static java.lang.Thread.sleep;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws InterruptedException {

    //проверяем список контактов, если он не пустой, то удаляем контакт
    if (app.getContactHelper().isThereAContact()) {
      //формируем список before до удаления
      List<ContactData> before = app.getContactHelper().getContactList();

      //удаляем последний элемент из списка
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContacts();
      app.getNavigationHelper().gotoHomePage();

      List<ContactData> after = app.getContactHelper().getContactList();

      //проверка
      before.remove(before.size() - 1);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);

      //иначе сначала создаем группу и контакт, а потом удаляем:
    } else {

      app.getNavigationHelper().gotoGroupPage();
      //создаем группу, если ее нет
      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
//создаем контакт
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().createContact(new ContactData("Svetlana", "Delete", "Novosibirsk", "snoskova07@gmail.com", "1231231", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();

      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContacts();
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      //проверка
      before.remove(before.size() - 1);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
    }
  }
}
