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
    //хеширование - предварительная проверка при помощи более быстрой операции
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    //проверка
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    //проверки, что группа не создается

    assertThat(after, equalTo(before));
  }
}