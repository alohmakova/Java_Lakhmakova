package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData ("test", null, null);
    //System.out.println (app.getGroupHelper().getGroupCount());
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals (after.size (), before.size () + 1);

    /*int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max ) {
        max = g.getId();
      }
    }*/
    //цикл и корпоратор выполняют одну функцию, поэтому цикл можно убрать

    //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare (o1.getId (), o2.getId ());
    // когда мы вставили это в параметр для функции max, строку можно удалить, а затем и саму переменную int max1

    group.setId(after.stream ().max ((o1, o2) -> Integer.compare (o1.getId (), o2.getId ())).get ().getId ());
    before.add (group);
    Assert.assertEquals (new HashSet<Object> (before), new HashSet<Object> (after));
  }

}
