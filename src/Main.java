import controller.ReferralManager;
import model.Clinician;
import model.Patient;
import util.CSVHandler;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load Data
        CSVHandler loader = new CSVHandler();
        List<Patient> patients = loader.loadPatients("patients.csv");

        // Create a Dummy Doctor
        Clinician doctor = new Clinician("C001", "John", "Watson", "GP", "12345", "S001");

        // Test the Singleton Referral Manager
        if (!patients.isEmpty()) {
            Patient p = patients.get(0); // Get the first patient

            System.out.println("Testing Referral for: " + p.getFirstName());

            // CALL THE SINGLETON
            ReferralManager.getInstance().createReferral(p, doctor, "Patient needs blood tests.");
        }
    }
}