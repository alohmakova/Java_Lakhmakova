package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //System.out.println (app.getGroupHelper().getGroupCount());
    app.getGroupHelper().createGroup(new GroupData("test", null, null));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals (after.size (), before.size () + 1);
  }

}
