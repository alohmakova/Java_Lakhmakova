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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactRemoveFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo ().groupPage ();
        if (app.db ().groups ().size () == 0) {//если нет группы
            app.group ().create (new GroupData ().withName ("test 0"));
            app.goTo ().homePage ();
            if (app.contact ().count () == 0) {//если нет группы и нет контакта
                File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
                app.contact ().fullCreation (new ContactData ()
                                .withFirstName ("Валентина").withLastName ("Ивлеева").withAddress ("Москва").withTelHome ("786875")
                                .withPhoto (photo).withEmail ("email@gmail.com"),
                        true, app);
                app.goTo ().homePage ();
            }
            Contacts withoutGroup = app.contact ().all ();//если нет группы, но есть контакт
            ContactData selectedContact = withoutGroup.iterator ().next ();
            app.contact ().selectContactById (selectedContact.getId ());
            app.contact ().addToGroup ();
            Groups g = app.db ().groups ();
            GroupData selectedGroup = g.iterator ().next ();
            app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
        } else {//если есть группа
            app.goTo ().homePage ();
            if (app.contact ().count () != 0) {//если есть группа и есть контакт
                app.contact ().selectNone ();
                if (app.contact ().count () == 0) {//если все контакты уже добавлены в группы
                    Groups g = app.db ().groups ();
                    GroupData selectedGroup = g.iterator ().next ();
                    app.contact ().selectGroupFromList (selectedGroup.getId ());
                } else {//если есть контакты, которые не добавлены в группы
                    app.contact ().selectAll ();
                    Groups g = app.db().groups ();
                    GroupData selectedGroup = g.iterator ().next ();
                    app.contact ().toGroupList();
                    app.contact ().selectGroup(selectedGroup.getId ());
                    app.contact ().addToGroup ();
                    app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
                }

            } else {//если есть группа, но нет контакта
                File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
                app.contact ().fullCreation (new ContactData ()
                                .withFirstName ("Тимур").withLastName ("сомов").withAddress ("Орёл").withTelHome ("786875")
                                .withPhoto (photo).withEmail ("email@gmail.com"),
                        true, app);
                app.goTo ().homePage ();
                Contacts withoutGroup = app.contact ().all ();
                ContactData selectedContact = withoutGroup.iterator ().next ();
                app.contact ().selectContactById (selectedContact.getId ());
                Groups g = app.db().groups ();
                GroupData selectedGroup = g.iterator ().next ();
                app.contact ().toGroupList();
                app.contact ().selectGroup(selectedGroup.getId ());
                app.contact ().addToGroup ();
                app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());
            }
        }
    }
        @Test
        public void testRemoveFromGroup() throws Exception {
            Contacts contacts = app.contact ().all ();
            ContactData selectedContact = contacts.iterator ().next ();
            app.contact ().selectContactById (selectedContact.getId ());
            app.contact ().removeFromGroup ();
            Groups g = app.db ().groups ();
            GroupData selectedGroup = g.iterator ().next ();
            app.contact ().goToUsersAddedGroupPage (selectedGroup.getName ());

//            assertFalse (app.contact ().isContactPresentInTheGroup (selectedContact.getId ()),
//                    "Контакт " + selectedContact.getFirstName () + " " + selectedContact.getLastName () + " " + "с id " + selectedContact.getId () +
//                            " присутствует на странице группы " + selectedGroup.getName ());

            Groups groupsOfAddedContact = app.db().groupsOfAddedContact (selectedContact.getId ());
            assertThat (selectedContact.getGroups ().without (selectedGroup), equalTo (groupsOfAddedContact));
        }
    }

