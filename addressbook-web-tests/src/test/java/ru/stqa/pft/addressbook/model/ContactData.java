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
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telMobile = telMobile;
        this.email = email;
        this.group = group;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals (firstName, that.firstName)) return false;
        return Objects.equals (lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode () : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode () : 0);
        return result;
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

}
