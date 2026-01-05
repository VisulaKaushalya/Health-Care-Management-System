package util;

import model.Patient;
import model.Clinician;
import model.Appointment;
import model. Patient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
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
            br.readLine();

            while ((line = br.readLine()) !=null){
                //commas for split and ignore , in address field
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                //check for enough data
                if (data.length >= 14){
                    String cleanAddress = data[8].replace("\"", "");
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
                            cleanAddress,  // Address
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
    //  SAVE PATIENTS
    public void savePatients(String filePath, List<model.Patient> patients) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            // Write Header
            out.println("patient_id,first_name,last_name,date_of_birth,nhs_number,gender,phone_number,email,address,postcode,emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id");

            for (model.Patient p : patients) {

                String safeAddress = "\"" + p.getAddress() + "\"";

                String record = String.join(",",
                        p.getPatientID(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getDateOfBirth(),
                        p.getNhsNumber(),
                        p.getGender(),
                        p.getPhoneNumber(),
                        p.getEmail(),
                        safeAddress,
                        p.getPostcode(),
                        p.getEmergencyName(),
                        p.getEmergencyPhone(),
                        p.getRegistrationDate(),
                        p.getGpSurgeryID()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
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
    //  SAVE CLINICIANS
    public void saveClinicians(String filePath, List<model.Clinician> clinicians) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            // Header
            out.println("clinician_id,first_name,last_name,title,speciality,gmc_number,phone_number,email,workplace_id,workplace_type,employment_status,start_date");

            for (model.Clinician c : clinicians) {
                String record = String.join(",",
                        c.getClinicianID(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getTitle(),
                        c.getSpeciality(),
                        c.getGmcNumber(),
                        c.getPhoneNumber(),
                        c.getEmail(),
                        c.getWorkplaceID(),
                        c.getWorkplaceType(),
                        c.getEmploymentStatus(),
                        c.getStartDate()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving clinicians: " + e.getMessage());
        }
    }

    //------------------Appointments------------------------------------------

    public List<Appointment> loadAppointments(String filePath) {
        List<Appointment> appointments = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // import to appointments
                if (data.length >= 13) {
                    System.out.println("Loading ID: " + data[0]);
                    Appointment a = new Appointment(
                            data[0].trim(),  // ID
                            data[1].trim(),  // Patient ID
                            data[2].trim(),  // Clinician ID
                            data[3].trim(),  // Facility
                            data[4].trim(),  // Date
                            data[5].trim(),  // Time
                            data[6].trim(),  // Duration
                            data[7].trim(),  // Type
                            data[8].trim(),  // Status
                            data[9].trim(),  // Reason
                            data[10].trim(), // Notes
                            data[11].trim(), // Created
                            data[12].trim()  // Modified
                    );
                    appointments.add(a);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading appointments: " + e.getMessage());
        }
        return appointments;
    }
    // add appointment write to file
    public void addAppointment(String filePath, Appointment a) {
        try (FileWriter fw = new FileWriter(filePath, true); // 'true' means append mode (add to end)
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Format: ID, PatientID, DoctorID, Facility, Date, Time, Duration, Type, Status, Reason, Notes, Created, Modified
            String record = String.join(",",
                    a.getAppointmentID(),
                    a.getPatientID(),
                    a.getClinicianID(),
                    a.getFacilityID(),
                    a.getDate(),
                    a.getTime(),
                    a.getDuration(),
                    a.getType(),
                    a.getStatus(),
                    a.getReason(),

                    "None", // Default note
                    "N/A",  // Created
                    "N/A"   // Modified
            );

            out.println(record); // Write to file

        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }
    // appointment save to file

    public void saveAllAppointments(String filePath, List<Appointment> appointments) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {

            // Write the Header first
            out.println("appointment_id, patient_id, clinician_id, facility_id, appointment_date, appointment_time, duration_minutes, appointment_type, status, reason_for_visit, notes, created_date, last_modified");

            // Loop through the list and write each one
            for (Appointment a : appointments) {
                String record = String.join(",",
                        a.getAppointmentID(),
                        a.getPatientID(),
                        a.getClinicianID(),
                        a.getFacilityID(),
                        a.getDate(),
                        a.getTime(),
                        a.getDuration(),
                        a.getType(),
                        a.getStatus(),
                        a.getReason(),
                        a.getNotes(),
                        a.getCreatedDate(),
                        a.getLastModified(),
                        "N/A",
                        "N/A"
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving all appointments: " + e.getMessage());
        }
    }

    // prescription data load

    public List<model.Prescription> loadPrescriptions(String filePath) {
        List<model.Prescription> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 14) { // Basic check
                    model.Prescription p = new model.Prescription(
                            data[0], data[1], data[2], data[3], data[4],
                            data[5], data[6], data[7], data[8], data[9],
                            data[10], data[11], data[12], data[13],
                            (data.length > 14) ? data[14] : ""
                    );
                    list.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading prescriptions: " + e.getMessage());
        }
        return list;
    }
    //  SAVE PRESCRIPTIONS
    public void savePrescriptions(String filePath, List<model.Prescription> prescriptions) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            // Header
            out.println("prescription_id,patient_id,clinician_id,appointment_id,prescription_date,medication_name,dosage,frequency,duration_days,quantity,instructions,pharmacy_name,status,issue_date,collection_date");

            for (model.Prescription p : prescriptions) {
                String record = String.join(",",
                        p.getPrescriptionID(),
                        p.getPatientID(),
                        p.getClinicianID(),
                        p.getAppointmentID(),
                        p.getDate(),
                        p.getMedication(),
                        p.getDosage(),
                        p.getFrequency(),
                        p.getDuration(),
                        p.getQuantity(),
                        "\"" + p.getInstructions() + "\"", // Quote instructions to be safe
                        p.getPharmacy(),
                        p.getStatus(),
                        p.getIssueDate(),
                        p.getCollectionDate()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving prescriptions: " + e.getMessage());
        }
    }




    // -----------------------------------------STAFF----------------------------------------------------
    //  LOAD STAFF (Paste this!)
    public List<model.Staff> loadStaff(String filePath) {
        List<model.Staff> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Ensure we have at least 12 columns
                if (data.length >= 12) {
                    model.Staff s = new model.Staff(
                            data[0], // ID
                            data[1], // First Name
                            data[2], // Last Name
                            data[3], // Role
                            data[4], // Dept
                            data[5], // Facility
                            data[6], // Phone
                            data[7], // Email
                            data[8], // Status
                            data[9], // Start Date
                            data[10], // Manager
                            data[11]  // Access Level
                    );
                    list.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading staff: " + e.getMessage());
        }
        return list;
    }

    //  SAVE STAFF
    public void saveStaff(String filePath, List<model.Staff> staffList) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            // Header matching the CSV
            out.println("staff_id,first_name,last_name,role,department,facility_id,phone_number,email,status,start_date,manager,access_level");

            for (model.Staff s : staffList) {
                String record = String.join(",",
                        s.getStaffID(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getRole(),
                        s.getDepartment(),
                        s.getFacilityID(),
                        s.getPhone(),
                        s.getEmail(),
                        s.getStatus(),
                        s.getStartDate(),
                        s.getManager(),
                        s.getAccessLevel()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving staff: " + e.getMessage());
        }
    }











    // --------------------------------FACILITIES------------------------------------------------

    //  LOAD FACILITIES
    public List<model.Facility> loadFacilities(String filePath) {
        List<model.Facility> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                // Regex split for commas inside quotes
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data.length >= 11) {
                    // Clean quotes from Address and Hours
                    String cleanAddress = data[3].replace("\"", "");
                    String cleanHours = data[7].replace("\"", "");

                    model.Facility f = new model.Facility(
                            data[0], data[1], data[2], cleanAddress, data[4],
                            data[5], data[6], cleanHours, data[8], data[9], data[10]
                    );
                    list.add(f);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading facilities: " + e.getMessage());
        }
        return list;
    }

    // SAVE FACILITIES
    public void saveFacilities(String filePath, List<model.Facility> facilities) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("facility_id,facility_name,facility_type,address,postcode,phone_number,email,opening_hours,manager_name,capacity,specialities_offered");

            for (model.Facility f : facilities) {
                // Re-quote fields with commas
                String safeAddress = "\"" + f.getAddress() + "\"";
                String safeHours = "\"" + f.getOpeningHours() + "\"";

                String record = String.join(",",
                        f.getFacilityID(), f.getName(), f.getType(), safeAddress,
                        f.getPostcode(), f.getPhone(), f.getEmail(), safeHours,
                        f.getManagerName(), f.getCapacity(), f.getSpecialities()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving facilities: " + e.getMessage());
        }
    }


}
