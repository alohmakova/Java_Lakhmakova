package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private JavascriptExecutor js;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties ();
    }

    public void init() throws IOException {
        String target = System.getProperty ("target", "local");
        properties.load (new FileReader (new File (String.format ("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper ();
        if("".equals (properties.getProperty ("selenium.server"))) {

            if (browser.equals (BrowserType.CHROME)) {
                wd = new ChromeDriver ();
            } else if (browser.equals (BrowserType.FIREFOX)) {
                wd = new FirefoxDriver ();
            } else if (browser.equals (BrowserType.IE)) {
                wd = new InternetExplorerDriver ();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities ();
            capabilities.setBrowserName (browser);
            capabilities.setPlatform (Platform.fromString (System.getProperty ("platform", "Windows 11")));
            wd = new RemoteWebDriver (new URL (properties.getProperty ("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       //ожидание, чтобы проверить, если какие-то элементы на странице появляются позже, потому что долго загружаются
        js = (JavascriptExecutor) wd;
        wd.get(properties.getProperty ("web.baseUrl"));

        //чтобы GroupHelper мог получить доступ к WebDriver wd - создать конструктор, поместить его после инициализации
        //т.е. GroupHelper должен конструироваться внутри метода init
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty ("web.adminLogin"), properties.getProperty("web.adminPassword"));

    }



    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
    public DbHelper db() {
        return dbHelper;
    }
}
