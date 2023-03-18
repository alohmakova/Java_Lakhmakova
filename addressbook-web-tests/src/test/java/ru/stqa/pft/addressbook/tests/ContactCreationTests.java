package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo ().groupPage ();
        if (!app.group ().isThereAParticularGroup ("my_group")) {
            app.group ().create (new GroupData ().withName ("my_group"));
        }
    }

    @Test
    public void testContactCreation() throws Exception {
        app.goTo ().homePage ();
       Contacts before = app.contact ().all ();
        app.goTo ().addPage ();
        File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
        ContactData contact = new ContactData ()
                .withFirstName ("Паулина").withLastName ("Сборовска").withAddress ("Рабочая 2").withTelMobile ("+989")
                .withTelWork ("2222").withEmail ("test@mail.com").withEmail3 ("merge@mail.ru").withPhoto (photo).withGroup ("my_group");
        app.contact ().create (contact, true, app);
        assertThat (app.contact ().count (), equalTo (before.size () + 1));
        Contacts after = app.contact ().all ();
        //assertThat (after.size (), equalTo (before.size () + 1));
        assertThat (after, equalTo (
                before.withAdded (contact.withId (after.stream().mapToInt ((c) -> c.getId ()).max().getAsInt()))));
    }
//    @Test
//    public void testCurrentDir(){
//        File currentDir = new File (".");
//        System.out.println (currentDir.getAbsolutePath ());
//        File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
//        System.out.println (photo.getAbsolutePath ());
//        System.out.println (photo.exists ());
//    }
}
