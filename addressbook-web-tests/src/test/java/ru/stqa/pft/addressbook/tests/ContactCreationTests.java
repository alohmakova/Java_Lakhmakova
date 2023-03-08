package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    //int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper ().getContactList();
    System.out.println (app.getContactHelper ().getContactList());
    app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAParticularGroup ("my_group")) {
      app.getGroupHelper().createGroup(new GroupData("my_group", null, null));
      }
    app.getNavigationHelper().goToAddPage();
      ContactData contact = new ContactData (before.get(before.size () - 1).getId (),"Василиса", "Мудрая", "СПб", "+79059059055", "happy@gmail.com", "my_group");
    app.getContactHelper().fillAndSubmitContactForm(contact);
    app.getNavigationHelper().returnToHomePage();
    //int after = app.getContactHelper().getContactCount();
   List<ContactData> after = app.getContactHelper ().getContactList();
    //Assert.assertEquals (after, before + 1);
    System.out.println (app.getContactHelper ().getContactList());
   Assert.assertEquals (after.size (), before.size () + 1);

      int max = 0;
      for (ContactData c : after) {
          if (c.getId() > max ) {
              max = c.getId();
          }
      }
      contact.setId(max);
      before.add (contact);
      Assert.assertEquals (new HashSet<Object> (before), new HashSet<Object> (after));
  }

}
