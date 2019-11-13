package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
       app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Svetlana").withLastName("Noskova").withAddress("Novosibirsk").withEmail("snoskova07@gmail.com").withPhone("7654321").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();

    //проверка
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
