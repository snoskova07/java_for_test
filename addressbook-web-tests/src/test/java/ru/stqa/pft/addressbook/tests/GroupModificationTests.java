package ru.stqa.pft.addressbook.tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

@Test
  public void testGroupModification() {
  app.getNavigationHelper().gotoGroupPage();
  app.getGroupHelper().selectGroup();
  app.getGroupHelper().initGroupModification();
  app.getGroupHelper().fillGroupForm(new GroupData("test1edit", "test2edit", "test3edit"));
  app.getGroupHelper().submitGroupModification();
  app.getGroupHelper().returnToGroupPage();
  app.getSessionHelper().logout();
}

}
