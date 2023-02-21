package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {

        click(By.linkText("groups"));
    }

    public void goToPage(String page_name, String url) {
      wd.findElement(By.linkText(page_name)).click();
      wd.get(url);
    }
}
