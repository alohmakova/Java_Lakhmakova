package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAParticularGroup ()) {
      app.getGroupHelper().createGroupToAddNewContact(new GroupData("my_group", null, null));
      }
    app.getNavigationHelper().goToAddPage();
    app.getContactHelper().fillAndSubmitContactForm(new ContactData ("Анфиса", "Счастливцева", "СПб", "+79059059055", "happy@gmail.com", "my_group"));
    app.getNavigationHelper().returnToHomePage();
  }

}
