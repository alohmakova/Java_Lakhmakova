package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase{
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap ().getProjects ();
        System.out.println (projects.size ());
        for (Project project :  projects) {
            System.out.println (project.getName ());
        }
    }
    @Test(enabled = false)
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap ().getProjects ();
        Issue issue = new Issue ().withSummary ("Test issue")
                .withDescription ("Test issue description").withProject (projects.iterator ().next ());
        Issue created = app.soap ().addIssue(issue);
        assertEquals(issue.getSummary (), created.getSummary ());
    }
    @Test
    public void testGetIssueStatusAndResolution() throws MalformedURLException, ServiceException, RemoteException {
        IssueData issue = app.soap ().getIssue (0000007);
        String status = issue.getStatus ().getName();
        String resolution = issue.getResolution ().getName();
        Integer statusId = issue.getStatus ().getId().intValue ();
        Integer resolutionId = issue.getResolution ().getId().intValue ();
        System.out.println ("Статус баг-репорта " + status + ", ID статуса " + statusId);
        System.out.println ("Resolution баг-репорта " + resolution + ", ID статуса " + resolutionId);
        System.out.println ("Баг открыт? " + isIssueOpen(0000007));
    }
}

