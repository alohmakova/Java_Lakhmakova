package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        if (! app.getContactHelper().isThereAContactToModify()) {
            app.getNavigationHelper().goToAddPage();
            app.getContactHelper().fillContactForm(new ContactData("Лидия", "Иванова", "Самара", "+79057590236", "ivanova@gmail.com", "[none]"), true);
            app.getContactHelper().submitContactForm();
            app.getNavigationHelper().returnToHomePage();
        }
        app.getContactHelper().initFillSubmitContactForm(new ContactData("Михаил", "Добряков", "Уфа", "+79050357261", "dobro@gmail.com", "[none]"));
        app.getNavigationHelper().returnToHomePage();
    }
}
