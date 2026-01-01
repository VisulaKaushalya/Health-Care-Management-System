package model;

public class Clinician extends Person {
    private String clinicianID;
    private String specialty;
    private String gmcNumber;
    private String workplaceID;

    //constructor
    public Clinician(String clinicianID, String firstName, String lastName,String specialty,String gmcNumber, String workplaceID) {
        super(firstName, lastName);
        this.clinicianID = clinicianID;
        this.specialty = specialty;
        this.gmcNumber = gmcNumber;
        this.workplaceID = workplaceID;

    }
    //getters
    public String getClinicianID() {
        return clinicianID;
    }
    public String getSpecialty() {
        return specialty;
    }
    public String getWorkplaceID() {
        return workplaceID;
    }

    @Override
    public String toString() {
        return "Dr. " + getLastName() + " (" + specialty +")";
    }

}
