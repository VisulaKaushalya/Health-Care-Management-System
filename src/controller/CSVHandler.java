package controller;

//  Model Imports
import model.Appointment;
import model.Clinician;
import model.Facility;
import model.Patient;
import model.Prescription;
import model.Referral;
import model.Staff;

//  Java Imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {



    // ========================================================================================
    // 1. PATIENTS

    public List<Patient> loadPatients(String filePath) {
        List<Patient> patients = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                // handle commas in addr
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data.length >= 14) {
                    String cleanAddress = data[8].replace("\"", "");
                    Patient p = new Patient(
                            data[0].trim(),  // ID
                            data[1].trim(),  // First Name
                            data[2].trim(),  // Last Name
                            data[3].trim(),  // DOB
                            data[4].trim(),  // NHS Number
                            data[5].trim(),  // Gender
                            data[6].trim(),  // Phone
                            data[7].trim(),  // Email
                            cleanAddress,    // Address
                            data[9].trim(),  // Postcode
                            data[10].trim(), // Emergency Name
                            data[11].trim(), // Emergency Phone
                            data[12].trim(), // Registration Date
                            data[13].trim()  // GP Surgery ID
                    );
                    patients.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading patients: " + e.getMessage());
        }
        return patients;
    }

    public void savePatients(String filePath, List<Patient> patients) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("patient_id,first_name,last_name,date_of_birth,nhs_number,gender,phone_number,email,address,postcode,emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id");

            for (Patient p : patients) {
                String safeAddress = "\"" + p.getAddress() + "\"";
                String record = String.join(",",
                        p.getPatientID(), p.getFirstName(), p.getLastName(), p.getDateOfBirth(),
                        p.getNhsNumber(), p.getGender(), p.getPhoneNumber(), p.getEmail(),
                        safeAddress, p.getPostcode(), p.getEmergencyName(), p.getEmergencyPhone(),
                        p.getRegistrationDate(), p.getGpSurgeryID()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }



    // ====================================================================================
    // 2. CLINICIANS

    public List<Clinician> loadClinicians(String filePath) {
        List<Clinician> clinicians = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 12) {
                    Clinician c = new Clinician(
                            data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),
                            data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(),
                            data[8].trim(), data[9].trim(), data[10].trim(), data[11].trim()
                    );
                    clinicians.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading clinicians: " + e.getMessage());
        }
        return clinicians;
    }

    public void saveClinicians(String filePath, List<Clinician> clinicians) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("clinician_id,first_name,last_name,title,speciality,gmc_number,phone_number,email,workplace_id,workplace_type,employment_status,start_date");
            for (Clinician c : clinicians) {
                String record = String.join(",",
                        c.getClinicianID(), c.getFirstName(), c.getLastName(), c.getTitle(),
                        c.getSpeciality(), c.getGmcNumber(), c.getPhoneNumber(), c.getEmail(),
                        c.getWorkplaceID(), c.getWorkplaceType(), c.getEmploymentStatus(), c.getStartDate()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving clinicians: " + e.getMessage());
        }
    }



    // ======================================================================================
    // 3. APPOINTMENTS

    public List<Appointment> loadAppointments(String filePath) {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 13) {
                    Appointment a = new Appointment(
                            data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),
                            data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(),
                            data[8].trim(), data[9].trim(), data[10].trim(), data[11].trim(), data[12].trim()
                    );
                    appointments.add(a);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading appointments: " + e.getMessage());
        }
        return appointments;
    }

    public void addAppointment(String filePath, Appointment a) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String record = String.join(",",
                    a.getAppointmentID(), a.getPatientID(), a.getClinicianID(), a.getFacilityID(),
                    a.getDate(), a.getTime(), a.getDuration(), a.getType(),
                    a.getStatus(), a.getReason(), "None", "N/A", "N/A"
            );
            out.println(record);
        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    public void saveAllAppointments(String filePath, List<Appointment> appointments) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("appointment_id, patient_id, clinician_id, facility_id, appointment_date, appointment_time, duration_minutes, appointment_type, status, reason_for_visit, notes, created_date, last_modified");
            for (Appointment a : appointments) {
                String record = String.join(",",
                        a.getAppointmentID(), a.getPatientID(), a.getClinicianID(), a.getFacilityID(),
                        a.getDate(), a.getTime(), a.getDuration(), a.getType(),
                        a.getStatus(), a.getReason(), a.getNotes(), a.getCreatedDate(), a.getLastModified()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving all appointments: " + e.getMessage());
        }
    }




    // ===========================================================================================
    // 4. PRESCRIPTIONS

    public List<Prescription> loadPrescriptions(String filePath) {
        List<Prescription> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 14) {
                    Prescription p = new Prescription(
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

    public void savePrescriptions(String filePath, List<Prescription> prescriptions) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("prescription_id,patient_id,clinician_id,appointment_id,prescription_date,medication_name,dosage,frequency,duration_days,quantity,instructions,pharmacy_name,status,issue_date,collection_date");
            for (Prescription p : prescriptions) {
                String record = String.join(",",
                        p.getPrescriptionID(), p.getPatientID(), p.getClinicianID(), p.getAppointmentID(),
                        p.getDate(), p.getMedication(), p.getDosage(), p.getFrequency(),
                        p.getDuration(), p.getQuantity(), "\"" + p.getInstructions() + "\"",
                        p.getPharmacy(), p.getStatus(), p.getIssueDate(), p.getCollectionDate()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving prescriptions: " + e.getMessage());
        }
    }





    // =================================================================================================
    // 5. STAFF

    public List<Staff> loadStaff(String filePath) {
        List<Staff> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 12) {
                    Staff s = new Staff(
                            data[0], data[1], data[2], data[3], data[4], data[5],
                            data[6], data[7], data[8], data[9], data[10], data[11]
                    );
                    list.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading staff: " + e.getMessage());
        }
        return list;
    }

    public void saveStaff(String filePath, List<Staff> staffList) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("staff_id,first_name,last_name,role,department,facility_id,phone_number,email,status,start_date,manager,access_level");
            for (Staff s : staffList) {
                String record = String.join(",",
                        s.getStaffID(), s.getFirstName(), s.getLastName(), s.getRole(),
                        s.getDepartment(), s.getFacilityID(), s.getPhone(), s.getEmail(),
                        s.getStatus(), s.getStartDate(), s.getManager(), s.getAccessLevel()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving staff: " + e.getMessage());
        }
    }







    // ===========================================================================================
    // 6. FACILITIES

    public List<Facility> loadFacilities(String filePath) {
        List<Facility> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 11) {
                    String cleanAddress = data[3].replace("\"", "");
                    String cleanHours = data[7].replace("\"", "");
                    Facility f = new Facility(
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

    public void saveFacilities(String filePath, List<Facility> facilities) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("facility_id,facility_name,facility_type,address,postcode,phone_number,email,opening_hours,manager_name,capacity,specialities_offered");
            for (Facility f : facilities) {
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





    // ================================================================================================
    // 7. REFERRALS

    public List<Referral> loadReferrals(String filePath) {
        List<Referral> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 16) {
                    Referral r = new Referral(
                            data[0], data[1], data[2], data[3], data[4],
                            data[5], data[6], data[7], data[8], data[9],
                            data[10], data[11], data[12], data[13], data[14], data[15]
                    );
                    list.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading referrals: " + e.getMessage());
        }
        return list;
    }

    public void saveReferrals(String filePath, List<Referral> referrals) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            out.println("referral_id,patient_id,referring_clinician_id,referred_to_clinician_id,referring_facility_id,referred_to_facility_id,referral_date,urgency_level,referral_reason,clinical_summary,requested_investigations,status,appointment_id,notes,created_date,last_updated");
            for (Referral r : referrals) {
                String safeSummary = "\"" + r.getSummary() + "\"";
                String safeReason = "\"" + r.getReason() + "\"";
                String safeNotes = "\"" + r.getNotes() + "\"";

                String record = String.join(",",
                        r.getReferralID(), r.getPatientID(), r.getReferringDoctorID(), r.getReferredToDoctorID(),
                        r.getReferringFacilityID(), r.getReferredToFacilityID(), r.getDate(), r.getUrgency(),
                        safeReason, safeSummary, r.getInvestigations(), r.getStatus(),
                        r.getAppointmentID(), safeNotes, r.getCreatedDate(), r.getLastUpdated()
                );
                out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving referrals: " + e.getMessage());
        }
    }
}