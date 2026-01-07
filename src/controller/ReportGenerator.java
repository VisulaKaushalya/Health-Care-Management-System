package controller;

import model.Prescription;
import model.Referral;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ReportGenerator {

    //  Generate Prescription
    public static void generatePrescription(Prescription p) {
        String filename = "Prescription_" + p.getPrescriptionID() + ".txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {

            // Header
            out.println("HEALTHCARE MANAGEMENT SYSTEM - PRESCRIPTION");
            out.println("==================================================");

            // Basic Info
            out.println("ID:       " + p.getPrescriptionID());
            out.println("Date:     " + p.getDate());
            out.println("Status:   " + p.getStatus());
            out.println("--------------------------------------------------");


            out.println("[PATIENT & DOCTOR]");
            out.println("Patient ID: " + p.getPatientID());
            out.println("Doctor ID:  " + p.getClinicianID());
            out.println();

            out.println("[MEDICATION DETAILS]");
            out.println("Drug Name:   " + p.getMedication());
            out.println("Dosage:      " + p.getDosage());
            out.println("Frequency:   " + p.getFrequency());
            out.println("Duration:    " + p.getDuration());
            out.println("Quantity:    " + p.getQuantity());
            out.println();

            out.println("[INSTRUCTIONS]");
            out.println(p.getInstructions());
            out.println();

            out.println("[DISPENSING]");
            out.println("Pharmacy:    " + p.getPharmacy());
            out.println("==================================================");

            JOptionPane.showMessageDialog(null, "Prescription saved to " + filename);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    //  Generate Refferal Letter
    public static void generateReferral(Referral r) {
        String filename = "Referral_" + r.getReferralID() + ".txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {

            // Header
            out.println("HEALTHCARE MANAGEMENT SYSTEM - REFERRAL LETTER");
            out.println("==================================================");

            out.println("Referral ID: " + r.getReferralID());
            out.println("Date:        " + r.getDate());
            out.println("Urgency:     " + r.getUrgency());
            out.println("--------------------------------------------------");

            out.println("[FROM]");
            out.println("Doctor:   " + r.getReferringDoctorID());
            out.println("Facility: " + r.getReferringFacilityID());
            out.println();

            out.println("[TO]");
            out.println("Doctor:   " + r.getReferredToDoctorID());
            out.println("Facility: " + r.getReferredToFacilityID());
            out.println();

            out.println("[PATIENT INFO]");
            out.println("Patient ID: " + r.getPatientID());
            out.println("Linked Appt: " + r.getAppointmentID());
            out.println("--------------------------------------------------");

            // Medical Content
            out.println("[REASON FOR REFERRAL]");
            out.println(r.getReason());
            out.println();

            out.println("[CLINICAL SUMMARY]");
            out.println(r.getSummary());
            out.println();

            out.println("[NOTES]");
            out.println(r.getNotes());
            out.println("==================================================");

            JOptionPane.showMessageDialog(null, "Referral saved to " + filename);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}