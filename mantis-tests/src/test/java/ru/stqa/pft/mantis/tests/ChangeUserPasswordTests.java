package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() throws IOException, MessagingException {
        app.mail().start();
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "pas";
        String email = String.format("user%s@localhost.localdomain", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLinkCreateUser(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        app.NavigationHelper().logout();
    }

    @Test
    public void testChangeUserPassword() throws IOException, MessagingException {
        Users users = app.db().users();
        UserData user = users.stream().filter(usr -> usr.getId() != 1).findFirst().get();
        String username = user.getUsername();
        String email = user.getEmail();
        long now = System.currentTimeMillis();
        app.session().login("administrator", "root");
        app.NavigationHelper().goToManageUsersPage();
        app.NavigationHelper().selectUser(username);
        app.NavigationHelper().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        String newPassword = "pas14";
        app.registration().finish(confirmationLink, newPassword);
        app.NavigationHelper().logout();
        app.session().login(username, newPassword);
        assertTrue(app.newSession().login(username, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email) && m.text.contains("Someone (presumably you) requested a password change through e-mail")).iterator().next();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    private String findConfirmationLinkCreateUser(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

   @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
