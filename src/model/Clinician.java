package model;

public class Clinician extends Person {
    private String clinicianID;
    private String title;
    private String speciality;
    private String gmcNumber;
    private String phoneNumber;
    private String email;
    private String workplaceID;
    private String workplaceType;
    private String employmentStatus;
    private String startDate;

    //constructor
    public Clinician(String clinicianID, String firstName, String lastName, String title,
                     String speciality, String gmcNumber, String phoneNumber, String email,
                     String workplaceID, String workplaceType, String employmentStatus, String startDate) {
        super(firstName, lastName);
        this.clinicianID = clinicianID;
        this.title = title;
        this.speciality = speciality;
        this.gmcNumber = gmcNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceID = workplaceID;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;

    }
    //getters
    public String getClinicianID() { return clinicianID; }
    public String getTitle() { return title; }
    public String getSpeciality() { return speciality; }
    public String getGmcNumber() { return gmcNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getWorkplaceID() { return workplaceID; }
    public String getWorkplaceType() { return workplaceType; }
    public String getEmploymentStatus() { return employmentStatus; }
    public String getStartDate() { return startDate; }

    @Override
    public String toString() {
        return "Dr. " + getFirstName() + " " + getLastName() + " (" + speciality + ")";
    }

}
