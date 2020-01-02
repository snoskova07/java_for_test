package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

public class TestBase {
  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
 //   app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  boolean isIssueOpen(int issueId) throws IOException, ServiceException {
    return !app.soap().getIssue(issueId).getStatus().equals("resolved");
  }

  public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws Exception {
 //   app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

}
