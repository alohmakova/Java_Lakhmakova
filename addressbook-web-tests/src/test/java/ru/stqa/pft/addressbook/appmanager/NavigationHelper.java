package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper {

private WebDriver wd;
    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
      wd.get("http://localhost/addressbook/group.php");
    }
}
