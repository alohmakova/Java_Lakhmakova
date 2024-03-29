package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;
    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander (generator);
        try {
            jCommander.parse (args);
        } catch (ParameterException ex) {
            jCommander.usage ();
            return;
        }

        generator.run();
    }
    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals ("csv")){
            saveAsCsv (contacts, new File (file));
        } else if (format.equals ("xml")){
            saveAsXml (contacts, new File (file));
        } else if (format.equals ("json")){
            saveAsJson (contacts, new File (file));
        } else {
            System.out.println ("Unrecognized format " + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder ().setPrettyPrinting ().excludeFieldsWithoutExposeAnnotation().create ();
        String json = gson.toJson(contacts);
       try (Writer writer = new FileWriter (file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations (ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter (file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println (new File (".").getAbsolutePath ());
      try  (Writer writer = new FileWriter (file)) {
          for (ContactData contact : contacts){
              writer.write(String.format ("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                      contact.getLastName (), contact.getFirstName (), contact.getAddress (),
                      contact.getEmail (), contact.getEmail2 (), contact.getEmail3 (),
                      contact.getTelHome (), contact.getTelMobile (), contact.getTelWork (),contact.getPhoto (), contact.getGroups ()));
          }
      }
    }
    File photo = new File ("src/test/resources/2023-02-27_12-49-38.png");
    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData> ();
        for (int i = 0; i < count; i++){
            contacts.add (new ContactData().withLastName (String.format ("LastName %s", i)).withFirstName (String.format ("FirstName %s", i)).withAddress (String.format ("Address %s", i))
                    .withEmail (String.format (i + "email@mail.com")).withEmail2 (String.format (i + "email2@mail.com")).withEmail3 (String.format (i + "email3@mail.com"))
                    .withTelHome (String.format ("111" + i + "111")).withTelMobile (String.format ("222" + i + "222")).withTelWork (String.format ("333" + i + "333")).withPhoto (photo));
        }
        return contacts;
    }
}
