package tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup().click();
    app.getGroupHelper().DeleteSelectedGroups().click();
    app.getGroupHelper().returnToGroupPage();
  }

}
