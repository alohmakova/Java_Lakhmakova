package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionViaSelectTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (! app.contact ().isThereAContactToModify()) {
            app.goTo ().groupPage ();
            if (! app.group ().isThereAParticularGroup ("my_group")) {
                app.group ().create (new GroupData ().withName ("my_group"));
            }
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Теста").withLastName ("Тестовая").withAddress ("Ужгород")
                            .withTelMobile ("+79057590236").withEmail ("email@gmail.com").withGroup ("my_group"),
                    true, app);
        }
    }
       @Test
    public void testContactDeletion() throws Exception {
           app.goTo().homePage();
           Contacts before = app.contact ().all ();
           ContactData deletedContact = before.iterator ().next ();
           app.contact ().delete (deletedContact);
           app.goTo ().homePage ();
           Contacts after = app.contact ().all ();
           assertThat (after.size (), equalTo (before.size()-1));

           assertThat (after, equalTo (before.without (deletedContact)));
    }
}
