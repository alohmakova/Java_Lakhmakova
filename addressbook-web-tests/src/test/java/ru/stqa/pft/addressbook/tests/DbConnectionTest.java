package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
    /*@Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement ();
            ResultSet rs = st.executeQuery ("Select id, firstname, lastname, address from addressbook where modified = (Select MAX(modified) from addressbook)");
            Contacts contacts = new Contacts ();
            while (rs.next()) {
                contacts.add (new ContactData ().withId (rs.getInt ("id"))
                                .withFirstName (rs.getString ("firstname"))
                                .withLastName (rs.getString ("lastname"))
                                .withAddress (rs.getString ("address")));
            }
            rs.close ();
            st.close ();
            conn.close ();
            System.out.println (contacts);


           } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }*/
    @Test(enabled = false)
    public void Test(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement ();
            ResultSet rs = st.executeQuery ("SELECT g.group_id, group_name, group_header, group_footer FROM group_list as g JOIN address_in_groups as a ON g.group_id = a.group_id WHERE id = 60");
            Groups groups = new Groups ();
            while (rs.next()) {
                groups.add (new GroupData ().withId (rs.getInt ("group_id")).withName (rs.getString ("group_name"))
                        .withHeader (rs.getString ("group_header")).withFooter (rs.getString ("group_footer")));
            }
            rs.close ();
            st.close ();
            conn.close ();
            System.out.println ("Последний измененный контакт добавлен в группу " + new Groups (groups));

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }
}
