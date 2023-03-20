package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validGroups () throws IOException {
    List<Object[]> list = new ArrayList<Object[]> ();
      BufferedReader reader = new BufferedReader (new FileReader (new File ("src/test/resources/groups.csv")));
      String  line = reader.readLine ();
      while (line != null) {
          String[] split = line.split (";");
          list.add (new Object[] {new GroupData ().withName (split[0]).withHeader (split[1]).withFooter (split[2])});
          line = reader.readLine ();
      }
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
