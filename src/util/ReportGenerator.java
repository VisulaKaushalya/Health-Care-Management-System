package util;

import model.Prescription;
import model.Referral;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ReportGenerator {

    // 1. Generate Prescription Text File
    public static void generatePrescription(Prescription p) {
        String filename = "Prescription_" + p.getPrescriptionID() + ".txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("========== PRESCRIPTION ==========");
            out.println("ID:       " + p.getPrescriptionID());
            out.println("Date:     " + p.getDate());
            out.println("----------------------------------");
            out.println("Patient ID: " + p.getPatientID());
            out.println("Doctor ID:  " + p.getClinicianID());
            out.println("----------------------------------");
            out.println("Medication: " + p.getMedication());
            out.println("Dosage:     " + p.getDosage());
            out.println("Frequency:  " + p.getFrequency());
            out.println("Duration:   " + p.getDuration());
            out.println("Quantity:   " + p.getQuantity());
            out.println("----------------------------------");
            out.println("Instructions:\n" + p.getInstructions());
            out.println("----------------------------------");
            out.println("Pharmacy: " + p.getPharmacy());
            out.println("Status:   " + p.getStatus());
            out.println("==================================");

            JOptionPane.showMessageDialog(null, "Prescription exported to " + filename);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error exporting prescription: " + e.getMessage());
        }
    }

    // 2. Generate Referral Letter Text File
    public static void generateReferral(Referral r) {
        String filename = "Referral_" + r.getReferralID() + ".txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("========== REFERRAL LETTER ==========");
            out.println("Referral ID: " + r.getReferralID());
            out.println("Date:        " + r.getDate());
            out.println("Urgency:     " + r.getUrgency().toUpperCase());
            out.println("-------------------------------------");
            out.println("FROM:");
            out.println("Doctor:   " + r.getReferringDoctorID());
            out.println("Facility: " + r.getReferringFacilityID());
            out.println("\nTO:");
            out.println("Doctor:   " + r.getReferredToDoctorID());
            out.println("Facility: " + r.getReferredToFacilityID());
            out.println("-------------------------------------");
            out.println("PATIENT: " + r.getPatientID());
            out.println("-------------------------------------");
            out.println("REASON FOR REFERRAL:");
            out.println(r.getReason());
            out.println("\nCLINICAL SUMMARY:");
            out.println(r.getSummary());
            out.println("\nNOTES:");
            out.println(r.getNotes());
            out.println("=====================================");

            JOptionPane.showMessageDialog(null, "Referral exported to " + filename);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error exporting referral: " + e.getMessage());
        }
    }
}