package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    //формируем список групп before перед удалением
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    //удаляем группу с индексом before.size() - 1
    app.group().delete(index);

    //формируем список  after, который должен быть на 1 меньше, чем before
    List<GroupData> after = app.group().list();
     //сравниваем списки по размеру
    Assert.assertEquals(after.size(), before.size() - 1);

  // новая проверка
    before.remove(index);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}

