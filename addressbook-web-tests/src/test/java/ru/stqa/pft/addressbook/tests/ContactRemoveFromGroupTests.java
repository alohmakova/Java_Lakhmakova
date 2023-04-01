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

public class ContactRemoveFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo ().groupPage ();
        if (app.db ().groups ().size () == 0) {
            app.group ().create (new GroupData ().withName ("test 0"));
            app.goTo ().homePage ();
            if (app.contact ().count () == 0) {
                File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
                app.contact ().fullCreation (new ContactData ()
                                .withFirstName ("Сегодня").withLastName ("Созданный").withAddress ("Воронеж").withTelHome ("786875")
                                .withPhoto (photo).withEmail ("email@gmail.com"),
                        true, app);
                app.goTo ().homePage ();
            }
            Contacts withoutGroup = app.contact ().all ();
            ContactData selectedContact = withoutGroup.iterator ().next ();
            app.contact ().selectContactById (selectedContact.getId ());
            app.contact ().addToGroup ();
            Groups g = app.db ().groups ();
            GroupData selectedGroup = g.iterator ().next ();
            app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
        } else {
            app.goTo ().homePage ();
            if (app.contact ().count () != 0) {
                app.contact ().selectNone ();
                if (app.contact ().count () == 0) {
                    Groups g = app.db ().groups ();
                    GroupData selectedGroup = g.iterator ().next ();
                    app.contact ().selectGroupFromList (selectedGroup.getId ());
                } else {
                    app.contact ().selectAll ();
                    app.contact ().addToGroup ();
                    Groups after = app.db ().groups ();
                    GroupData selectedGroup = after.iterator ().next ();
                    app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
                }

            } else {
                File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
                app.contact ().fullCreation (new ContactData ()
                                .withFirstName ("Сегодня").withLastName ("Созданный").withAddress ("Воронеж").withTelHome ("786875")
                                .withPhoto (photo).withEmail ("email@gmail.com"),
                        true, app);
                app.goTo ().homePage ();
                Contacts withoutGroup = app.contact ().all ();
                ContactData selectedContact = withoutGroup.iterator ().next ();
                app.contact ().selectContactById (selectedContact.getId ());
                app.contact ().addToGroup ();
                Groups g = app.db ().groups ();
                GroupData selectedGroup = g.iterator ().next ();
                app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
            }
        }
    }
        @Test
        public void testRemoveFromGroup() throws Exception {
//            Contacts before = app.contact ().all ();
            Contacts withoutGroup = app.contact ().all ();
            ContactData selectedContact = withoutGroup.iterator ().next ();
            app.contact ().selectContactById (selectedContact.getId ());
            app.contact ().removeFromGroup ();
            Groups g = app.db ().groups ();
            GroupData selectedGroup = g.iterator ().next ();
            app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
//        assertEquals (app.contact ().count (), before.size () - 1);
//        Contacts after = app.contact ().all ();
//        assertThat (after, equalTo (before.withAdded(selectedContact)));
            //verifyContactListInUI ();
        }
    }

