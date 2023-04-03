package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo ().homePage ();
        app.contact ().selectNone();
        if (app.contact ().count () == 0) {
            File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Гаврила").withLastName ("Задорин").withAddress ("Тюмень").withTelHome ("786875")
                    .withPhoto (photo).withEmail ("email@gmail.com"),
                    true, app);
        }

        if (app.db().groups ().size () == 0) {
            app.goTo ().groupPage ();
            app.group ().create (new GroupData ().withName ("test 0"));
        }
    }

    @Test
    public void testAddToGroup() throws Exception {
        app.goTo ().homePage ();
        //Contacts before = app.db().contacts ();
        app.contact ().selectNone();
        Contacts withoutGroup = app.contact ().all ();
        ContactData selectedContact = withoutGroup.iterator ().next ();
        app.contact ().selectContactById (selectedContact.getId ());//выбрать контакт при помощи чек бокса
        app.contact ().addToGroup ();
        Groups g = app.db().groups ();
        GroupData selectedGroup = g.iterator ().next ();
        app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
        Groups groupToAddContact = app.db().groupToAddContact ();
        //assertEquals (app.contact ().count (), before.size ());
        assertThat (groupToAddContact, equalTo (selectedContact.getGroups ().withAdded (selectedGroup)));
        //verifyContactListInUI ();
        System.out.println (groupToAddContact);
        System.out.println (selectedContact);
        System.out.println (selectedGroup);
    }

}

