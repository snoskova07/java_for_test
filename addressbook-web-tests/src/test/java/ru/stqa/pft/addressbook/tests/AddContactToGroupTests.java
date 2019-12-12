package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        Groups groups = app.db().groups();
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 0"));
            app.goTo().homePage();
        }
        Contacts contacts = app.db().contacts();
        if (contacts.size() == 0) {
            ContactData contact = new ContactData().withFirstName("Svetlana").withLastName("AddToGroup");
            app.contact().create(contact, true);
            app.goTo().homePage();
        }
        if (app.contact().emptyNoneContactList()) {
            ContactData contact = new ContactData().withFirstName("Svetlana").withLastName("ForEmptyGroup");
            app.contact().create(contact, true);
            app.goTo().homePage();
        }
    }
//3) Сравнивать надо изменившиеся списки групп контакта (который добавляем или удаляем из группы).
//   Перед необходимо получать актуальную информацию о группах этого контакта из базы данных.

    @Test
    public void testAddContactToGroup() {
        Groups group = app.db().groups();
        Contacts contactBefore = app.db().contacts();
        ContactData contactWithoutGroup = contactBefore.iterator().next();

        app.contact().selectNoneGroup();
        if (!app.contact().emptyGroup()) {
            Contacts contactsUI = app.contact().all();
            ContactData selectedContact = contactsUI.iterator().next();
            GroupData selectedGroup = group.iterator().next();
            app.contact().addContactToGroup(selectedContact.getId(), selectedGroup.getId());
        } else {
            app.contact().selectAllGroup();
            Contacts contactsUI = app.contact().all();
            ContactData selectedContact = contactsUI.iterator().next();
            GroupData selectedGroup = group.iterator().next();
            app.contact().addContactToGroup(selectedContact.getId(), selectedGroup.getId());
        }
               Contacts contactAfter = app.db().contacts();
               ContactData contactWithGroup = contactAfter.iterator().next();
               assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(group.iterator().next())));
    }
}
