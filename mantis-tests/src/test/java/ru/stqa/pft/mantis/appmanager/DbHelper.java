//package ru.stqa.pft.mantis.appmanager;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import ru.stqa.pft.addressbook.model.ContactData;
//import ru.stqa.pft.addressbook.model.Contacts;
//import ru.stqa.pft.addressbook.model.GroupData;
//import ru.stqa.pft.addressbook.model.Groups;
//
//import java.sql.*;
//import java.util.List;
//
//public class DbHelper {
//
//    public DbHelper() {
//
//
////        public int allUsersExceptAdmin (){
////            Connection conn = null;
////            try {
////                conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
////                Statement st = conn.createStatement ();
////                ResultSet rs = st.executeQuery ("SELECT id FROM mantis_user_table WHERE username NOT LIKE 'administrator' ");
////
////                rs.close ();
////                st.close ();
////                conn.close ();
////                System.out.println (rs);
////                return rs;
////
////            } catch (SQLException ex) {
////                // handle any errors
////                System.out.println ("SQLException: " + ex.getMessage ());
////                System.out.println ("SQLState: " + ex.getSQLState ());
////                System.out.println ("VendorError: " + ex.getErrorCode ());
////                return null;
////
////            }
////        }
//    }
//}