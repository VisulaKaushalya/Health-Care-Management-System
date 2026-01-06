package model;

public class Referral {
    private String referralID;
    private String patientID;
    private String referringDoctorID;
    private String referredToDoctorID;
    private String referringFacilityID;
    private String referredToFacilityID;
    private String date;
    private String urgency;
    private String reason;
    private String summary;
    private String investigations;
    private String status;
    private String appointmentID;
    private String notes;
    private String createdDate;
    private String lastUpdated;

    public Referral(String referralID, String patientID, String referringDoctorID,
                    String referredToDoctorID, String referringFacilityID,
                    String referredToFacilityID, String date, String urgency,
                    String reason, String summary, String investigations,
                    String status, String appointmentID, String notes,
                    String createdDate, String lastUpdated) {
        this.referralID = referralID;
        this.patientID = patientID;
        this.referringDoctorID = referringDoctorID;
        this.referredToDoctorID = referredToDoctorID;
        this.referringFacilityID = referringFacilityID;
        this.referredToFacilityID = referredToFacilityID;
        this.date = date;
        this.urgency = urgency;
        this.reason = reason;
        this.summary = summary;
        this.investigations = investigations;
        this.status = status;
        this.appointmentID = appointmentID;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    // Getters
    public String getReferralID() { return referralID; }
    public String getPatientID() { return patientID; }
    public String getReferringDoctorID() { return referringDoctorID; }
    public String getReferredToDoctorID() { return referredToDoctorID; }
    public String getReferringFacilityID() { return referringFacilityID; }
    public String getReferredToFacilityID() { return referredToFacilityID; }
    public String getDate() { return date; }
    public String getUrgency() { return urgency; }
    public String getReason() { return reason; }
    public String getSummary() { return summary; }
    public String getInvestigations() { return investigations; }
    public String getStatus() { return status; }
    public String getAppointmentID() { return appointmentID; }
    public String getNotes() { return notes; }
    public String getCreatedDate() { return createdDate; }
    public String getLastUpdated() { return lastUpdated; }
}