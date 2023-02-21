package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToPage("add new", "http://localhost/addressbook/edit.php");
    app.getContactHelper().fillContactForm(new ContactData("Лидия", "Иванова", "Самара", "+79057590236", "ivanova@gmail.com"));
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().goToPage("home page", "http://localhost/addressbook/index.php");
  }

}
