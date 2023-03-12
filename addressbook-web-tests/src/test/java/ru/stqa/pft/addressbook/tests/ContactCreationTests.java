package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.contact ().list ();
        app.goTo ().addPage ();
        ContactData contact = new ContactData ("Проверка", "Тест", "СПб", "+79059059055", "happy@gmail.com", "my_group");
        app.contact ().create (contact, true, app);
        List<ContactData> after = app.contact ().list ();
        Assert.assertEquals (after.size (), before.size () + 1);

        contact.setId (after.stream ().max ((o1, o2) -> Integer.compare (o1.getId (), o2.getId ())).get ().getId ());
        before.add (contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId (), g2.getId ());
        before.sort (byId);
        after.sort (byId);
        Assert.assertEquals (before, after);
    }

}
