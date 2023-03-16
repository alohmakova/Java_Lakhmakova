package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        ContactData contact = app.contact ().all ().iterator ().next ();//только allPhones - все телефоны слеплены как единое целое
        ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm (contact);//отдельные атрибуты для телефонов и пустой allPhones

        assertThat (contact.getAllPhones(), equalTo (mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList (contact.getTelHome (), contact.getTelMobile (), contact.getTelWork ())
                .stream ().filter ((s) -> ! s.equals (""))
                .map (ContactPhoneTests::cleaned)
                .collect (Collectors.joining ("\n"));
    }

    public static String cleaned (String phone) {
        return phone.replaceAll ("\\s","").replaceAll ("[-()]","");
    }
}
