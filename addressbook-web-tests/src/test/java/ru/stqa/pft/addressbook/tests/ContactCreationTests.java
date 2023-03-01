package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAParticularGroup ()) {
      app.getGroupHelper().createGroupToAddNewContact(new GroupData("test", null, null));
      }
    app.getNavigationHelper().goToAddPage();
    app.getContactHelper().fillAndSubmitContactForm(new ContactData ("Лидия", "Карпова", "Самара", "+79057590236", "ivanova@gmail.com", "test"));
    app.getNavigationHelper().returnToHomePage();
  }

}
