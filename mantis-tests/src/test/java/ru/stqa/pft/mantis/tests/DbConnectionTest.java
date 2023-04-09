package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnectionTest {
    @Test
    public void testEmail(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
            Statement st = conn.createStatement ();
            ResultSet rs = st.executeQuery ("SELECT email FROM mantis_user_table WHERE id = 4");
            List<String> emailList = new ArrayList<> ();
            while (rs.next ()) {
                String email = rs.getString ("email");
                emailList.add (email);
            }
            rs.close ();
            st.close ();
            conn.close ();
            String email = String.join(",", emailList);
            System.out.println (email);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println ("SQLException: " + ex.getMessage ());
            System.out.println ("SQLState: " + ex.getSQLState ());
            System.out.println ("VendorError: " + ex.getErrorCode ());
        }
    }

    @Test
    public void Test(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
            Statement st = conn.createStatement ();
            ResultSet rs = st.executeQuery ("SELECT id FROM mantis_user_table WHERE username NOT LIKE 'administrator' ");
            List<Integer> idList = new ArrayList<> ();
            while (rs.next()) {
                int id = rs.getInt("id");
                idList.add(id);
            }
            rs.close ();
            st.close ();
            conn.close ();
            System.out.println (idList);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }
}
