import model.Patient;
import util.CSVHandler;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create our CSV loader
        CSVHandler loader = new CSVHandler();

        // 2. Try to load the file (Make sure filename matches exactly!)
        System.out.println("Loading patients...");
        List<Patient> myPatients = loader.loadPatients("patients.csv");

        // 3. Print the result
        System.out.println("Found " + myPatients.size() + " patients.");

        // 4. Print the first one just to check
        if (!myPatients.isEmpty()) {
            System.out.println("First Patient: " + myPatients.get(0).toString());
        }
    }
}