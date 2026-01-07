package model;

public class Prescription {
    // 1 Identifiers
    private String prescriptionID;
    private String patientID;
    private String clinicianID;
    private String appointmentID;

    // 2 Medical Details
    private String medication;
    private String dosage;
    private String frequency;
    private String duration;
    private String quantity;
    private String instructions;

    // 3 Logistics
    private String pharmacy;
    private String status;
    private String date;
    private String issueDate;
    private String collectionDate;

    public Prescription(String prescriptionID, String patientID, String clinicianID, String appointmentID,
                        String date, String medication, String dosage, String frequency, String duration,
                        String quantity, String instructions, String pharmacy, String status,
                        String issueDate, String collectionDate) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.appointmentID = appointmentID;
        this.date = date;
        this.medication = medication;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacy = pharmacy;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    // ----------Getters  ---
    public String getPrescriptionID() { return prescriptionID; }
    public String getPatientID() { return patientID; }
    public String getClinicianID() { return clinicianID; }
    public String getAppointmentID() { return appointmentID; }

    public String getMedication() { return medication; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public String getDuration() { return duration; }
    public String getQuantity() { return quantity; }
    public String getInstructions() { return instructions; }

    public String getPharmacy() { return pharmacy; }
    public String getStatus() { return status; }
    public String getDate() { return date; }
    public String getIssueDate() { return issueDate; }
    public String getCollectionDate() { return collectionDate; }

    @Override
    public String toString() {
        return "RX: " + prescriptionID + " (" + medication + ")";
    }
}