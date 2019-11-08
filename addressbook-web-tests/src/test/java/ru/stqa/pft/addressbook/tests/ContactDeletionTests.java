package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws InterruptedException {

    //проверяем список контактов, если он не пустой, то удаляем контакт
    if (app.getContactHelper().isThereAContact()) {
      //формируем список before до удаления
      List<ContactData> before = app.getContactHelper().getContactList();

      //выбираем последний элемент из списка

      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContacts();
      //иначе сначала создаем группу и контакт, а потом удаляем
    } else {

      app.getNavigationHelper().gotoGroupPage();
      //создаем группу, если ее нет
      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }

      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().createContact(new ContactData("Svetlana", "Noskova", "Novosibirsk", "snoskova07@gmail.com", "1231231", "test1"), true);
      app.getNavigationHelper().gotoHomePage();

      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContacts();
    }
  }

}
