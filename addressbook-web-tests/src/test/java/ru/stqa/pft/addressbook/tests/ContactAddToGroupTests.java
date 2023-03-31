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
        File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
        if (app.db().contacts ().size () == 0) {
            app.contact ().fullCreation (new ContactData ()
                            .withFirstName ("Теста").withLastName ("Тестовая").withAddress ("Ужгород").withTelHome ("786875")
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
        Contacts before = app.db().contacts ();
        ContactData selectedContact = before.iterator ().next ();
        Groups g = app.db().groups ();
        GroupData selectedGroup = g.iterator ().next ();
        app.contact ().selectContactById (selectedContact.getId ());;//выбрать контакт при помощи чек бокса
        app.contact ().toGroupList ();
        app.contact ().selectGroup (selectedGroup.getId ());
        app.contact ().addToGroup ();
        app.contact ().goToPaticularGroupPage (selectedGroup.getName ());
//        assertEquals (app.contact ().count (), before.size () - 1);
        Contacts after = app.db().contacts ();
        assertThat (after, equalTo (before.without (selectedGroup)));
        //verifyContactListInUI ();
    }

}
