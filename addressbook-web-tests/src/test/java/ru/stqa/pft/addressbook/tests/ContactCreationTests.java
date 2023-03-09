package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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
      ContactData contact = new ContactData ("Василиса", "Мудрая", "СПб", "+79059059055", "happy@gmail.com", "my_group");
    app.getContactHelper().fillAndSubmitContactForm(contact);
    app.getNavigationHelper().returnToHomePage();
    //int after = app.getContactHelper().getContactCount();
   List<ContactData> after = app.getContactHelper ().getContactList();
    //Assert.assertEquals (after, before + 1);
    System.out.println (app.getContactHelper ().getContactList());
   Assert.assertEquals (after.size (), before.size () + 1);

//      int max = 0;
//      for (ContactData c : after) {
//          if (c.getId() > max ) {
//              max = c.getId();
//          }
//      }
//      contact.setId(max);
      contact.setId(after.stream ().max ((o1, o2) -> Integer.compare (o1.getId (), o2.getId ())).get ().getId ());
      before.add (contact);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId (), g2.getId ());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals (before, after);
  }

}
