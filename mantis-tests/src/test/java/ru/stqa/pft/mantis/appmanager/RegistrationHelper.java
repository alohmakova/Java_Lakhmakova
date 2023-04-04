package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {

    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get (app.getProperty ("web.baseUrl") + "/signup_page.php");
        wd.findElement (By.name ("username")).sendKeys (username);

    }
}
