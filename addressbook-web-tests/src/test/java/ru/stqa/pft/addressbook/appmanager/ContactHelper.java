package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super (wd);
    }

    public void submitContactForm() {
        click (By.xpath ("//input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type (By.name ("firstname"), contactData.getFirstName ());
        type (By.name ("lastname"), contactData.getLastName ());
        type (By.name ("address"), contactData.getAddress ());
        type (By.name ("mobile"), contactData.getTelMobile ());
        type (By.name ("email"), contactData.getEmail ());

        if (creation) {
            new Select (wd.findElement (By.name ("new_group"))).selectByVisibleText (contactData.getGroup ());
        } else {
            Assert.assertFalse (isElementPresent (By.name ("new_group")));
        }
    }


    public void initModification(int index) {

        wd.findElements (By.xpath ("//img[@alt='Edit']")).get (index).click ();
    }
    private void initModificationById(int id) {
        wd.findElement(By.xpath ("//a[@href='edit.php?id=" + id + "']")).click ();
        //a[@href='edit.php?id=" + id + "']
        //"input[value='" + id + "']>img[@alt='Edit']"


    }

    public void submitContactModification() {
        click (By.xpath ("//input[22]"));
    }

    public void selectContact(int index) {
        wd.findElements (By.name ("selected[]")).get (index).click ();
    }
    private void selectContactById(int id) {
        wd.findElement(By.cssSelector ("input[value='" + id + "']")).click (); //здесь должен искаться 1 элемент - обрати внимание!
    }

    public WebElement deleteContact() {
        return wd.findElement(By.xpath("//input[@value='Delete']"));
    }

    public void fullCreation(ContactData contact, boolean creation, ApplicationManager app) {
        app.goTo ().addPage ();
        fillContactForm (contact, creation);
        contactCache = null;
        submitContactForm ();
    }
    public void create(ContactData contact, boolean creation, ApplicationManager app) {
        fillContactForm (contact, true);
        submitContactForm ();
        contactCache = null;
        returnToHomePage();
    }
    public void modify(ContactData contact, boolean creation) {//это для редактирования контакта по id
        initModificationById (contact.getId ());
        fillContactForm (contact, false);
        submitContactModification ();
        contactCache = null;
    }
    public void delete(ContactData contact) {
        selectContactById (contact.getId ());
        deleteContact ().click();
        contactCache = null;
        pressOk ();
    }
    public void modifyAndDelete(ContactData contact) {//это для удаления контакта через редактирование по id
        initModificationById (contact.getId ());
        deleteContact ().click();
        contactCache = null;
    }
    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));

    }
    public void delete(int index) {
        selectContact (index);
        deleteContact ().click();
        pressOk ();
    }
    public void modify(int index, ContactData contact, boolean creation) {//это для редактирования контакта по порядковому номеру, а не по id
        initModification (index);
        fillContactForm (contact, false);
        submitContactModification ();
    }
    public boolean isThereAContact() {
        return isElementPresent (By.name ("selected[]"));
    }

    public boolean isThereAContactToModify() {
        return isElementPresent (By.xpath ("//img[@alt='Edit']"));
    }

    public int count() {

        return wd.findElements (By.name ("selected[]")).size ();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData> ();
        List<WebElement> elements = wd.findElements (By.xpath ("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String telMobile = element.findElement(By.xpath("./td[6]")).getText();
            String email = element.findElement(By.xpath("./td[5]")).getText();
            int id = Integer.parseInt (element.findElement(By.tagName ("input")).getAttribute ("value"));
            ContactData contact = new ContactData ().withId (id).withFirstName (firstName).withLastName (lastName).withAddress (address).withTelMobile (telMobile).withEmail (email);
            contacts.add (contact);
        }
        return contacts;

    }
    private Contacts contactCache = null;
    public Contacts all() {
        if (contactCache != null){
            return new Contacts (contactCache);
        }
        contactCache = new Contacts ();
        List<WebElement> rows = wd.findElements (By.name ("entry"));
        //List<WebElement> elements = wd.findElements (By.xpath ("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements (By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName ("input")).getAttribute ("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get (2).getText();
            String address = row.findElement(By.xpath("./td[4]")).getText();
            String telMobile = row.findElement(By.xpath("./td[6]")).getText();
            String email = row.findElement(By.xpath("./td[5]")).getText();
            //int id = Integer.parseInt (element.findElement(By.tagName ("input")).getAttribute ("value"));
            contactCache.add (new ContactData ().withId (id).withFirstName (firstName).withLastName (lastName).withAddress (address).withTelMobile (telMobile).withEmail (email));
        }
        return new Contacts (contactCache);

    }

    public void waitForMessage() {
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initModificationById(contact.getId());
        String firstname = wd.findElement(By.name ("firstname")).getAttribute ("value");
        String lastname = wd.findElement(By.name ("lastname")).getAttribute("value");
        String address = wd.findElement(By.name ("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name ("mobile")).getAttribute ("value");
        String work = wd.findElement(By.name ("work")).getAttribute("value");
        String email = wd.findElement(By.name ("email")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId (contact.getId()).withFirstName (firstname).withLastName (lastname).withAddress(address)
                                .withTelHome (home).withTelMobile (mobile).withTelWork (work).withEmail (email);
    }
}

