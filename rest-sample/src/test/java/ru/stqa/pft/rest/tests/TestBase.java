package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.stqa.pft.rest.Issue;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class TestBase {
    private Executor getExecutor() {
        return Executor.newInstance().auth("b31e382ca8445202e66b03aaf31508a3", "");
    }
    protected Issue getIssueWithId(int issueId) throws IOException {
        String json = getExecutor().execute (Request.Get ("https://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent ().asString ();
        JsonElement parsed = new JsonParser ().parse (json);
        JsonElement issue = parsed.getAsJsonObject ().get ("issues");
        Set<Issue> issues = new Gson ().fromJson(issue, new TypeToken<Set<Issue>> () {}.getType());
        return issues.iterator().next();
    }
    public boolean isIssueOpen(int issueId) throws IOException {
        String stateName = getIssueWithId(issueId).getStateName ();
        return Objects.equals (stateName, "Open") || Objects.equals(stateName, "In Progress");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException ("Ignored because of issue " + issueId);
        }
    }
}
