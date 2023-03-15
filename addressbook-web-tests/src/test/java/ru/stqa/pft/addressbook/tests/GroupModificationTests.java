package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo ().groupPage ();
        if (app.group ().list ().size () == 0) {
            app.group ().create (new GroupData().withName ("my_group"));
        }
    }

    @Test
    public void testGroupModification() throws Exception {

        Groups before = app.group ().all ();
        GroupData modifiedGroup = before.iterator ().next ();
        GroupData group = new GroupData ()
                .withId (modifiedGroup.getId ()).withName ("new").withHeader ("logo").withFooter ("test3");
        app.group ().modify (group);
        assertEquals (app.group ().count (), before.size ());
        Groups after = app.group ().all ();
        //assertEquals (after.size(), before.size ());
        assertThat (after, equalTo (before.without (modifiedGroup).withAdded (group)));
    }


}
