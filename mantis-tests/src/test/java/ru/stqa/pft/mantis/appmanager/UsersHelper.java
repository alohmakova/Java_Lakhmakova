package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import javax.swing.*;
import java.awt.*;

public class UsersHelper extends HelperBase {


    public UsersHelper(ApplicationManager app) {
        super (app);
    }


    public void login(String login, String password) {
        Dimension size = new Dimension(1936, 1216);
        wd.manage().window().setSize(size);
        wd.get("http://localhost/mantisbt-2.25.6/login_page.php");
        type (By.name ("username"), login);
        click (By.xpath ("//input[@value='Login']"));
        type (By.name ("password"), password);
        click (By.xpath ("//input[@value='Login']"));

//        wd.findElement(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr/td/a")).click();
//        driver.get("http://localhost/mantisbt-2.25.6/manage_user_edit_page.php?user_id=1");
//        driver.findElement(By.linkText("Manage Users")).click();
//        driver.get("http://localhost/mantisbt-2.25.6/manage_user_page.php");
//        driver.findElement(By.linkText("efomna")).click();
//        driver.get("http://localhost/mantisbt-2.25.6/manage_user_edit_page.php?user_id=2");
//        driver.findElement(By.xpath("//input[@value='Reset Password']")).click();
//        driver.get("http://localhost/mantisbt-2.25.6/manage_user_reset.php");
//        driver.get("http://localhost/mantisbt-2.25.6/manage_user_page.php");

    }

    public void manageUsers() {
        click (By.xpath ("//*[@id=\"sidebar\"]/ul/li[6]/a/span"));
        click (By.linkText ("Manage Users"));
    }

    public void selectUser() {

    }
}
