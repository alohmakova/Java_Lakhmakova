package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionViaSelectTests extends TestBase {
       @Test
    public void testContactDeletion() throws Exception {
        app.getContactHelper().selectContact("1");
        app.getContactHelper().DeleteContact();
        app.getContactHelper().pressOk();
    }
}
