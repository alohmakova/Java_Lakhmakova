package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private JavascriptExecutor js;

    public void init() {

        wd = new ChromeDriver();
        js = (JavascriptExecutor) wd;
        wd.get("http://localhost/addressbook/index.php");

        //чтобы GroupHelper мог получить доступ к WebDriver wd - создать конструктор, поместить его после инициализации
        //т.е. GroupHelper должен конструироваться внутри метода init
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
