package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.contact ().isThereAContactToModify ()) {
            app.contact ().fullCreation (new ContactData
                            ("Лидия", "Иванова", "Самара", "+79057590236", "ivanova@gmail.com", "[none]"),
                    true, app);
        }
    }

    @Test
    public void testContactModification() throws Exception {

        app.goTo ().homePage ();
        List<ContactData> before = app.contact ().list ();
        int index = before.size () - 1;
        ContactData contact = new ContactData ("Михаил", "Добряков", "Уфа", "+79050357261", "dobro@gmail.com", "[none]");
        app.contact ().modify (index, contact, false);
        app.goTo ().returnToHomePage ();
        List<ContactData> after = app.contact ().list ();
        Assert.assertEquals (after.size (), before.size ());

        before.remove (index);
        before.add (contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId (), g2.getId ());
        before.sort (byId);
        after.sort (byId);
        Assert.assertEquals (before, after);
    }
}
