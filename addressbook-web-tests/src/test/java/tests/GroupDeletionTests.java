package tests;

import org.testng.annotations.*;
import tests.TestBase;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectGroup().click();
    app.DeleteSelectedGroups().click();
    app.returnToGroupPage();
  }

}
