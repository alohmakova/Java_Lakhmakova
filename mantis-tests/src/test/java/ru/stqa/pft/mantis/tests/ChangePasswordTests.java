package ru.stqa.pft.mantis.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail ().start ();
    }
    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.users ().login ("administrator", "root");
        app.users ().manageUsers ();
        List<Integer> id = app.db().allUsersExceptAdmin ();
        Integer selectedId = id.iterator ().next ();
        app.users ().selectUser (selectedId);
        List<MailMessage> mailMessages = app.mail ().waitForMail (1, 10000);
        String email = app.db().emailToChangePassword(selectedId);
        String confirmationLink = findConfirmationLink (mailMessages, email);
        String password = "213jhjg24";
        app.users ().newPassword (confirmationLink, password);
        String user = app.db().userName(selectedId);
        AssertJUnit.assertTrue(app.newSession ().login (user, password));

    }
    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream ().filter ((m) -> m.to.equals (email)).findFirst ().get ();
        VerbalExpression regex = VerbalExpression.regex ().find ("http://").nonSpace ().oneOrMore ().build ();
        return regex.getText (mailMessage.text);
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail ().stop ();
    }

}
