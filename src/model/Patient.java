package model;

public class Patient extends Person {
    private String patientID;
    private String dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String postcode;
    private String emergencyName;
    private String emergencyPhone;
    private String registrationDate;
    private String gpSurgeryID;

    //constructor
    public Patient(String patientID, String firstName, String lastName, String dateOfBirth,
                   String nhsNumber, String gender, String phoneNumber, String email,
                   String address, String postcode, String emergencyName,
                   String emergencyPhone, String registrationDate, String gpSurgeryID) {
        super(firstName, lastName);
        this.patientID = patientID;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryID = gpSurgeryID;
    }

    //getters
    public String getPatientID() { return patientID; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getNhsNumber() { return nhsNumber; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getEmergencyName() { return emergencyName; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public String getRegistrationDate() { return registrationDate; }
    public String getGpSurgeryID() { return gpSurgeryID; }


    //print patient details
    @Override
    public String toString() {
        return "ID: " + getPatientID() + " | Name: " + getFirstName() + " " + getLastName();
    }

}
