package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromXml () throws IOException {
        try (BufferedReader reader = new BufferedReader (new FileReader (new File ("src/test/resources/contacts.xml")))) {
            String xml = "";
            String  line = reader.readLine ();
            while (line != null) {
                xml += line;
                line = reader.readLine ();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations (ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream ().map ((g) -> new Object[] {g}).collect (Collectors.toList ()).iterator ();
        }
    }
    @DataProvider
    public Iterator<Object[]> validContactsFromJson () throws IOException {
       try (BufferedReader reader = new BufferedReader (new FileReader (new File ("src/test/resources/contacts.json")))) {
           String json = "";
           String  line = reader.readLine ();
           while (line != null) {
               json += line;
               line = reader.readLine ();
           }
           Gson gson = new Gson();
           List<ContactData> contacts = gson.fromJson (json, new TypeToken<List<ContactData>> () {}.getType ());
           return contacts.stream ().map ((g) -> new Object[] {g}).collect (Collectors.toList ()).iterator ();
       }

    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups ().size () == 0) {
        app.goTo ().groupPage ();
        app.group ().create (new GroupData ().withName ("test 0"));
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception {
        Groups groups = app.db().groups ();
        app.goTo ().homePage ();
        Contacts before = app.db().contacts ();
        app.goTo ().addPage ();
        app.contact ().create (contact.inGroup(groups.iterator ().next ()), true, app);
        assertThat (app.contact ().count (), equalTo (before.size () + 1));
        Contacts after = app.db().contacts ();
        assertThat (after, equalTo (
                before.withAdded (contact.withId (after.stream().mapToInt ((c) -> c.getId ()).max().getAsInt()))));
        verifyContactListInUI ();
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
