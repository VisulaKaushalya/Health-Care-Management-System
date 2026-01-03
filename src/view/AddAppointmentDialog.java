package view;

import model.Clinician;
import model.Patient;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddAppointmentDialog extends JDialog {

    private JComboBox<String> patientCombo;
    private JComboBox<String> doctorCombo;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField reasonField;
    private boolean submitted = false; // To track if user clicked Save

    public AddAppointmentDialog(Frame owner, List<Patient> patients, List<Clinician> clinicians) {
        super(owner, "Book New Appointment", true); // blocks the main window until closed
        setSize(400, 350);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 cols, padding

        // 1. Patient Selection
        add(new JLabel("  Select Patient:"));
        patientCombo = new JComboBox<>();
        for (Patient p : patients) {
            // Store ID hidden, show Name visible
            patientCombo.addItem(p.getPatientID() + " - " + p.getFirstName() + " " + p.getLastName());
        }
        add(patientCombo);

        // 2. Doctor Selection
        add(new JLabel("  Select Doctor:"));
        doctorCombo = new JComboBox<>();
        for (Clinician c : clinicians) {
            doctorCombo.addItem(c.getClinicianID() + " - Dr. " + c.getLastName());
        }
        add(doctorCombo);

        // 3. Date
        add(new JLabel("  Date (DD/MM/YYYY):"));
        dateField = new JTextField("01/01/2026"); // Placeholder
        add(dateField);

        // 4. Time
        add(new JLabel("  Time (HH:MM):"));
        timeField = new JTextField("09:00");
        add(timeField);

        // 5. Reason
        add(new JLabel("  Reason:"));
        reasonField = new JTextField("Checkup");
        add(reasonField);

        // 6. Buttons
        JButton cancelButton = new JButton("Cancel");
        JButton saveButton = new JButton("Book Appointment");

        cancelButton.addActionListener(e -> dispose()); // Close window
        saveButton.addActionListener(e -> {
            submitted = true;
            dispose(); // Close window
        });

        add(cancelButton);
        add(saveButton);
    }

    // Getters
    public boolean isSubmitted() { return submitted; }
    public String getSelectedPatient() { return (String) patientCombo.getSelectedItem(); }
    public String getSelectedDoctor() { return (String) doctorCombo.getSelectedItem(); }
    public String getDate() { return dateField.getText(); }
    public String getTime() { return timeField.getText(); }
    public String getReason() { return reasonField.getText(); }
}