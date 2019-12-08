package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.mantis.tests.TestBase.app;

public class LoginTests {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        System.out.println(app.getProperty("web.baseUrl"));

        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));

    }
}
