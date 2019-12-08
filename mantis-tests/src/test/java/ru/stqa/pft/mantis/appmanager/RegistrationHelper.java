package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) throws IOException {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
