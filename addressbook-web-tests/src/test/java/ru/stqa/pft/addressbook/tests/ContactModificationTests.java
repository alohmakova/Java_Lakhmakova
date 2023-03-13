package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.contact ().isThereAContactToModify ()) {
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Лидия").withLastName ("Иванова").withAddress ("Самара")
                            .withTelMobile ("+7856300000").withEmail ("ivanova@gmail.com").withGroup ("[none]"),
                    true, app);
        }
    }

    @Test
    public void testContactModification() throws Exception {

        app.goTo ().homePage ();
        Set<ContactData> before = app.contact ().all ();
        ContactData modifiedContact = before.iterator ().next ();
        ContactData contact = new ContactData ()
                .withId (modifiedContact.getId ()).withFirstName ("Михаил").withLastName ("Добряков")
                .withAddress ("Солнечная 1/5").withTelMobile ("+7880001111").withEmail ("dobro@gmail.com").withGroup ("[none]");
        app.contact ().modify (contact, false);
        app.contact ().returnToHomePage ();
        Set<ContactData> after = app.contact ().all ();
        Assert.assertEquals (after.size (), before.size ());

        before.remove (modifiedContact);
        before.add (contact);
        Assert.assertEquals (before, after);
    }
}
