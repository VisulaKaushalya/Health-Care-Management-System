package model;

public class Patient extends Person {
    private String patientID;
    private String dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String address;
    private String postcode;
    private String emergencyName;
    private String emergencyPhone;
    private String registrationDate;
    private String gpSurgeryID;

    public Patient(String patientID, String firstName, String lastName, String dateOfBirth,
                   String nhsNumber, String gender, String phoneNumber, String email,
                   String address, String postcode, String emergencyName,
                   String emergencyPhone, String registrationDate, String gpSurgeryID) {

        // Pass to Parent (Person)
        super(firstName, lastName, phoneNumber, email);

        this.patientID = patientID;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryID = gpSurgeryID;
    }

    // ---------- Getters ---
    public String getPatientID() { return patientID; }
    public String getNhsNumber() { return nhsNumber; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getRegistrationDate() { return registrationDate; }

    // ------------ Compatibility Getters ---

    public String getDateOfBirth() { return dateOfBirth; }
    public String getDob() { return dateOfBirth; }

    public String getGpSurgeryID() { return gpSurgeryID; }
    public String getSurgeryID() { return gpSurgeryID; }

    public String getEmergencyName() { return emergencyName; }
    public String getEmgName() { return emergencyName; }

    public String getEmergencyPhone() { return emergencyPhone; }
    public String getEmgPhone() { return emergencyPhone; }

    //getPhoneNumber() getEmail() inherits

    @Override
    public String toString() {
        return patientID + " - " + getFirstName() + " " + getLastName();
    }
}