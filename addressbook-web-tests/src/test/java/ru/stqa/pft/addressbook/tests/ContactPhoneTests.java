package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @Test
    public void testContactPhones() {
        app.goTo ().homePage ();
        ContactData contact = app.contact ().all ().iterator ().next ();
        ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm (contact);

        assertThat (contact.getTelHome(), equalTo (cleaned(contactInfoFromEditForm.getTelHome())));
        assertThat (contact.getTelMobile(), equalTo (cleaned(contactInfoFromEditForm.getTelMobile())));
        assertThat (contact.getTelWork(), equalTo (cleaned(contactInfoFromEditForm.getTelWork())));
    }
    public String cleaned (String phone) {
        return phone.replaceAll ("\\s","").replaceAll ("[-()]","");
    }
}
