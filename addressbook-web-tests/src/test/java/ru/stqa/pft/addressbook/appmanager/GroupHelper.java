package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click ();
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
        returnToGroupPage();
    }
    public void modify(int index, GroupData group) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }
    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups ().click();
        returnToGroupPage();
    }
    public boolean isThereAGroup() {
        return isElementPresent(By.name ("selected[]"));
    }

    public boolean isThereAParticularGroup(String title) {
        return isElementPresent(By.xpath("//*[@id=\"content\"]/form/span/input[@title='Select (my_group)']"));
    }

//    public void createGroupToAddNewContact(GroupData group) {
//        initGroupCreation();
//        fillGroupForm(group);
//        submitGroupCreation();
//    }

    public int getGroupCount() {
       return wd.findElements(By.name ("selected[]")).size ();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        //new ArrayList<GroupData>() - Обязательно нужно указать конкретный класс который реализует интерфейс List
        List<WebElement> elements = wd.findElements (By.cssSelector ("span.group"));
                //Найти все элементы которые имеют текст span и класс group
        for (WebElement element : elements) {
            String name = element.getText ();
            int id = Integer.parseInt (element.findElement (By.tagName ("input")).getAttribute ("value"));
            groups.add (new GroupData ().withId (id).withName (name));
        }
        return groups;
    }

    /*public int getAParticularGroupCount() {
        return wd.findElements(By.xpath("//*[@id=\"content\"]/form/span/input[@title='Select (test)']")).size ();
    }*/
    //сколько групп именно с таким названием на странице Groups
}
