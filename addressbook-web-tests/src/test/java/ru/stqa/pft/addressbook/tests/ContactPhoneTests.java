package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {

        if (! app.contact ().isThereAContactToModify()) {
            app.goTo ().groupPage ();
            if (! app.group ().isThereAParticularGroup ("my_group")) {
                app.group ().create (new GroupData ().withName ("my_group"));
            }
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Теста").withLastName ("Тестовая").withAddress ("Ужгород").withTelHome ("111")
                            .withTelMobile ("+79057590236").withTelWork ("6666").withEmail ("email@gmail.com").withGroup ("my_group"),
                    true, app);
        }
    }
    @Test
    public void testContactPhones() {
        app.goTo ().homePage ();
        ContactData contact = app.contact ().all ().iterator ().next ();
        ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm (contact);

        assertThat (contact.getTelMobile(), equalTo (cleaned(contactInfoFromEditForm.getTelMobile())));
        assertThat (contact.getTelHome(), equalTo (cleaned(contactInfoFromEditForm.getTelHome())));
        assertThat (contact.getTelWork(), equalTo (cleaned(contactInfoFromEditForm.getTelWork())));
    }
    public String cleaned (String phone) {
        return phone.replaceAll ("\\s","").replaceAll ("[-()]","");
    }
}
