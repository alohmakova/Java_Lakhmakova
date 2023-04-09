package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder ()
                .configure () // configures settings from hibernate.cfg.xml
                .build ();
        sessionFactory = new MetadataSources (registry).buildMetadata ().buildSessionFactory ();

    }

    public Groups groups() {
        Session session = sessionFactory.openSession ();
        session.beginTransaction ();
        List<GroupData> result = session.createQuery ("from GroupData").list ();
        session.getTransaction ().commit ();
        session.close ();
        return new Groups (result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession ();
        session.beginTransaction ();
        List<ContactData> result = session.createQuery ("from ContactData").list ();
        session.getTransaction ().commit ();
        session.close ();
        return new Contacts (result);
    }

    public Groups groupsOfAddedContact(int id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement ();
            ResultSet rs = st.executeQuery ("SELECT g.group_id, group_name, group_header, group_footer FROM group_list as g JOIN address_in_groups as a ON g.group_id = a.group_id WHERE id = " + id);
            Groups groups = new Groups ();
            while (rs.next ()) {
                groups.add (new GroupData ().withId (rs.getInt ("group_id")).withName (rs.getString ("group_name"))
                        .withHeader (rs.getString ("group_header")).withFooter (rs.getString ("group_footer")));
            }
            rs.close ();
            st.close ();
            conn.close ();
            //System.out.println ("Последний измененный контакт добавлен в группу " + new Groups (groups));
            return groups;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println ("SQLException: " + ex.getMessage ());
            System.out.println ("SQLState: " + ex.getSQLState ());
            System.out.println ("VendorError: " + ex.getErrorCode ());
            return null;

        }
    }

    public boolean contactAddedToGroup(int id, int group_id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement ();
            ResultSet rs = st.executeQuery ("SELECT id, group_id FROM address_in_groups WHERE id = " + id + " AND group_id = " + group_id);
            List<Integer> contactsInGroupList = new ArrayList<> ();
            while (rs.next ()) {
                int idFromDb = rs.getInt ("id");
                int group_idFromDb = rs.getInt ("group_id");
                contactsInGroupList.add (idFromDb);
                contactsInGroupList.add (group_idFromDb);
            }
            rs.close ();
            st.close ();
            conn.close ();
            System.out.println ("Id контакта, Id группы: " + contactsInGroupList);
            return contactsInGroupList.isEmpty ();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println ("SQLException: " + ex.getMessage ());
            System.out.println ("SQLState: " + ex.getSQLState ());
            System.out.println ("VendorError: " + ex.getErrorCode ());
            return false;
        }
    }
    }
