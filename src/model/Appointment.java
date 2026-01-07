package model;

public class Appointment {
    private String appointmentID;
    private String patientID;
    private String clinicianID;
    private String facilityID;
    private String date;
    private String time;
    private String duration;
    private String type;
    private String status;
    private String reason;
    private String notes;
    private String createdDate;
    private String lastModified;

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

    // ----- Getters -----
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

    // Helper
    @Override
    public String toString() {
        return "Appt: " + appointmentID + " [" + date + " " + time + "]";
    }
}