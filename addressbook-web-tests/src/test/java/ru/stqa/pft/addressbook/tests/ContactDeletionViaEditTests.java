package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionViaEditTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
        if (app.db().contacts ().size () == 0) {
            if (app.db().groups ().size () == 0) {
            app.goTo ().groupPage ();
            app.group ().create (new GroupData ().withName ("test 0"));
            }
            app.contact ().fullCreation (new ContactData ()
                    .withFirstName ("Теста").withLastName ("Тестовая").withAddress ("Ужгород").withTelHome ("786875")
                            .withPhoto (photo).withEmail ("email@gmail.com").withGroup ("test 0"),
                    true, app);
        }
    }
    @Test
    public void testContactDeletion() throws Exception {
        app.goTo ().homePage ();
        Contacts before = app.db().contacts ();
        ContactData modifiedContact = before.iterator ().next ();
        app.contact ().modifyAndDelete (modifiedContact);
        app.goTo ().homePage ();
        assertEquals (app.contact ().count (), before.size () - 1);
        Contacts after = app.db().contacts ();
        //assertEquals (after.size (), before.size () - 1);

        assertThat (after, equalTo (before.without (modifiedContact)));
    }

}

