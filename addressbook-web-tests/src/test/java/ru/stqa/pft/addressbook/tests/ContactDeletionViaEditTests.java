package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionViaEditTests extends TestBase{
    @Test
    public void testContactDeletion() throws Exception {
        app.getContactHelper().initContactModification();
        app.getContactHelper().DeleteContact();
    }

}

