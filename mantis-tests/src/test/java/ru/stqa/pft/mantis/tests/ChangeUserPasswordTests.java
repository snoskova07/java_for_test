package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangeUserPasswordTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() throws IOException, MessagingException {
   /*       app.mail().start();
      long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
  */
//        assertTrue(app.newSession().login(user, password));
    }

    @Test
    public void testChangeUserPassword() throws IOException {
        Users users = app.db().users();
        app.session().login("administrator", "root");
        app.navigationHelper().goToManageUsersPage();
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

 /*   @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
*/
}
