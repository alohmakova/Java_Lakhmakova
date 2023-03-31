package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase {
    @Test
    public void testRemoveFromGroup() throws Exception {
        app.goTo ().homePage ();
        Groups g = app.db().groups ();
        GroupData selectedGroup = g.iterator ().next ();
        app.contact ().toGroupList ();
        app.contact ().selectGroupFromList (selectedGroup.getId ());
        //Contacts before = app.db().contacts ();
        Contacts before = app.contact ().all ();
        ContactData selectedContact = before.iterator ().next ();
        app.contact ().selectContactById1 (selectedContact.getId ());//выбрать контакт при помощи чек бокса???
        app.contact ().removeFromGroup ();
        app.contact ().goToPaticularGroupPage (selectedGroup.getName ());
//        assertEquals (app.contact ().count (), before.size () - 1);
        Contacts after = app.contact ().all ();
        assertThat (after, equalTo (before.without (selectedGroup)));
        //verifyContactListInUI ();
    }
}
