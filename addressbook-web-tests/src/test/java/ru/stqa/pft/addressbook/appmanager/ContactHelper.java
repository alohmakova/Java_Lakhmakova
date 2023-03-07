package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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


    public void initContactModification(int index) {
        wd.findElements (By.xpath ("//img[@alt='Edit']")).get (index).click ();
    }

    public void submitContactModification() {
        click (By.xpath ("//input[22]"));
    }

    public void selectContact(int index) {
        wd.findElements (By.name ("selected[]")).get (index).click ();
    }

    public void DeleteContact() {
        click (By.xpath ("//input[@value='Delete']"));
    }

    public boolean isThereAContact() {
        return isElementPresent (By.name ("selected[]"));
    }

    public boolean isThereAContactToModify() {
        return isElementPresent (By.xpath ("//img[@alt='Edit']"));
    }

    public void selectContactAndDelete(int index) {
        selectContact (index);
        DeleteContact ();
        pressOk ();
    }

    public void fillAndSubmitContactForm(ContactData contact) {
        fillContactForm (contact, true);
        submitContactForm ();
    }

    public void initContactModificationAndDelete(int index) {
        initContactModification (index);
        DeleteContact ();
    }

    public void initFillSubmitContactForm(ContactData contact, int index) {
        initContactModification (index);
        fillContactForm (contact, false);
        submitContactModification ();
    }

    public int getContactCount() {

        return wd.findElements (By.name ("selected[]")).size ();
    }

    public void fullContactCreationProcess(ContactData contact, boolean creation, ApplicationManager app) {
        app.getNavigationHelper ().goToAddPage ();
        fillContactForm (contact, creation);
        submitContactForm ();
        app.getNavigationHelper ().returnToHomePage ();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData> ();
        List<WebElement> elements = wd.findElements (By.xpath ("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("./td[3]")).getText();
            String firstName = element.findElement(By.xpath("./td[2]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String telMobile = element.findElement(By.xpath("./td[5]")).getText();
            String email = element.findElement(By.xpath("./td[6]")).getText();
            ContactData contact = new ContactData (firstName, lastName, address, telMobile, email, null);
            contacts.add (contact);
        }
        return contacts;

    }



}

