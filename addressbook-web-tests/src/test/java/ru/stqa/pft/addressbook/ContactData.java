package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String telMobile;
    private final String email;

    public ContactData(String firstName, String lastName, String address, String telMobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telMobile = telMobile;
        this.email = email;
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

    public String getTelMobile() {
        return telMobile;
    }

    public String getEmail() {
        return email;
    }
}
