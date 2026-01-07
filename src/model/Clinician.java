package model;

public class Clinician extends Person {
    private String clinicianID;
    private String title;
    private String speciality;
    private String gmcNumber;
    private String workplaceID;
    private String workplaceType;
    private String employmentStatus;
    private String startDate;

    public Clinician(String clinicianID, String firstName, String lastName, String title,
                     String speciality, String gmcNumber, String phoneNumber, String email,
                     String workplaceID, String workplaceType, String employmentStatus, String startDate) {

        // Pass to the Parent (Person)
        super(firstName, lastName, phoneNumber, email);

        this.clinicianID = clinicianID;
        this.title = title;
        this.speciality = speciality;
        this.gmcNumber = gmcNumber;
        this.workplaceID = workplaceID;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    // ----- Getters ----
    public String getClinicianID() { return clinicianID; }
    public String getTitle() { return title; }
    public String getSpeciality() { return speciality; }
    public String getWorkplaceID() { return workplaceID; }
    public String getWorkplaceType() { return workplaceType; }
    public String getStartDate() { return startDate; }

    // --------- Compatibility Getters ---

    public String getGmcNumber() { return gmcNumber; }
    public String getGmc() { return gmcNumber; } // Alias

    public String getEmploymentStatus() { return employmentStatus; }
    public String getStatus() { return employmentStatus; } // Alias

    // getPhoneNumber() and getEmail() inherits

    @Override
    public String toString() {
        return "Dr. " + getFirstName() + " " + getLastName() + " (" + speciality + ")";
    }
}