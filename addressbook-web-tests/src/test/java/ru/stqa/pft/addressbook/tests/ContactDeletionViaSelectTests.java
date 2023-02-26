package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionViaSelectTests extends TestBase {
       @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()) {
               app.getNavigationHelper().goToAddPage();
               if (! app.getGroupHelper().isThereAGroupONAddPage()) {
                   app.getNavigationHelper().gotoGroupPage();
                   app.getGroupHelper().createGroupToAddNewContact(new GroupData("test", null, null));
                   app.getNavigationHelper().goToAddPage();
               }
               app.getContactHelper().fillAndSubmitContactForm();
               app.getNavigationHelper().returnToHomePage();
           }
        app.getContactHelper().selectContactAndDelete ();
    }
}
