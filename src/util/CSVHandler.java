package util;

import model.Patient;
import model.Clinician;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVHandler {

    //------------------------------patients--------------------------

    //list all patients
    public  static List<Patient> loadPatients(String filePath){
        List<Patient> patients = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            //skip header
            br.readLine();

            while ((line = br.readLine()) !=null){
                //commas for split
                String[] data = line.split(",");

                //check for enough data
                if (data.length >= 14){
                    Patient p =new Patient(
                    //extract from columns of csv
                            data[0].trim(),  // ID
                            data[1].trim(),  // First Name
                            data[2].trim(),  // Last Name
                            data[3].trim(),  // DOB
                            data[4].trim(),  // NHS Number
                            data[5].trim(),  // Gender
                            data[6].trim(),  // Phone
                            data[7].trim(),  // Email
                            data[8].trim(),  // Address
                            data[9].trim(),  // Postcode
                            data[10].trim(), // Emergency Name
                            data[11].trim(), // Emergency Phone
                            data[12].trim(), // Registration Date
                            data[13].trim()  // GP Surgery ID
                    );


                    //object to add to list

                    patients.add(p);

                }

            }
        }
        catch (IOException e){
            System.out.println("Error reading File: " + e.getMessage());
        }
        return patients;
    }


    //------------------------clinicians-----------------------------------

    public List<Clinician> loadClinicians(String filePath) {
        List<Clinician> clinicians = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // import data to clinicians
                if (data.length >= 12) {
                    Clinician c = new Clinician(
                            data[0].trim(),  // ID
                            data[1].trim(),  // First Name
                            data[2].trim(),  // Last Name
                            data[3].trim(),  // Title
                            data[4].trim(),  // Speciality
                            data[5].trim(),  // GMC
                            data[6].trim(),  // Phone
                            data[7].trim(),  // Email
                            data[8].trim(),  // Workplace ID
                            data[9].trim(),  // Workplace Type
                            data[10].trim(), // Status
                            data[11].trim()  // Start Date
                    );
                    clinicians.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading clinicians: " + e.getMessage());
        }
        return clinicians;
    }

}
