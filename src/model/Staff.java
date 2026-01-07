package model;

public class Staff extends Person {
    private String staffID;
    private String role;
    private String department;
    private String facilityID;
    private String status;
    private String startDate;
    private String manager;
    private String accessLevel;

    public Staff(String staffID, String firstName, String lastName, String role,
                 String department, String facilityID, String phone, String email,
                 String status, String startDate, String manager, String accessLevel) {

        // Pass Name, Phone, Email to Person
        super(firstName, lastName, phone, email);

        this.staffID = staffID;
        this.role = role;
        this.department = department;
        this.facilityID = facilityID;
        this.status = status;
        this.startDate = startDate;
        this.manager = manager;
        this.accessLevel = accessLevel;
    }

    // ---------- Getters ---
    public String getStaffID() { return staffID; }
    public String getRole() { return role; }
    public String getDepartment() { return department; }
    public String getFacilityID() { return facilityID; }
    public String getStatus() { return status; }
    public String getStartDate() { return startDate; }
    public String getManager() { return manager; }
    public String getAccessLevel() { return accessLevel; }

    // getPhone() getEmail() inherits

    @Override
    public String toString() {
        return role + ": " + getFirstName() + " " + getLastName();
    }
}