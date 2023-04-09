package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo ().homePage ();
        app.contact ().selectNone();
        if (app.contact ().count () == 0) {
            File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Антон").withLastName ("Мишутин").withAddress ("Волгоград").withTelHome ("4546576")
                    .withPhoto (photo).withEmail ("ytytrt@gmail.com"),
                    true, app);
        }

        if (app.db().groups ().size () == 0) {
            app.goTo ().groupPage ();
            app.group ().create (new GroupData ().withName ("example"));
        }
    }

    @Test
    public void testAddToGroup() throws Exception {
        app.goTo ().homePage ();
        app.contact ().selectNone();
        Contacts withoutGroup = app.contact ().all ();
        ContactData selectedContact = withoutGroup.iterator ().next ();
        app.contact ().selectContactById (selectedContact.getId ());//выбрать контакт при помощи чек бокса
        Groups g = app.db().groups ();
        GroupData selectedGroup = g.iterator ().next ();
        app.contact ().toGroupList();
        app.contact ().selectGroup(selectedGroup.getId ());
        app.contact ().addToGroup ();
        app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
        Groups groupsOfAddedContact = app.db().groupsOfAddedContact (selectedContact.getId ());

//        assertTrue(app.contact ().isContactPresentInTheGroup (selectedContact.getId ()),
//                    "Контакт " + selectedContact.getFirstName () + " " + selectedContact.getLastName () + " " + "с id " + selectedContact.getId () +
//                            " не найден на странице группы " + selectedGroup.getName ());

        assertThat (selectedContact.getGroups ().withAdded(selectedGroup), equalTo (groupsOfAddedContact));

        assertFalse (app.db().contactAddedToGroup (selectedContact.getId (), selectedGroup.getId ()),
                "Контакт " + selectedContact.getFirstName () + " " + selectedContact.getLastName () + " " + "с id " + selectedContact.getId () +
                          " не найден на странице группы " + selectedGroup.getName ());
    }

}

