package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo ().groupPage ();
    Groups before = app.group ().all ();
    GroupData group = new GroupData ().withName ("test");
    app.group ().create (group);
    assertThat (app.group ().count (), equalTo (before.size () + 1));
    Groups after = app.group ().all ();
    //assertThat (after.size (), equalTo (before.size () + 1));
    //теперь эту проверку можно поставить как перед, так и после assertThat
    //так кака метод withAdded делает копию - объект before остаётся неизменным - в сравнении участвует его копия
    //удобно, что можно не заботиться о порядке выполнения проверок


    assertThat (after, equalTo (
            before.withAdded(group.withId (after.stream().mapToInt ((g) -> g.getId ()).max().getAsInt()))));
  }
  @Test
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
