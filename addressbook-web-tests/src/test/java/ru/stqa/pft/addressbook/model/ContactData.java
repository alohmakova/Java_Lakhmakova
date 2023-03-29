package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField()
    @Id
    @Column (name = "id")
    private int id; //= Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String firstName;
    @Expose
    @Column (name = "lastname")
    private String lastName;
    @Expose
    @Column (name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column (name = "mobile")
    @Type(type = "text")
    private String telMobile;
    @Expose
    @Column (name = "home")
    @Type (type = "text")
    private String telHome;
    @Expose
    @Column (name = "work")
    @Type (type = "text")
    private String telWork;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Transient
    private String allEmails;
    @Expose
    @Column (name = "email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column (name = "email3")
    @Type(type = "text")
    private String email3;
    @Expose
    @Column (name = "photo")
    @Type(type = "text")
    private String photo;
    @Expose
    @Transient
    private String group;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withTelMobile(String telMobile) {
        this.telMobile = telMobile;
        return this;
    }

    public ContactData withTelHome(String telHome) {
        this.telHome = telHome;
        return this;
    }

    public ContactData withTelWork(String telWork) {
        this.telWork = telWork;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath ();
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public String getTelHome() {
        return telHome;
    }

    public String getTelWork() {
        return telWork;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }
    public File getPhoto() {
        return new File (photo);
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals (firstName, that.firstName)) return false;
        return Objects.equals (lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode () : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode () : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telMobile='" + telMobile + '\'' +
                ", telHome='" + telHome + '\'' +
                ", telWork='" + telWork + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

}
