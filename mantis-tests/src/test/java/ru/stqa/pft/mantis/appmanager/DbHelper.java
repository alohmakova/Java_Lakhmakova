package ru.stqa.pft.mantis.appmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class DbHelper {

        public List<Integer> allUsersExceptAdmin() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
                Statement st = conn.createStatement ();
                ResultSet rs = st.executeQuery ("SELECT id FROM mantis_user_table WHERE username NOT LIKE 'administrator' ");
                List<Integer> idList = new ArrayList<> ();
                while (rs.next ()) {
                    int id = rs.getInt ("id");
                    idList.add (id);
                }
                rs.close ();
                st.close ();
                conn.close ();
                System.out.println (idList);
                return idList;

            } catch (SQLException ex) {
                // handle any errors
                System.out.println ("SQLException: " + ex.getMessage ());
                System.out.println ("SQLState: " + ex.getSQLState ());
                System.out.println ("VendorError: " + ex.getErrorCode ());
                return null;
            }
        }
    }