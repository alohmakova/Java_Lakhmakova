package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("my_group", null, null));
    }
    app.getGroupHelper().selectGroup().click();
    app.getGroupHelper().DeleteSelectedGroups().click();
    app.getGroupHelper().returnToGroupPage();
  }

}
