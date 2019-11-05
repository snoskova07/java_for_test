package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.*;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

  }
}
