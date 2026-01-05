package model;

public class Staff extends Person {
    private String staffID;
    private String role;
    private String department;
    private String facilityID;
    private String phone;
    private String email;
    private String status;
    private String startDate;
    private String manager;
    private String accessLevel;

    // Constructor matches the 12 columns in staff.csv
    public Staff(String staffID, String firstName, String lastName, String role,
                 String department, String facilityID, String phone, String email,
                 String status, String startDate, String manager, String accessLevel) {

        super(firstName, lastName); // Inherit Name fields
        this.staffID = staffID;
        this.role = role;
        this.department = department;
        this.facilityID = facilityID;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.startDate = startDate;
        this.manager = manager;
        this.accessLevel = accessLevel;
    }

    // Getters
    public String getStaffID() { return staffID; }
    public String getRole() { return role; }
    public String getDepartment() { return department; }
    public String getFacilityID() { return facilityID; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
    public String getStartDate() { return startDate; }
    public String getManager() { return manager; }
    public String getAccessLevel() { return accessLevel; }

    @Override
    public String toString() {
        return role + ": " + getFirstName() + " " + getLastName();
    }
}