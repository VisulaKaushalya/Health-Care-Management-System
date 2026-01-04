package view;

import model.Prescription;
import model.Patient;
import model.Clinician;
import model.Appointment;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrescriptionDialog extends JDialog {
    // Dropdowns
    private JComboBox<String> cmbPatient = new JComboBox<>();
    private JComboBox<String> cmbDoctor = new JComboBox<>();
    private JComboBox<String> cmbAppointment = new JComboBox<>();

    // Medical Fields
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

    //  Constructor
    public PrescriptionDialog(Frame owner, Prescription p, List<Patient> patients, List<Clinician> clinicians, List<Appointment> appointments) {
        super(owner, (p == null) ? "Issue Prescription" : "Edit Prescription", true);
        setSize(450, 650);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(13, 2, 5, 5));

        //  Load Dropdowns
        for (Patient pat : patients) {
            cmbPatient.addItem(pat.getPatientID() + " - " + pat.getFirstName() + " " + pat.getLastName());
        }
        for (Clinician doc : clinicians) {
            cmbDoctor.addItem(doc.getClinicianID() + " - Dr. " + doc.getLastName());
        }

        // Load Appointments
        cmbAppointment.addItem("N/A - No Appointment");
        for (Appointment app : appointments) {
            cmbAppointment.addItem(app.getAppointmentID() + " - " + app.getDate());
        }

        // 2. Add Fields
        add(new JLabel("Patient:"));        add(cmbPatient);
        add(new JLabel("Doctor:"));         add(cmbDoctor);
        add(new JLabel("Appointment:"));    add(cmbAppointment);
        add(new JLabel("Date (YYYY-MM-DD):")); add(txtDate);
        add(new JLabel("Medication:"));     add(txtMedication);
        add(new JLabel("Dosage:"));         add(txtDosage);
        add(new JLabel("Frequency:"));      add(txtFrequency);
        add(new JLabel("Duration (Days):"));add(txtDuration);
        add(new JLabel("Quantity:"));       add(txtQuantity);
        add(new JLabel("Instructions:"));   add(txtInstructions);
        add(new JLabel("Pharmacy:"));       add(txtPharmacy);
        add(new JLabel("Status:"));         add(cmbStatus);

        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        add(btnCancel); add(btnSave);

        //  Pre-fill if Editing
        if (p != null) {
            // Helper to select the right item in dropdowns
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

    // Helper method to select dropdown item that starts with ID
    private void setSelectedStart(JComboBox<String> box, String id) {
        for (int i = 0; i < box.getItemCount(); i++) {
            if (box.getItemAt(i).startsWith(id)) {
                box.setSelectedIndex(i);
                return;
            }
        }
    }

    public boolean isSubmitted() { return submitted; }

    // Getters
    public String getSelectedPatientID() { return cmbPatient.getSelectedItem().toString().split(" - ")[0]; }
    public String getSelectedDoctorID() { return cmbDoctor.getSelectedItem().toString().split(" - ")[0]; }

    // Get Selected Appt ID
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