package model;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Person(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public String getPhone() { return phone; }
    public String getPhoneNumber() { return phone; } // Alias
    public String getEmail() { return email; }
}