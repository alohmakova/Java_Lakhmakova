package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionViaSelectTests extends TestBase {
       @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()) {
              app.getNavigationHelper().gotoGroupPage();
            if (! app.getGroupHelper().isThereAParticularGroup ("my_group")) {
                app.getGroupHelper().createGroupToAddNewContact(new GroupData("my_group", null, null));
            }
            app.getContactHelper().fullContactCreationProcess(new ContactData ("Мария", "Тестовая", "Пхукет", "+79057590236", "mail@gmail.com", "my_group"), true, app);
           }
           int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContactAndDelete (before - 1);
           app.getNavigationHelper().goHome ();
           int after = app.getContactHelper().getContactCount();
           Assert.assertEquals (after, before - 1);
    }
}
