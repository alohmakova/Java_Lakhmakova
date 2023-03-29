package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        //if (app.db().contacts ().size () == 0) {
            if (!app.contact ().isThereAContactToModify ()) {
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Лидия").withLastName ("Иванова").withAddress ("Самара").withTelHome ("786875")
                            .withTelMobile("+989").withTelWork ("2222").withEmail ("ivanova@gmail.com").withGroup ("[none]"),
                    true, app);
        }
    }

    @Test
    public void testContactModification() throws Exception {

        app.goTo ().homePage ();
        Contacts before = app.contact ().all ();
        ContactData modifiedContact = before.iterator ().next ();
        ContactData contact = new ContactData ()
                .withId (modifiedContact.getId ()).withFirstName ("Михаил").withLastName ("Добряков").withAddress ("Гродно").withTelMobile ("+7880001111").withEmail ("dobro@gmail.com").withEmail2 ("utro@gmail.com").withEmail3 ("sunny@gmail.com").withGroup ("[none]");
        app.contact ().modify (contact, false);
        app.contact ().returnToHomePage ();
        assertEquals (app.contact ().count (), before.size ());
        Contacts after = app.contact ().all ();
        //assertEquals (after.size (), before.size ());

        assertThat (after, equalTo (before.without (modifiedContact).withAdded (contact)));
    }
}
