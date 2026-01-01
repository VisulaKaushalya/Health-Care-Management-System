package model;

public class Patient extends Person {
    private  String patientid;
    private  String dateOfBirth;
    private  String nhsNumber;

    //constructor
    public Patient(String patientid, String firstName, String lastName, String dateOfBirth, String nhsNumber) {
        super(firstName, lastName);
        this.patientid = patientid;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
    }

    //getters
    public String getPatientid() {
        return patientid;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    //print patient details
    @Override
    public String toString() {
        return "ID: " + patientid + " | Name: " + getFirstName() + " " + getLastName();
    }

}
