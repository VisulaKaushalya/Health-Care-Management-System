package model;

public class Appointment {
    private String appointmentID;    // Index 0
    private String patientID;        // Index 1
    private String clinicianID;      // Index 2
    private String facilityID;       // Index 3
    private String date;             // Index 4
    private String time;             // Index 5
    private String duration;         // Index 6
    private String type;             // Index 7
    private String status;           // Index 8
    private String reason;           // Index 9
    private String notes;            // Index 10
    private String createdDate;      // Index 11
    private String lastModified;     // Index 12

    public Appointment(String appointmentID, String patientID, String clinicianID, String facilityID,
                       String date, String time, String duration, String type, String status,
                       String reason, String notes, String createdDate, String lastModified) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.facilityID = facilityID;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    // Getters
    public String getAppointmentID() { return appointmentID; }
    public String getPatientID() { return patientID; }
    public String getClinicianID() { return clinicianID; }
    public String getFacilityID() { return facilityID; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDuration() { return duration; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }
    public String getNotes() { return notes; }
    public String getCreatedDate() { return createdDate; }
    public String getLastModified() { return lastModified; }

    @Override
    public String toString() {
        return "Appt: " + appointmentID + " [" + date + "]";
    }
}