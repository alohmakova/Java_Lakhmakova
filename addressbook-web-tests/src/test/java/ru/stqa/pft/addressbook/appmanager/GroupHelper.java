package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

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

    public WebElement deleteSelectedGroups() {
      return wd.findElement(By.name("delete"));
    }

    public void selectGroup(int index) {//Здесь в качестве параметра индекс, то есть порядковый номер элемента

        wd.findElements(By.name("selected[]")).get(index).click ();
    }
    private void selectGroupById(int id) {//Здесь в качестве параметра идентификатор
        wd.findElement(By.cssSelector ("input[value='"+ id + "']")).click (); //здесь должен искаться 1 элемент - обрати внимание!
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }
    public void modify(int index, GroupData group) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }
    public void modify(GroupData group) {
        selectGroupById (group.getId ());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }
    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups ().click();
        returnToGroupPage();
    }
    public void delete(GroupData group) {
        selectGroupById(group.getId ());
        deleteSelectedGroups ().click();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name ("selected[]"));
    }

    public boolean isThereAParticularGroup(String title) {
        return isElementPresent(By.xpath("//*[@id=\"content\"]/form/span/input[@title='Select (my_group)']"));
    }

    public int getGroupCount() {
       return wd.findElements(By.name ("selected[]")).size ();
    }

    public List<GroupData> list() { //список
        List<GroupData> groups = new ArrayList<GroupData> ();
        List<WebElement> elements = wd.findElements (By.cssSelector ("span.group"));
        for (WebElement element : elements) {
            String name = element.getText ();
            int id = Integer.parseInt (element.findElement (By.tagName ("input")).getAttribute ("value"));
            groups.add (new GroupData ().withId (id).withName (name));
        }
        return groups;
    }
    private Groups groupCache = null;
    public Groups all() { //множество
        if (groupCache != null){
            return new Groups (groupCache);
        }
        groupCache = new Groups ();
        List<WebElement> elements = wd.findElements (By.cssSelector ("span.group"));
        for (WebElement element : elements) {
            String name = element.getText ();
            int id = Integer.parseInt (element.findElement (By.tagName ("input")).getAttribute ("value"));
            groupCache.add (new GroupData ().withId (id).withName (name));
        }
        return new Groups (groupCache);
    }

    /*public int getAParticularGroupCount() {
        return wd.findElements(By.xpath("//*[@id=\"content\"]/form/span/input[@title='Select (test)']")).size ();
    }*/
    //сколько групп именно с таким названием на странице Groups
}
