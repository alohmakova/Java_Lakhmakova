package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToPage("http://localhost/addressbook/edit.php", By.linkText("add new"));
    app.getContactHelper().fillContactForm(new ContactData("Лидия", "Иванова", "Самара", "+79057590236", "ivanova@gmail.com"));
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().goToPage("http://localhost/addressbook/index.php", By.linkText("home page"));
  }

}
