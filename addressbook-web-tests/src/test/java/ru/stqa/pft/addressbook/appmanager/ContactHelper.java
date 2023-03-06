package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

            public ContactHelper(WebDriver wd) { super(wd); }

    public void submitContactForm() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getTelMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initContactModification(int index) {
        wd.findElements (By.xpath("//img[@alt='Edit']")).get (index).click ();
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void selectContact(int index) {
        wd.findElements (By.name("selected[]")).get(index).click ();
    }

    public void DeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isThereAContactToModify() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }
    public void selectContactAndDelete(int index) {
        selectContact(index);
        DeleteContact();
        pressOk();
    }

    public void fillAndSubmitContactForm (ContactData contact) {
        fillContactForm(contact, true);
        submitContactForm();
    }

    public void initContactModificationAndDelete(int index) {
        initContactModification(index);
        DeleteContact();
    }

    public void initFillSubmitContactForm(ContactData contact, int index) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();
    }

    public int getContactCount() {
            return wd.findElements(By.name ("selected[]")).size ();
        }

    public void fullContactCreationProcess(ContactData contact, boolean creation, ApplicationManager app) {
        app.getNavigationHelper().goToAddPage();
        fillContactForm(contact, creation);
        submitContactForm();
        app.getNavigationHelper().returnToHomePage();
    }
}

