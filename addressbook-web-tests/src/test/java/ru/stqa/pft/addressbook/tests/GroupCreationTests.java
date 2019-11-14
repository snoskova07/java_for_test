package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Groups after = app.group().all();
    //старая проверка по размеру списка
    assertThat(after.size(), equalTo(before.size() + 1));

//проверка
//    group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
//    before.add(group);
//    Assert.assertEquals(before, after);
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}