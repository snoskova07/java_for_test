package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    //переходим на страницу со списком групп
    app.getNavigationHelper().gotoGroupPage();

    //если нет ни одной группы, то создаем ее
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

    //формируем список групп before перед удалением
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //удаляем группу с индексом before.size() - 1
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

    //формируем список  after, который должен быть на 1 меньше, чем before
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //сравниваем списки по размеру
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  // новая проверка
  // before.remove(before.size() - 1);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

