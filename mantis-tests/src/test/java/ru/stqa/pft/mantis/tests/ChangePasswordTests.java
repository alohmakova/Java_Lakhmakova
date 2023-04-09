package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        app.users ().login ("administrator", "root");
        app.users ().manageUsers ();
        List<Integer> id = app.db().allUsersExceptAdmin ();
        Integer selectedId = id.iterator ().next ();
        app.users ().selectUser (selectedId);

    }

}
