package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {

        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public WebElement DeleteSelectedGroups() {
      return wd.findElement(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click ();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name ("selected[]"));
    }

    public boolean isThereAParticularGroup() {
        return isElementPresent(By.xpath("//*[@id=\"content\"]/form/span/input[@title='Select (my_group)']"));
    }

    public void createGroupToAddNewContact(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
    }

    public int getGroupCount() {
       return wd.findElements(By.name ("selected[]")).size ();
    }

    /*public int getAParticularGroupCount() {
        return wd.findElements(By.xpath("//*[@id=\"content\"]/form/span/input[@title='Select (test)']")).size ();
    }*/
    //сколько групп именно с таким названием на странице Groups
}
