package controller;

import model.Patient;
import model.Clinician;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class ReferralManager {
    //static instance
    private static ReferralManager instance;

    //constructor
    private ReferralManager() {
    }

    //public method
    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    //business logic, create referral

    public void createReferral(Patient patient, Clinician doctor, String notes){
        //simulate an email

        try (FileWriter fw = new FileWriter("referrals_log.txt",true);
            PrintWriter out = new PrintWriter(fw)){

            out.println("--- REFERRAL SENT---");
            out.println("Date: " + LocalDate.now());
            out.println("From: Dr. " + doctor.getLastName());
            out.println("Patient: " + patient.getFirstName() + " " + patient.getLastName());
            out.println("Details: " + notes);
            out.println("-----------------------\n");

            System.out.println("Success: Referral logged for " + patient.getLastName());
        }

        catch (IOException e){
        System.out.println("Error creating referral: " + e.getMessage());
        }
    }
}
