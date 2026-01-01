package util;

import model.Patient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVHandler {
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
                if (data.length >= 5){
                    //extract from columns of csv
                    String id = data[0].trim();
                    String first = data[1].trim();
                    String last = data[2].trim();
                    String dob = data[3].trim();
                    String nhs = data[4].trim();

                    //object to add to list

                    Patient p = new Patient(id, first, last, dob, nhs);
                    patients.add(p);

                }

            }
        }
        catch (IOException e){
            System.out.println("Error reading File: " + e.getMessage());
        }
        return patients;
    }

}
