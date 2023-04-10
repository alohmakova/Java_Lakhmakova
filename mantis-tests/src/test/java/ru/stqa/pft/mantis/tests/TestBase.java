package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty ("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp ().upload (new File ("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp ().restore ("config_inc.php.back", "config_inc.php");
        app.stop();
    }

    private static MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator ()
                .getMantisConnectPort (new URL (app.getProperty ("soap.url")));
        return mc;
    }
    public boolean isIssueStatusClosed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect ();
        IssueData issues = mc.mc_issue_get (app.getProperty("soap.login"), app.getProperty("soap.password"), BigInteger.valueOf (issueId));
        Arrays.asList (issues).stream ()
                .map((i) -> new Issue ().withStatus (i.getStatus ())).collect (Collectors.toSet ());

        String status = issues.getStatus ().getName ();
        return Objects.equals (status, "closed");

    }
    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect ();
        IssueData issues = mc.mc_issue_get (app.getProperty("soap.login"), app.getProperty("soap.password"), BigInteger.valueOf (issueId));
        Arrays.asList (issues).stream ()
                .map((i) -> new Issue ().withResolution (i.getResolution ())).collect (Collectors.toSet ());

        String resolution = issues.getResolution ().getName ();
        return Objects.equals (resolution, "open");

    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException ("Ignored because of issue " + issueId);
        }
    }
    }


