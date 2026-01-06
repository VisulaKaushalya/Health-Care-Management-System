package view;

import model.Appointment;
import model.Clinician;
import model.Patient;
import model.Staff;
import model.Referral;
import controller.ReferralManager;
import view.ReferralTableModel;
import view.ReferralDialog;

import util.CSVHandler;
import model.Prescription;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {


    public MainFrame() {
        super("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // ---------------- Load ALL Data  ------------------------------------------
        CSVHandler loader = new CSVHandler();

        System.out.println("Loading data..."); // Debug print
        List<Patient> patients = loader.loadPatients("patients.csv");
        List<Clinician> clinicians = loader.loadClinicians("clinicians.csv");
        List<Appointment> appointments = loader.loadAppointments("appointments.csv");
        List<Prescription> prescriptions = loader.loadPrescriptions("prescriptions.csv");
        List<model.Staff> staffList = loader.loadStaff("staff.csv");
        List<model.Facility> facilities = loader.loadFacilities("facilities.csv");
        ReferralManager refManager = ReferralManager.getInstance();
        List<model.Referral> referralList = refManager.getAllReferrals();


        refManager.loadData("referrals.csv");

        JTabbedPane tabbedPane = new JTabbedPane();



        // -------------------------------- PATIENTS ---------------------------------

        JPanel patientPanel = new JPanel(new BorderLayout());
        PatientTableModel patientModel = new PatientTableModel(patients);
        JTable patientTable = new JTable(patientModel);
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JPanel patientTopPanel = new JPanel(new BorderLayout());
        patientTopPanel.add(createSearchPanel(patientTable), BorderLayout.NORTH);

        //  Add Buttons
        JPanel patientButtonPanel = new JPanel();
        JButton btnAddPatient = new JButton("Add Patient");
        JButton btnEditPatient = new JButton("Edit");
        JButton btnDelPatient = new JButton("Delete");

        patientButtonPanel.add(btnAddPatient);
        patientButtonPanel.add(btnEditPatient);
        patientButtonPanel.add(btnDelPatient);

        patientTopPanel.add(patientButtonPanel, BorderLayout.SOUTH);

        // Add the Top Panel
        patientPanel.add(patientTopPanel, BorderLayout.NORTH);
        patientPanel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        // --- PATIENT ACTIONS ---

        // PATIENT
        btnAddPatient.addActionListener(e -> {
            PatientDialog dialog = new PatientDialog(this, null);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String newID = "P" + (patients.size() + 1001); // Generate ID
                String regDate = java.time.LocalDate.now().toString(); // Today's date

                Patient p = new Patient(
                        newID,
                        dialog.getFirstName(),
                        dialog.getLastName(),
                        dialog.getDob(),
                        dialog.getNhsNumber(),  // <--- Now getting real input
                        dialog.getGender(),
                        dialog.getPhone(),
                        dialog.getEmail(),      // <--- Real input
                        dialog.getAddress(),    // <--- Real input
                        dialog.getPostcode(),   // <--- Real input
                        dialog.getEmgName(),    // <--- Real input
                        dialog.getEmgPhone(),   // <--- Real input
                        regDate,                // Auto-set today
                        dialog.getSurgeryID()   // <--- Real input
                );

                patients.add(p);
                patientModel.fireTableDataChanged();
                loader.savePatients("patients.csv", patients);
            }
        });

        // 2. EDIT PATIENT (Full Data)
        btnEditPatient.addActionListener(e -> {
            int row = patientTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a patient to edit.");
            } else {
                int modelRow = patientTable.convertRowIndexToModel(row);
                Patient p = patients.get(modelRow);

                PatientDialog dialog = new PatientDialog(this, p);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    Patient updated = new Patient(
                            p.getPatientID(),       // Keep ID
                            dialog.getFirstName(),  // Updated
                            dialog.getLastName(),   // Updated
                            dialog.getDob(),        // Updated
                            dialog.getNhsNumber(),  // Updated
                            dialog.getGender(),     // Updated
                            dialog.getPhone(),      // Updated
                            dialog.getEmail(),      // Updated
                            dialog.getAddress(),    // Updated
                            dialog.getPostcode(),   // Updated
                            dialog.getEmgName(),    // Updated
                            dialog.getEmgPhone(),   // Updated
                            p.getRegistrationDate(),// Keep original Reg Date
                            dialog.getSurgeryID()   // Updated
                    );

                    patients.set(modelRow, updated);
                    patientModel.fireTableDataChanged();
                    loader.savePatients("patients.csv", patients);
                }
            }
        });
        // 3. DELETE PATIENT
        btnDelPatient.addActionListener(e -> {
            int row = patientTable.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a patient to delete.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete this patient?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        //  Convert View Row -> Model Row
                        int modelRow = patientTable.convertRowIndexToModel(row);

                        // Remove and update
                        patients.remove(modelRow);
                        patientModel.fireTableDataChanged();

                        // Save to File
                        System.out.println("Saving delete to file...");
                        loader.savePatients("patients.csv", patients);

                        JOptionPane.showMessageDialog(this, "Patient Deleted Successfully.");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
                    }
                }
            }
        });










// --------------------------------------------DOCTORS ---------------------------------------------------------
        JPanel doctorPanel = new JPanel(new BorderLayout());
        ClinicianTableModel docModel = new ClinicianTableModel(clinicians);
        JTable docTable = new JTable(docModel);
        docTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // A. Top Panel (Search + Buttons)
        JPanel doctorTopPanel = new JPanel(new BorderLayout());
        doctorTopPanel.add(createSearchPanel(docTable), BorderLayout.NORTH);

        JPanel docButtonPanel = new JPanel();
        JButton btnAddDoc = new JButton("Add Doctor");
        JButton btnEditDoc = new JButton("Edit");
        JButton btnDelDoc = new JButton("Delete");

        docButtonPanel.add(btnAddDoc);
        docButtonPanel.add(btnEditDoc);
        docButtonPanel.add(btnDelDoc);

        doctorTopPanel.add(docButtonPanel, BorderLayout.SOUTH);

        doctorPanel.add(doctorTopPanel, BorderLayout.NORTH);
        doctorPanel.add(new JScrollPane(docTable), BorderLayout.CENTER);


        //  ADD DOCTOR
        btnAddDoc.addActionListener(e -> {
            ClinicianDialog dialog = new ClinicianDialog(this, null);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String newID = "C" + (clinicians.size() + 1001);
                String today = java.time.LocalDate.now().toString();

                Clinician c = new Clinician(
                        newID, dialog.getFirstName(), dialog.getLastName(), dialog.getTitle(),
                        dialog.getSpeciality(), dialog.getGmc(), dialog.getPhone(), dialog.getEmail(),
                        dialog.getWorkplaceID(), dialog.getWorkplaceType(), dialog.getStatus(), today
                );

                clinicians.add(c);
                docModel.fireTableDataChanged();
                loader.saveClinicians("clinicians.csv", clinicians);
            }
        });

        //  EDIT DOCTOR
        btnEditDoc.addActionListener(e -> {
            int row = docTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a doctor to edit.");
            } else {
                int modelRow = docTable.convertRowIndexToModel(row);
                Clinician c = clinicians.get(modelRow);

                ClinicianDialog dialog = new ClinicianDialog(this, c);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    Clinician updated = new Clinician(
                            c.getClinicianID(), // Keep ID
                            dialog.getFirstName(), dialog.getLastName(), dialog.getTitle(),
                            dialog.getSpeciality(), dialog.getGmc(), dialog.getPhone(), dialog.getEmail(),
                            dialog.getWorkplaceID(), dialog.getWorkplaceType(), dialog.getStatus(),
                            c.getStartDate() // Keep start date
                    );

                    clinicians.set(modelRow, updated);
                    docModel.fireTableDataChanged();
                    loader.saveClinicians("clinicians.csv", clinicians);
                }
            }
        });

        //  DELETE DOCTOR
        btnDelDoc.addActionListener(e -> {
            int row = docTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a doctor to delete.");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Delete this doctor?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int modelRow = docTable.convertRowIndexToModel(row);
                    clinicians.remove(modelRow);
                    docModel.fireTableDataChanged();
                    loader.saveClinicians("clinicians.csv", clinicians);
                }
            }
        });






















        //-------------------------APPOINTMENTS -----------------------------------
        JPanel appointmentPanel = new JPanel(new BorderLayout());

        //  Setup Model & Table
        AppointmentTableModel apptModel = new AppointmentTableModel(appointments, patients, clinicians);
        JTable apptTable = new JTable(apptModel);
        apptTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        appointmentPanel.add(createSearchPanel(apptTable), BorderLayout.NORTH);
        appointmentPanel.add(new JScrollPane(apptTable), BorderLayout.CENTER);

        //  Button Panel
        JPanel buttonPanel = new JPanel();

        JButton bookButton = new JButton("Book Appointment");
        JButton cancelButton = new JButton("Cancel Appointment");

        // Styling
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setForeground(Color.RED);

        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);

        appointmentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Book Appointment
        bookButton.addActionListener(e -> {
            AddAppointmentDialog dialog = new AddAppointmentDialog(this, patients, clinicians);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String rawPatient = dialog.getSelectedPatient();
                String patientID = rawPatient.split(" - ")[0];
                String rawDoctor = dialog.getSelectedDoctor();
                String doctorID = rawDoctor.split(" - ")[0];
                String newID = "A" + (appointments.size() + 100);

                Appointment newAppt = new Appointment(
                        newID, patientID, doctorID, "General-Room",
                        dialog.getDate(), dialog.getTime(), "30", "Consultation",
                        "Active", dialog.getReason(), "None", "Today", "Today"
                );

                appointments.add(newAppt);
                apptModel.fireTableDataChanged();
                loader.addAppointment("appointments.csv", newAppt);

                JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!");
            }
        });

        // Cancel Appointment
        cancelButton.addActionListener(e -> {
            int selectedRow = apptTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment to cancel.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to cancel this appointment?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove from list
                    appointments.remove(selectedRow);
                    // Refresh table
                    apptModel.fireTableDataChanged();
                    // Save to file
                    loader.saveAllAppointments("appointments.csv", appointments);

                    JOptionPane.showMessageDialog(this, "Appointment Cancelled.");
                }
            }
        });









        //------------------------------ PRESCRIPTIONS ---------------------------------------
        JPanel prescriptionPanel = new JPanel(new BorderLayout());
        PrescriptionTableModel rxModel = new PrescriptionTableModel(prescriptions, patients, clinicians);
        JTable rxTable = new JTable(rxModel);
        rxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //  Top Panel (Search + Buttons)
        JPanel rxTopPanel = new JPanel(new BorderLayout());
        rxTopPanel.add(createSearchPanel(rxTable), BorderLayout.NORTH);

        JPanel rxButtonPanel = new JPanel();
        JButton btnAddRx = new JButton("Issue Prescription");
        JButton btnEditRx = new JButton("Edit");
        JButton btnDelRx = new JButton("Delete");

        rxButtonPanel.add(btnAddRx);
        rxButtonPanel.add(btnEditRx);
        rxButtonPanel.add(btnDelRx);

        rxTopPanel.add(rxButtonPanel, BorderLayout.SOUTH);

        prescriptionPanel.add(rxTopPanel, BorderLayout.NORTH);
        prescriptionPanel.add(new JScrollPane(rxTable), BorderLayout.CENTER);

        // --- PRESCRIPTION ACTIONS ---

        // ADD PRESCRIPTION
        btnAddRx.addActionListener(e -> {
            // Pass patients & clinicians lists to the dialog
            PrescriptionDialog dialog = new PrescriptionDialog(this, null, patients, clinicians, appointments);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String newID = "RX" + (prescriptions.size() + 1001);

                Prescription p = new Prescription(
                        newID,
                        dialog.getSelectedPatientID(),
                        dialog.getSelectedDoctorID(),
                        dialog.getSelectedApptID(),
                        dialog.getDate(),
                        dialog.getMedication(),
                        dialog.getDosage(),
                        dialog.getFrequency(),
                        dialog.getDuration(),
                        dialog.getQuantity(),
                        dialog.getInstructions(),
                        dialog.getPharmacy(),
                        dialog.getStatus(),
                        dialog.getDate(), // Issue Date = Date
                        "" // Collection Date (Empty initially)
                );

                prescriptions.add(p);
                rxModel.fireTableDataChanged();
                loader.savePrescriptions("prescriptions.csv", prescriptions);
            }
        });

        //  EDIT PRESCRIPTION
        btnEditRx.addActionListener(e -> {
            int row = rxTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a prescription to edit.");
            } else {
                int modelRow = rxTable.convertRowIndexToModel(row);
                Prescription p = prescriptions.get(modelRow);

                PrescriptionDialog dialog = new PrescriptionDialog(this, p, patients, clinicians, appointments);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    Prescription updated = new Prescription(
                            p.getPrescriptionID(),
                            dialog.getSelectedPatientID(),
                            dialog.getSelectedDoctorID(),
                            dialog.getSelectedApptID(),
                            dialog.getDate(),
                            dialog.getMedication(),
                            dialog.getDosage(),
                            dialog.getFrequency(),
                            dialog.getDuration(),
                            dialog.getQuantity(),
                            dialog.getInstructions(),
                            dialog.getPharmacy(),
                            dialog.getStatus(),
                            p.getIssueDate(),
                            p.getCollectionDate()
                    );

                    prescriptions.set(modelRow, updated);
                    rxModel.fireTableDataChanged();
                    loader.savePrescriptions("prescriptions.csv", prescriptions);
                }
            }
        });

        // DELETE PRESCRIPTION
        btnDelRx.addActionListener(e -> {
            int row = rxTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a prescription to delete.");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Delete this prescription?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int modelRow = rxTable.convertRowIndexToModel(row);
                    prescriptions.remove(modelRow);
                    rxModel.fireTableDataChanged();
                    loader.savePrescriptions("prescriptions.csv", prescriptions);
                }
            }
        });







        // ----------------------------------Staff--------------------------------------------------------


        JPanel staffPanel = new JPanel(new BorderLayout());
        StaffTableModel staffModel = new StaffTableModel(staffList); // Make sure you have this class!
        JTable staffTable = new JTable(staffModel);
        staffTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //  Top Panel (Search + Buttons)
        JPanel staffTopPanel = new JPanel(new BorderLayout());
        staffTopPanel.add(createSearchPanel(staffTable), BorderLayout.NORTH);

        JPanel staffButtonPanel = new JPanel();
        JButton btnAddStaff = new JButton("Add Staff");
        JButton btnEditStaff = new JButton("Edit");
        JButton btnDelStaff = new JButton("Delete");

        staffButtonPanel.add(btnAddStaff);
        staffButtonPanel.add(btnEditStaff);
        staffButtonPanel.add(btnDelStaff);

        staffTopPanel.add(staffButtonPanel, BorderLayout.SOUTH);

        staffPanel.add(staffTopPanel, BorderLayout.NORTH);
        staffPanel.add(new JScrollPane(staffTable), BorderLayout.CENTER);

        // --- STAFF ACTIONS ---

        //  ADD STAFF
        btnAddStaff.addActionListener(e -> {
            StaffDialog dialog = new StaffDialog(this, null);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String newID = "S" + (staffList.size() + 1001);
                String today = java.time.LocalDate.now().toString();

                model.Staff s = new model.Staff(
                        newID, dialog.getFirstName(), dialog.getLastName(), dialog.getRole(),
                        dialog.getDepartment(), dialog.getFacility(), dialog.getPhone(),
                        dialog.getEmail(), dialog.getStatus(), today,
                        dialog.getManager(), dialog.getAccess()
                );

                staffList.add(s);
                staffModel.fireTableDataChanged();
                loader.saveStaff("staff.csv", staffList);
            }
        });

        //  EDIT STAFF
        btnEditStaff.addActionListener(e -> {
            int row = staffTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a staff member to edit.");
            } else {
                int modelRow = staffTable.convertRowIndexToModel(row);
                model.Staff s = staffList.get(modelRow);

                StaffDialog dialog = new StaffDialog(this, s);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    model.Staff updated = new model.Staff(
                            s.getStaffID(), // Keep ID
                            dialog.getFirstName(), dialog.getLastName(), dialog.getRole(),
                            dialog.getDepartment(), dialog.getFacility(), dialog.getPhone(),
                            dialog.getEmail(), dialog.getStatus(), s.getStartDate(), // Keep Start Date
                            dialog.getManager(), dialog.getAccess()
                    );

                    staffList.set(modelRow, updated);
                    staffModel.fireTableDataChanged();
                    loader.saveStaff("staff.csv", staffList);
                }
            }
        });

        //  DELETE STAFF
        btnDelStaff.addActionListener(e -> {
            int row = staffTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a staff member to delete.");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Delete this staff member?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int modelRow = staffTable.convertRowIndexToModel(row);
                    staffList.remove(modelRow);
                    staffModel.fireTableDataChanged();
                    loader.saveStaff("staff.csv", staffList);
                }
            }
        });


















        // ------------------------------ FACILITIES ------------------------------------------------------
        JPanel facilityPanel = new JPanel(new BorderLayout());
        FacilityTableModel facilityModel = new FacilityTableModel(facilities);
        JTable facilityTable = new JTable(facilityModel);
        facilityTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // A. Top Panel (Search + Buttons)
        JPanel facTopPanel = new JPanel(new BorderLayout());
        facTopPanel.add(createSearchPanel(facilityTable), BorderLayout.NORTH);

        JPanel facButtonPanel = new JPanel();
        JButton btnAddFac = new JButton("Add Facility");
        JButton btnEditFac = new JButton("Edit");
        JButton btnDelFac = new JButton("Delete");

        facButtonPanel.add(btnAddFac);
        facButtonPanel.add(btnEditFac);
        facButtonPanel.add(btnDelFac);

        facTopPanel.add(facButtonPanel, BorderLayout.SOUTH);

        facilityPanel.add(facTopPanel, BorderLayout.NORTH);
        facilityPanel.add(new JScrollPane(facilityTable), BorderLayout.CENTER);

        // --- FACILITY ACTIONS ---

        // 1. ADD FACILITY
        btnAddFac.addActionListener(e -> {
            FacilityDialog dialog = new FacilityDialog(this, null);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String newID = "F" + (facilities.size() + 101); // Simple ID generation

                model.Facility f = new model.Facility(
                        newID, dialog.getName(), dialog.getFacilityType(), dialog.getAddress(),
                        dialog.getPostcode(), dialog.getPhone(), dialog.getEmail(),
                        dialog.getHours(), dialog.getManager(), dialog.getCapacity(),
                        dialog.getSpecialities()
                );

                facilities.add(f);
                facilityModel.fireTableDataChanged();
                loader.saveFacilities("facilities.csv", facilities);
            }
        });

        // 2. EDIT FACILITY
        btnEditFac.addActionListener(e -> {
            int row = facilityTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a facility to edit.");
            } else {
                int modelRow = facilityTable.convertRowIndexToModel(row);
                model.Facility f = facilities.get(modelRow);

                FacilityDialog dialog = new FacilityDialog(this, f);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    model.Facility updated = new model.Facility(
                            f.getFacilityID(), // Keep ID
                            dialog.getName(), dialog.getFacilityType(), dialog.getAddress(),
                            dialog.getPostcode(), dialog.getPhone(), dialog.getEmail(),
                            dialog.getHours(), dialog.getManager(), dialog.getCapacity(),
                            dialog.getSpecialities()
                    );

                    facilities.set(modelRow, updated);
                    facilityModel.fireTableDataChanged();
                    loader.saveFacilities("facilities.csv", facilities);
                }
            }
        });

        // 3. DELETE FACILITY
        btnDelFac.addActionListener(e -> {
            int row = facilityTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a facility to delete.");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Delete this facility?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int modelRow = facilityTable.convertRowIndexToModel(row);
                    facilities.remove(modelRow);
                    facilityModel.fireTableDataChanged();
                    loader.saveFacilities("facilities.csv", facilities);
                }
            }
        });





        // --- TAB 7: REFERRALS (Using Singleton) ---
        JPanel refPanel = new JPanel(new BorderLayout());

        // Model gets the list from the Singleton
        ReferralTableModel refModel = new ReferralTableModel(refManager.getAllReferrals());
        JTable refTable = new JTable(refModel);
        refTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Top Panel
        JPanel refTopPanel = new JPanel(new BorderLayout());
        refTopPanel.add(createSearchPanel(refTable), BorderLayout.NORTH);

        JPanel refButtonPanel = new JPanel();
        JButton btnAddRef = new JButton("Create Referral");
        JButton btnEditRef = new JButton("Edit");
        JButton btnDelRef = new JButton("Delete");

        refButtonPanel.add(btnAddRef);
        refButtonPanel.add(btnEditRef);
        refButtonPanel.add(btnDelRef);

        refTopPanel.add(refButtonPanel, BorderLayout.SOUTH);

        refPanel.add(refTopPanel, BorderLayout.NORTH);
        refPanel.add(new JScrollPane(refTable), BorderLayout.CENTER);

        // --- REFERRAL ACTIONS ---

        // 1. ADD REFERRAL
        btnAddRef.addActionListener(e -> {
            // Pass the lists for Dropdowns (Patients, Docs, Facilities)
            ReferralDialog dialog = new ReferralDialog(this, null, patients, clinicians, facilities);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String newID = "R" + (refManager.getAllReferrals().size() + 1001);
                String today = java.time.LocalDate.now().toString();

                model.Referral r = new model.Referral(
                        newID,
                        dialog.getPatientID(),
                        dialog.getRefDoctorID(),
                        dialog.getToDoctorID(),
                        dialog.getFromFacilityID(),
                        dialog.getToFacilityID(),
                        dialog.getDate(),
                        dialog.getUrgency(),
                        dialog.getReason(),
                        dialog.getSummary(),
                        dialog.getInvestigations(),
                        dialog.getStatus(),
                        dialog.getApptID(),
                        dialog.getNotes(),
                        today, // Created Date
                        today  // Last Updated
                );

                // USE SINGLETON TO ADD & SAVE
                refManager.addReferral(r);
                refModel.fireTableDataChanged();
                refManager.saveData("referrals.csv");
            }
        });

        // 2. EDIT REFERRAL
        btnEditRef.addActionListener(e -> {
            int row = refTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a referral to edit.");
            } else {
                int modelRow = refTable.convertRowIndexToModel(row);
                model.Referral r = refManager.getAllReferrals().get(modelRow);

                ReferralDialog dialog = new ReferralDialog(this, r, patients, clinicians, facilities);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    String today = java.time.LocalDate.now().toString();

                    model.Referral updated = new model.Referral(
                            r.getReferralID(),
                            dialog.getPatientID(),
                            dialog.getRefDoctorID(),
                            dialog.getToDoctorID(),
                            dialog.getFromFacilityID(),
                            dialog.getToFacilityID(),
                            dialog.getDate(),
                            dialog.getUrgency(),
                            dialog.getReason(),
                            dialog.getSummary(),
                            dialog.getInvestigations(),
                            dialog.getStatus(),
                            dialog.getApptID(),
                            dialog.getNotes(),
                            r.getCreatedDate(), // Keep Created Date
                            today // Update "Last Updated"
                    );

                    // USE SINGLETON TO UPDATE
                    refManager.updateReferral(modelRow, updated);
                    refModel.fireTableDataChanged();
                    refManager.saveData("referrals.csv");
                }
            }
        });

        // 3. DELETE REFERRAL
        btnDelRef.addActionListener(e -> {
            int row = refTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a referral to delete.");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Delete this referral?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int modelRow = refTable.convertRowIndexToModel(row);

                    // USE SINGLETON TO DELETE
                    refManager.deleteReferral(modelRow);
                    refModel.fireTableDataChanged();
                    refManager.saveData("referrals.csv");
                }
            }
        });


















        // Add Tabs
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Doctors", doctorPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);
        tabbedPane.addTab("Prescriptions", prescriptionPanel);
        tabbedPane.addTab("Staff", staffPanel);
        tabbedPane.addTab("Facilities", facilityPanel);
        tabbedPane.addTab("Referrals", refPanel);

        add(tabbedPane);
        setVisible(true);



    }




    // search/filter
    private JPanel createSearchPanel(JTable table) {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search: ");
        JTextField searchField = new JTextField(20); // 20 columns wide

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        // 1. Set up the Sorter
        TableRowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        // 2. Add Listener to Filter when typing
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filter(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filter(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null); // Reset
                } else {
                    // (?i) means Case-Insensitive (A = a)
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        return searchPanel;
    }
}