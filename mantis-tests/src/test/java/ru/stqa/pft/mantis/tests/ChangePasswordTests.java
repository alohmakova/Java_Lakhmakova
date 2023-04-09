package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class ChangePasswordTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        app.users ().login ("administrator", "root");
        app.users ().manageUsers ();
        app.users ().selectUser ();

    }

}
