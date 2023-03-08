package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String telMobile;
    private final String email;
    private String group;

    public ContactData(int id, String firstName, String lastName, String address, String telMobile, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telMobile = telMobile;
        this.email = email;
        this.group = group;
    }
    public ContactData(String firstName, String lastName, String address, String telMobile, String email, String group) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telMobile = telMobile;
        this.email = email;
        this.group = group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telMobile='" + telMobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals (firstName, that.firstName)) return false;
        if (!Objects.equals (lastName, that.lastName)) return false;
        if (!Objects.equals (address, that.address)) return false;
        if (!Objects.equals (telMobile, that.telMobile)) return false;
        return Objects.equals (email, that.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode () : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode () : 0);
        result = 31 * result + (address != null ? address.hashCode () : 0);
        result = 31 * result + (telMobile != null ? telMobile.hashCode () : 0);
        result = 31 * result + (email != null ? email.hashCode () : 0);
        return result;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() { return group; }

}
