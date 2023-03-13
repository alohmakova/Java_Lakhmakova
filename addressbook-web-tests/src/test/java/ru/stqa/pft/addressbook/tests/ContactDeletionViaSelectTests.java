package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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
           Set<ContactData> before = app.contact ().all ();
           ContactData deletedContact = before.iterator ().next ();
           app.contact ().delete (deletedContact);
           app.goTo ().homePage ();
           Set<ContactData> after = app.contact ().all ();
           Assert.assertEquals (after.size (), before.size()-1);

           before.remove (deletedContact);
           Assert.assertEquals (before, after);
    }
}
