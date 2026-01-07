package view;

// 1 Model Imports
import model.Appointment;
import model.Clinician;
import model.Facility;
import model.Patient;
import model.Prescription;
import model.Referral;
import model.Staff;

// 2 Controller
import controller.ReferralManager;
import controller.CSVHandler;
import controller.ReportGenerator;

// 3 View Imports
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    // ---------- CLASS FIELDS  --------------
    private CSVHandler loader;
    private ReferralManager refManager;

    private List<Patient> patients;
    private List<Clinician> clinicians;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private List<Staff> staffList;
    private List<Facility> facilities;


    // --------- CONSTRUCTOR ------------
    public MainFrame() {
        super("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // 1 Load Data
        initializeData();

        // 2 Setup UI Tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Patients", createPatientPanel());
        tabbedPane.addTab("Doctors", createDoctorPanel());
        tabbedPane.addTab("Appointments", createAppointmentPanel());
        tabbedPane.addTab("Prescriptions", createPrescriptionPanel());
        tabbedPane.addTab("Staff", createStaffPanel());
        tabbedPane.addTab("Facilities", createFacilityPanel());
        tabbedPane.addTab("Referrals", createReferralPanel());

        add(tabbedPane);
        setVisible(true);
    }

    // ------------ Data initializatin -----------
    private void initializeData() {
        System.out.println("Loading data...");
        loader = new CSVHandler();

        patients = loader.loadPatients("patients.csv");
        clinicians = loader.loadClinicians("clinicians.csv");
        appointments = loader.loadAppointments("appointments.csv");
        prescriptions = loader.loadPrescriptions("prescriptions.csv");
        staffList = loader.loadStaff("staff.csv");
        facilities = loader.loadFacilities("facilities.csv");

        // Singleton for Referrals
        refManager = ReferralManager.getInstance();
        refManager.loadData("referrals.csv");
    }



    // =======================================================================================
    // TAB 1: PATIENTS

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        PatientTableModel model = new PatientTableModel(patients, facilities);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createSearchPanel(table), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add Patient");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDel);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> {
            PatientDialog dialog = new PatientDialog(this, null);
            dialog.setVisible(true);
            if (dialog.isSubmitted()) {
                String newID = "P" + (patients.size() + 1001);
                Patient p = new Patient(
                        newID, dialog.getFirstName(), dialog.getLastName(), dialog.getDob(),
                        dialog.getNhsNumber(), dialog.getGender(), dialog.getPhone(), dialog.getEmail(),
                        dialog.getAddress(), dialog.getPostcode(), dialog.getEmgName(), dialog.getEmgPhone(),
                        java.time.LocalDate.now().toString(), dialog.getSurgeryID()
                );
                patients.add(p);
                model.fireTableDataChanged();
                loader.savePatients("patients.csv", patients);
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a patient to edit.");
            } else {
                int modelRow = table.convertRowIndexToModel(row);
                Patient p = patients.get(modelRow);
                PatientDialog dialog = new PatientDialog(this, p);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    Patient updated = new Patient(
                            p.getPatientID(), dialog.getFirstName(), dialog.getLastName(), dialog.getDob(),
                            dialog.getNhsNumber(), dialog.getGender(), dialog.getPhone(), dialog.getEmail(),
                            dialog.getAddress(), dialog.getPostcode(), dialog.getEmgName(), dialog.getEmgPhone(),
                            p.getRegistrationDate(), dialog.getSurgeryID()
                    );
                    patients.set(modelRow, updated);
                    model.fireTableDataChanged();
                    loader.savePatients("patients.csv", patients);
                }
            }
        });

        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete patient?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                patients.remove(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                loader.savePatients("patients.csv", patients);
            }
        });

        return panel;
    }





    // ============================================================================================
    // TAB 2: DOCTORS

    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        ClinicianTableModel model = new ClinicianTableModel(clinicians, facilities);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createSearchPanel(table), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add Doctor");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDel);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> {
            ClinicianDialog dialog = new ClinicianDialog(this, null);
            dialog.setVisible(true);
            if (dialog.isSubmitted()) {
                String newID = "C" + (clinicians.size() + 1001);
                Clinician c = new Clinician(
                        newID, dialog.getFirstName(), dialog.getLastName(), dialog.getTitle(),
                        dialog.getSpeciality(), dialog.getGmc(), dialog.getPhone(), dialog.getEmail(),
                        dialog.getWorkplaceID(), dialog.getWorkplaceType(), dialog.getStatus(), java.time.LocalDate.now().toString()
                );
                clinicians.add(c);
                model.fireTableDataChanged();
                loader.saveClinicians("clinicians.csv", clinicians);
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int modelRow = table.convertRowIndexToModel(row);
                Clinician c = clinicians.get(modelRow);
                ClinicianDialog dialog = new ClinicianDialog(this, c);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    Clinician updated = new Clinician(
                            c.getClinicianID(), dialog.getFirstName(), dialog.getLastName(), dialog.getTitle(),
                            dialog.getSpeciality(), dialog.getGmc(), dialog.getPhone(), dialog.getEmail(),
                            dialog.getWorkplaceID(), dialog.getWorkplaceType(), dialog.getStatus(), c.getStartDate()
                    );
                    clinicians.set(modelRow, updated);
                    model.fireTableDataChanged();
                    loader.saveClinicians("clinicians.csv", clinicians);
                }
            }
        });

        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete doctor?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                clinicians.remove(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                loader.saveClinicians("clinicians.csv", clinicians);
            }
        });

        return panel;
    }






    // =================================================================================
    // TAB 3: APPOINTMENTS

    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        AppointmentTableModel model = new AppointmentTableModel(appointments, patients, clinicians, facilities);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        panel.add(createSearchPanel(table), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnBook = new JButton("Book Appointment");
        JButton btnEdit = new JButton("Edit");
        JButton btnCancel = new JButton("Cancel Appointment");
        btnCancel.setForeground(Color.RED);

        btnPanel.add(btnBook); btnPanel.add(btnEdit); btnPanel.add(btnCancel);
        panel.add(btnPanel, BorderLayout.SOUTH);

        // 1 BOOK APPOINTMENT
        btnBook.addActionListener(e -> {

            AddAppointmentDialog dialog = new AddAppointmentDialog(this, null, patients, clinicians, facilities);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String patientID = dialog.getSelectedPatient().split(" - ")[0];
                String doctorID = dialog.getSelectedDoctor().split(" - ")[0];
                String newID = "A" + (appointments.size() + 1001);

                Appointment newAppt = new Appointment(
                        newID, patientID, doctorID,
                        dialog.getFacilityID(),
                        dialog.getDate(), dialog.getTime(),
                        dialog.getDuration(),
                        dialog.getAppointmentType(),
                        dialog.getStatus(),
                        dialog.getReason(),
                        dialog.getNotes(),
                        java.time.LocalDate.now().toString(),
                        java.time.LocalDate.now().toString()
                );
                appointments.add(newAppt);
                model.fireTableDataChanged();
                loader.saveAllAppointments("appointments.csv", appointments);
            }
        });

        // 2 EDIT APPOINTMENT
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an appointment to edit.");
            } else {
                int modelRow = table.convertRowIndexToModel(row);
                Appointment appt = appointments.get(modelRow);


                AddAppointmentDialog dialog = new AddAppointmentDialog(this, appt, patients, clinicians, facilities);
                dialog.setVisible(true);

                if (dialog.isSubmitted()) {
                    String patientID = dialog.getSelectedPatient().split(" - ")[0];
                    String doctorID = dialog.getSelectedDoctor().split(" - ")[0];

                    Appointment updated = new Appointment(
                            appt.getAppointmentID(),
                            patientID, doctorID,
                            dialog.getFacilityID(),
                            dialog.getDate(), dialog.getTime(),
                            dialog.getDuration(),
                            dialog.getAppointmentType(),
                            dialog.getStatus(),
                            dialog.getReason(),
                            dialog.getNotes(),
                            appt.getCreatedDate(),
                            java.time.LocalDate.now().toString()
                    );

                    appointments.set(modelRow, updated);
                    model.fireTableDataChanged();
                    loader.saveAllAppointments("appointments.csv", appointments);
                }
            }
        });

        // 3 CANCEL APPOINTMENT
        btnCancel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Cancel appointment?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                appointments.remove(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                loader.saveAllAppointments("appointments.csv", appointments);
            }
        });

        return panel;
    }




    // ============================================================================================
    // TAB 4: PRESCRIPTIONS

    private JPanel createPrescriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        PrescriptionTableModel model = new PrescriptionTableModel(prescriptions, patients, clinicians);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createSearchPanel(table), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Issue Prescription");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");
        JButton btnPrint = new JButton("Print to File");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDel); btnPanel.add(btnPrint);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> {
            PrescriptionDialog dialog = new PrescriptionDialog(this, null, patients, clinicians, appointments);
            dialog.setVisible(true);
            if (dialog.isSubmitted()) {
                String newID = "RX" + (prescriptions.size() + 1001);
                Prescription p = new Prescription(
                        newID, dialog.getSelectedPatientID(), dialog.getSelectedDoctorID(), dialog.getSelectedApptID(),
                        dialog.getDate(), dialog.getMedication(), dialog.getDosage(), dialog.getFrequency(),
                        dialog.getDuration(), dialog.getQuantity(), dialog.getInstructions(), dialog.getPharmacy(),
                        dialog.getStatus(), dialog.getDate(), ""
                );
                prescriptions.add(p);
                model.fireTableDataChanged();
                loader.savePrescriptions("prescriptions.csv", prescriptions);
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int modelRow = table.convertRowIndexToModel(row);
                Prescription p = prescriptions.get(modelRow);
                PrescriptionDialog dialog = new PrescriptionDialog(this, p, patients, clinicians, appointments);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    Prescription updated = new Prescription(
                            p.getPrescriptionID(), dialog.getSelectedPatientID(), dialog.getSelectedDoctorID(), dialog.getSelectedApptID(),
                            dialog.getDate(), dialog.getMedication(), dialog.getDosage(), dialog.getFrequency(),
                            dialog.getDuration(), dialog.getQuantity(), dialog.getInstructions(), dialog.getPharmacy(),
                            dialog.getStatus(), p.getIssueDate(), p.getCollectionDate()
                    );
                    prescriptions.set(modelRow, updated);
                    model.fireTableDataChanged();
                    loader.savePrescriptions("prescriptions.csv", prescriptions);
                }
            }
        });

        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete prescription?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                prescriptions.remove(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                loader.savePrescriptions("prescriptions.csv", prescriptions);
            }
        });

        btnPrint.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                ReportGenerator.generatePrescription(prescriptions.get(table.convertRowIndexToModel(row)));
            } else {
                JOptionPane.showMessageDialog(this, "Select a prescription to print.");
            }
        });

        return panel;
    }






    // ===========================================================================================
    // TAB 5: STAFF

    private JPanel createStaffPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        StaffTableModel model = new StaffTableModel(staffList, facilities);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createSearchPanel(table), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add Staff");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDel);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> {
            StaffDialog dialog = new StaffDialog(this, null);
            dialog.setVisible(true);
            if (dialog.isSubmitted()) {
                String newID = "S" + (staffList.size() + 1001);
                Staff s = new Staff(
                        newID, dialog.getFirstName(), dialog.getLastName(), dialog.getRole(),
                        dialog.getDepartment(),
                        dialog.getFacilityID(),
                        dialog.getPhone(), dialog.getEmail(),
                        dialog.getStatus(), java.time.LocalDate.now().toString(), dialog.getManager(), dialog.getAccess()
                );
                staffList.add(s);
                model.fireTableDataChanged();
                loader.saveStaff("staff.csv", staffList);
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int modelRow = table.convertRowIndexToModel(row);
                Staff s = staffList.get(modelRow);
                StaffDialog dialog = new StaffDialog(this, s);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    Staff updated = new Staff(
                            s.getStaffID(), dialog.getFirstName(), dialog.getLastName(), dialog.getRole(),
                            dialog.getDepartment(),
                            dialog.getFacilityID(),
                            dialog.getPhone(), dialog.getEmail(),
                            dialog.getStatus(), s.getStartDate(), dialog.getManager(), dialog.getAccess()
                    );
                    staffList.set(modelRow, updated);
                    model.fireTableDataChanged();
                    loader.saveStaff("staff.csv", staffList);
                }
            }
        });

        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete staff?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                staffList.remove(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                loader.saveStaff("staff.csv", staffList);
            }
        });

        return panel;
    }








    // =================================================================================
    // TAB 6: FACILITIES

    private JPanel createFacilityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        FacilityTableModel model = new FacilityTableModel(facilities);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createSearchPanel(table), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add Facility");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDel);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> {
            FacilityDialog dialog = new FacilityDialog(this, null);
            dialog.setVisible(true);
            if (dialog.isSubmitted()) {
                String newID = "F" + (facilities.size() + 101);
                Facility f = new Facility(
                        newID, dialog.getName(), dialog.getFacilityType(), dialog.getAddress(),
                        dialog.getPostcode(), dialog.getPhone(), dialog.getEmail(),
                        dialog.getHours(), dialog.getManager(), dialog.getCapacity(), dialog.getSpecialities()
                );
                facilities.add(f);
                model.fireTableDataChanged();
                loader.saveFacilities("facilities.csv", facilities);
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int modelRow = table.convertRowIndexToModel(row);
                Facility f = facilities.get(modelRow);
                FacilityDialog dialog = new FacilityDialog(this, f);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    Facility updated = new Facility(
                            f.getFacilityID(), dialog.getName(), dialog.getFacilityType(), dialog.getAddress(),
                            dialog.getPostcode(), dialog.getPhone(), dialog.getEmail(),
                            dialog.getHours(), dialog.getManager(), dialog.getCapacity(), dialog.getSpecialities()
                    );
                    facilities.set(modelRow, updated);
                    model.fireTableDataChanged();
                    loader.saveFacilities("facilities.csv", facilities);
                }
            }
        });

        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete facility?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                facilities.remove(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                loader.saveFacilities("facilities.csv", facilities);
            }
        });

        return panel;
    }








    // =================================================================================
    // TAB 7: REFERRALS ( Singleton )

    private JPanel createReferralPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        ReferralTableModel model = new ReferralTableModel(refManager.getAllReferrals(), patients, clinicians, facilities);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createSearchPanel(table), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Create Referral");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");
        JButton btnPrint = new JButton("Print Letter");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDel); btnPanel.add(btnPrint);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> {
            ReferralDialog dialog = new ReferralDialog(this, null, patients, clinicians, facilities);
            dialog.setVisible(true);
            if (dialog.isSubmitted()) {
                String newID = "R" + (refManager.getAllReferrals().size() + 1001);
                String today = java.time.LocalDate.now().toString();
                Referral r = new Referral(
                        newID, dialog.getPatientID(), dialog.getRefDoctorID(), dialog.getToDoctorID(),
                        dialog.getFromFacilityID(), dialog.getToFacilityID(), dialog.getDate(),
                        dialog.getUrgency(), dialog.getReason(), dialog.getSummary(),
                        dialog.getInvestigations(), dialog.getStatus(), dialog.getApptID(),
                        dialog.getNotes(), today, today
                );
                refManager.addReferral(r);
                model.fireTableDataChanged();
                refManager.saveData("referrals.csv");
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int modelRow = table.convertRowIndexToModel(row);
                Referral r = refManager.getAllReferrals().get(modelRow);
                ReferralDialog dialog = new ReferralDialog(this, r, patients, clinicians, facilities);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    Referral updated = new Referral(
                            r.getReferralID(), dialog.getPatientID(), dialog.getRefDoctorID(), dialog.getToDoctorID(),
                            dialog.getFromFacilityID(), dialog.getToFacilityID(), dialog.getDate(),
                            dialog.getUrgency(), dialog.getReason(), dialog.getSummary(),
                            dialog.getInvestigations(), dialog.getStatus(), dialog.getApptID(),
                            dialog.getNotes(), r.getCreatedDate(), java.time.LocalDate.now().toString()
                    );
                    refManager.updateReferral(modelRow, updated);
                    model.fireTableDataChanged();
                    refManager.saveData("referrals.csv");
                }
            }
        });

        btnDel.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete referral?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                refManager.deleteReferral(table.convertRowIndexToModel(row));
                model.fireTableDataChanged();
                refManager.saveData("referrals.csv");
            }
        });

        btnPrint.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                ReportGenerator.generateReferral(refManager.getAllReferrals().get(table.convertRowIndexToModel(row)));
            } else {
                JOptionPane.showMessageDialog(this, "Select a referral to print.");
            }
        });

        return panel;
    }







    // =================================================================================
    // HELPER: Search Panel Logic

    private JPanel createSearchPanel(JTable table) {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search: ");
        JTextField searchField = new JTextField(20);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        TableRowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        return searchPanel;
    }
}