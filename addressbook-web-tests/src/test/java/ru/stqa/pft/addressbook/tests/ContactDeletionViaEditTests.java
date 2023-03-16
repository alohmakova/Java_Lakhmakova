package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionViaEditTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (! app.contact ().isThereAContactToModify()) {
            app.goTo ().groupPage ();
            if (! app.group ().isThereAParticularGroup ("my_group")) {
                app.group ().create (new GroupData ().withName ("my_group"));
            }
            app.contact ().fullCreation (new ContactData ()
                    .withFirstName ("Теста").withLastName ("Тестовая").withAddress ("Ужгород").withTelHome ("786875")
                            .withEmail ("email@gmail.com").withGroup ("my_group"),
                    true, app);
        }
    }
    @Test
    public void testContactDeletion() throws Exception {
        app.goTo ().homePage ();
        Contacts before = app.contact ().all ();
        ContactData modifiedContact = before.iterator ().next ();
        app.contact ().modifyAndDelete (modifiedContact);
        app.goTo ().homePage ();
        assertEquals (app.contact ().count (), before.size () - 1);
        Contacts after = app.contact ().all ();
        //assertEquals (after.size (), before.size () - 1);

        assertThat (after, equalTo (before.without (modifiedContact)));
    }

}

