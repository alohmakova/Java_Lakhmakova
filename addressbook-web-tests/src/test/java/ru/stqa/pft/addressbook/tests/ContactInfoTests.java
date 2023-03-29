package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
        if (app.db().contacts ().size () == 0) {
            if (app.db().groups ().size () == 0) {
                app.goTo ().groupPage ();
                app.group ().create (new GroupData ().withName ("group"));
            }
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Сравнение").withLastName ("Сравнительное").withAddress ("Ужгород").withTelHome ("786875")
                            .withPhoto (photo).withTelMobile ("+989").withTelWork ("2222").withEmail ("email@gmail.com").withGroup ("test 0"),
                    true, app);
        }
    }
    @Test
    public void testContactPhones() {
        app.goTo ().homePage ();
        ContactData contact = app.contact ().all ().iterator ().next ();
        ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm (contact);

        assertThat (contact.getAllPhones(), equalTo (mergePhones(contactInfoFromEditForm)));
        assertThat (contact.getAllEmails(), equalTo (mergeEmails (contactInfoFromEditForm)));
        assertThat (contact.getAddress (), equalTo (contactInfoFromEditForm.getAddress ()));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList (contact.getTelHome (), contact.getTelMobile (), contact.getTelWork ())
                .stream ().filter ((s) -> ! s.equals (""))
                .map (ContactInfoTests::cleaned)
                .collect (Collectors.joining ("\n"));
    }

    public static String cleaned (String phone) {
        return phone.replaceAll ("\\s","").replaceAll ("[-()]","");
    }
    private String mergeEmails(ContactData contact) {
        return Arrays.asList (contact.getEmail (), contact.getEmail2 (), contact.getEmail3 ())
                .stream ().filter ((s) -> ! s.equals (""))
                .collect (Collectors.joining ("\n"));
    }
}
