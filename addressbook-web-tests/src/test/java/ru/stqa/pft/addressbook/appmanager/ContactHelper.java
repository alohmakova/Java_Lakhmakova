package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

            public ContactHelper(WebDriver wd) { super(wd); }

    public void submitContactForm() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getTelMobile());
        type(By.name("email"), contactData.getEmail());
    }


    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void selectContact(String id) {
        click(By.id(id));
    }

    public void DeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
}
