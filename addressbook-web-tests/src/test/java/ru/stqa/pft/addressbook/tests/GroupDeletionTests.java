package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = app.group ().all ();
    GroupData deletedGroup = before.iterator ().next ();
    app.group ().delete(deletedGroup);
    Groups after = app.group ().all ();
    assertThat (after.size (), equalTo (before.size () - 1)); //здесь менять на index не нужно, так как здесь другой смысл


    assertThat (after, equalTo (before.without (deletedGroup)));
  }



}
