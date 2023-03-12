package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo ().groupPage ();
    if (app.group ().list ().size () == 0) {
      app.group ().create (new GroupData().withName ("my_group"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group ().list ();
    int index = before.size () - 1;
    app.group ().delete (index);
    List<GroupData> after = app.group ().list ();
    Assert.assertEquals (after.size (), before.size () - 1); //здесь менять на index не нужно, так как здесь другой смысл

    before.remove (index);
    Assert.assertEquals (before, after);
  }



}
