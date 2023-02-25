package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {

        //app.getContactHelper().selectContact().click(); надо ли сначала выбрать контакт?
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Павел", "Майков", "Клинцы", "+79050357261", "emzil@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
