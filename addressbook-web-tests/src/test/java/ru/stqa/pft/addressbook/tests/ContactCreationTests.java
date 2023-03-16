package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo ().groupPage ();
        if (!app.group ().isThereAParticularGroup ("my_group")) {
            app.group ().create (new GroupData ().withName ("my_group"));
        }
    }

    @Test
    public void testContactCreation() throws Exception {
        app.goTo ().homePage ();
       Contacts before = app.contact ().all ();
        app.goTo ().addPage ();
        ContactData contact = new ContactData ()
                .withFirstName ("Паулина").withLastName ("Сборовска").withAddress ("Рабочая 2")
                .withTelMobile ("+9809").withTelWork ("2222").withEmail ("test@mail.com").withEmail3 ("merge@mail.ru").withGroup ("my_group");
        app.contact ().create (contact, true, app);
        assertThat (app.contact ().count (), equalTo (before.size () + 1));
        Contacts after = app.contact ().all ();
        //assertThat (after.size (), equalTo (before.size () + 1));
        assertThat (after, equalTo (
                before.withAdded (contact.withId (after.stream().mapToInt ((c) -> c.getId ()).max().getAsInt()))));
    }

}
