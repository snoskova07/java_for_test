package ru.stqa.pft.rest;

import org.testng.SkipException;

public class TestBase {

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        return true;
    }


}
