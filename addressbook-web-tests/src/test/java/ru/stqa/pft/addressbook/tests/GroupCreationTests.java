package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    //старая проверка по размеру списка
    Assert.assertEquals(after.size(), before.size() + 1);

    //новая проверка
    group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }
}