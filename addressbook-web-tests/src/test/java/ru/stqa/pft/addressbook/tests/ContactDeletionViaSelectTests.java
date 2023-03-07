package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionViaSelectTests extends TestBase {
       @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()) {
              app.getNavigationHelper().gotoGroupPage();
            if (! app.getGroupHelper().isThereAParticularGroup ("my_group")) {
                app.getGroupHelper().createGroup(new GroupData("my_group", null, null));
            }
            app.getContactHelper().fullContactCreationProcess(new ContactData ("Мария", "Тестовая", "Пхукет", "+79057590236", "mail@gmail.com", "my_group"), true, app);
           }
           List<ContactData> before = app.getContactHelper ().getContactList();
        app.getContactHelper().selectContactAndDelete (before.size () - 1);
           app.getNavigationHelper().goHome ();
           List<ContactData> after = app.getContactHelper ().getContactList();
           Assert.assertEquals (after.size (), before.size () - 1);
    }
}
