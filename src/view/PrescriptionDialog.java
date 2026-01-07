package view;

import model.Appointment;
import model.Clinician;
import model.Patient;
import model.Prescription;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class PrescriptionDialog extends JDialog {
    // 1 Dropdowns
    private JComboBox<String> cmbPatient = new JComboBox<>();
    private JComboBox<String> cmbDoctor = new JComboBox<>();
    private JComboBox<String> cmbAppointment = new JComboBox<>();

    // 2 Medical Fields
    private JTextField txtDate = new JTextField(java.time.LocalDate.now().toString());
    private JTextField txtMedication = new JTextField();
    private JTextField txtDosage = new JTextField();
    private JTextField txtFrequency = new JTextField();
    private JTextField txtDuration = new JTextField();
    private JTextField txtQuantity = new JTextField();
    private JTextField txtInstructions = new JTextField();
    private JTextField txtPharmacy = new JTextField();
    private JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Issued", "Dispensed", "Cancelled"});

    private boolean submitted = false;

    // ---------------- Constructor ----------
    public PrescriptionDialog(Frame owner, Prescription p, List<Patient> patients, List<Clinician> clinicians, List<Appointment> appointments) {
        super(owner, (p == null) ? "Issue Prescription" : "Edit Prescription", true);

        // A Layout Setup
        setSize(500, 700);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // B Load to Dropdowns
        loadDropdowns(patients, clinicians, appointments);

        // C Form Panel
        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Patient:"));        formPanel.add(cmbPatient);
        formPanel.add(new JLabel("Doctor:"));         formPanel.add(cmbDoctor);
        formPanel.add(new JLabel("Link Appointment:")); formPanel.add(cmbAppointment);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):")); formPanel.add(txtDate);
        formPanel.add(new JLabel("Medication:"));     formPanel.add(txtMedication);
        formPanel.add(new JLabel("Dosage:"));         formPanel.add(txtDosage);
        formPanel.add(new JLabel("Frequency:"));      formPanel.add(txtFrequency);
        formPanel.add(new JLabel("Duration (Days):"));formPanel.add(txtDuration);
        formPanel.add(new JLabel("Quantity:"));       formPanel.add(txtQuantity);
        formPanel.add(new JLabel("Instructions:"));   formPanel.add(txtInstructions);
        formPanel.add(new JLabel("Pharmacy:"));       formPanel.add(txtPharmacy);
        formPanel.add(new JLabel("Status:"));         formPanel.add(cmbStatus);

        add(formPanel, BorderLayout.CENTER);

        // D Button Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.SOUTH);

        // E Pre-fill Data If Editing
        if (p != null) {
            setSelectedStart(cmbPatient, p.getPatientID());
            setSelectedStart(cmbDoctor, p.getClinicianID());
            setSelectedStart(cmbAppointment, p.getAppointmentID());

            txtDate.setText(p.getDate());
            txtMedication.setText(p.getMedication());
            txtDosage.setText(p.getDosage());
            txtFrequency.setText(p.getFrequency());
            txtDuration.setText(p.getDuration());
            txtQuantity.setText(p.getQuantity());
            txtInstructions.setText(p.getInstructions());
            txtPharmacy.setText(p.getPharmacy());
            cmbStatus.setSelectedItem(p.getStatus());
        }
    }



    // --------- Helper Methods -----------

    private void loadDropdowns(List<Patient> patients, List<Clinician> clinicians, List<Appointment> appointments) {
        // Patients
        for (Patient pat : patients) {
            cmbPatient.addItem(pat.getPatientID() + " - " + pat.getFirstName() + " " + pat.getLastName());
        }
        // Doctors
        for (Clinician doc : clinicians) {
            cmbDoctor.addItem(doc.getClinicianID() + " - Dr. " + doc.getLastName());
        }
        // Appointments
        cmbAppointment.addItem("N/A - No Appointment");
        if (appointments != null) {
            for (Appointment app : appointments) {
                cmbAppointment.addItem(app.getAppointmentID() + " - " + app.getDate());
            }
        }
    }

    // Helper auto selct with id
    private void setSelectedStart(JComboBox<String> box, String id) {
        if (id == null || id.isEmpty()) return;
        for (int i = 0; i < box.getItemCount(); i++) {
            if (box.getItemAt(i).startsWith(id)) {
                box.setSelectedIndex(i);
                return;
            }
        }
    }

    // --------- Getters ---------
    public boolean isSubmitted() { return submitted; }

    // Logic to extract
    public String getSelectedPatientID() {
        return cmbPatient.getSelectedItem().toString().split(" - ")[0];
    }
    public String getSelectedDoctorID() {
        return cmbDoctor.getSelectedItem().toString().split(" - ")[0];
    }
    public String getSelectedApptID() {
        String selection = cmbAppointment.getSelectedItem().toString();
        if (selection.startsWith("N/A")) return "N/A";
        return selection.split(" - ")[0];
    }

    public String getDate() { return txtDate.getText(); }
    public String getMedication() { return txtMedication.getText(); }
    public String getDosage() { return txtDosage.getText(); }
    public String getFrequency() { return txtFrequency.getText(); }
    public String getDuration() { return txtDuration.getText(); }
    public String getQuantity() { return txtQuantity.getText(); }
    public String getInstructions() { return txtInstructions.getText(); }
    public String getPharmacy() { return txtPharmacy.getText(); }
    public String getStatus() { return cmbStatus.getSelectedItem().toString(); }
}