package ru.stqa.pft.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        //Resolved
      //  skipIfNotFixed(2307);
        //Open
        skipIfNotFixed(2306);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Atest issue").withDescription("A test issue").withStatus("Open");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(oldIssues, newIssues);
    }
}