package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionViaEditTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (! app.contact ().isThereAContactToModify()) {
            app.goTo ().groupPage ();
            if (! app.group ().isThereAParticularGroup ("my_group")) {
                app.group ().create (new GroupData ().withName ("my_group"));
            }
            app.contact ().fullCreation (new ContactData ("Теста", "Тестова", "Самара", "+79057590236", "ivanova@gmail.com", "my_group"), true, app);
        }
    }
    @Test
    public void testContactDeletion() throws Exception {

        List<ContactData> before = app.contact ().list ();
        int index = before.size()-1;
        app.contact ().initModification (index);
        app.contact ().delete ();
        app.goTo ().homePage ();
        List<ContactData> after = app.contact ().list ();
        Assert.assertEquals (after.size (), before.size () - 1);

        before.remove (index);
        Assert.assertEquals (before, after);
    }

}

