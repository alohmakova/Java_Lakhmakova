package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        if (!app.getContactHelper ().isThereAContactToModify ()) {
            app.getContactHelper ().fullContactCreationProcess (new ContactData
                            ("Лидия", "Иванова", "Самара", "+79057590236", "ivanova@gmail.com", "[none]"),
                    true, app);
        }
        List<ContactData> before = app.getContactHelper ().getContactList ();
        ContactData contact = new ContactData (before.get(before.size () - 1).getId (),"Михаил", "Добряков", "Уфа", "+79050357261", "dobro@gmail.com", "[none]");
        app.getContactHelper ().initFillSubmitContactForm ((contact), before.size () - 1);
        app.getNavigationHelper ().returnToHomePage ();
        List<ContactData> after = app.getContactHelper ().getContactList ();
        Assert.assertEquals (after.size (), before.size ());

        before.remove (before.size () - 1);
        before.add (contact);
        Assert.assertEquals (new HashSet<Object> (before), new HashSet<Object> (after));
    }
}
