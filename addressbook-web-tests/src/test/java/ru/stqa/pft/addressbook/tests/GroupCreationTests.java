package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validGroups () {
    List<Object[]> list = new ArrayList<Object[]> ();
    list.add (new Object[] {new GroupData().withName ("test").withHeader ("header").withFooter ("footer")});
    list.add (new Object[] {new GroupData().withName ("test1").withHeader ("header1").withFooter ("footer1")});
    list.add (new Object[] {new GroupData().withName ("test2").withHeader ("header2").withFooter ("footer2")});
    return list.iterator ();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
      app.goTo ().groupPage ();
      Groups before = app.group ().all ();
      app.group ().create (group);
      assertThat (app.group ().count (), equalTo (before.size () + 1));
      Groups after = app.group ().all ();
      //assertThat (after.size (), equalTo (before.size () + 1));
      //теперь эту проверку можно поставить как перед, так и после assertThat
      //так кака метод withAdded делает копию - объект before остаётся неизменным - в сравнении участвует его копия
      //удобно, что можно не заботиться о порядке выполнения проверок

      assertThat (after, equalTo (
              before.withAdded (group.withId (after.stream ().mapToInt ((g) -> g.getId ()).max ().getAsInt ()))));
    }
  @Test(enabled = false)
  public void testBadGroupCreation() throws Exception {//создать группу с апострофом в названии test' нельзя
    app.goTo ().groupPage ();
    Groups before = app.group ().all ();
    GroupData group = new GroupData ().withName ("test'");
    app.group ().create (group);
    assertThat (app.group ().count (), equalTo (before.size ()));
    Groups after = app.group ().all ();
    //assertThat (after.size (), equalTo (before.size ()));


    assertThat (after, equalTo (before));
  }

}
