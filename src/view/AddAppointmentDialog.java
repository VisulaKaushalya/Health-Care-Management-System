package view;

import model.Appointment;
import model.Clinician;
import model.Facility;
import model.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AddAppointmentDialog extends JDialog {

    // UI Components
    private JComboBox<String> cmbPatient;
    private JComboBox<String> cmbDoctor;
    private JComboBox<String> cmbFacility;
    private JTextField txtDate;
    private JTextField txtTime;
    private JTextField txtDuration;
    private JComboBox<String> cmbType;
    private JComboBox<String> cmbStatus;
    private JTextField txtReason;
    private JTextField txtNotes;

    private boolean submitted = false;

    // Constructor accepts List<Facility>
    public AddAppointmentDialog(Frame owner, Appointment appt, List<Patient> patients, List<Clinician> clinicians, List<Facility> facilities) {
        super(owner, (appt == null) ? "Book New Appointment" : "Edit Appointment", true);

        // 1 Layout Config
        setSize(500, 650);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // 2 Form Panel
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize Components
        cmbPatient = new JComboBox<>();
        cmbDoctor = new JComboBox<>();
        cmbFacility = new JComboBox<>();

        txtDate = new JTextField(java.time.LocalDate.now().toString());
        txtTime = new JTextField("09:00");
        txtDuration = new JTextField("15");


        cmbType = new JComboBox<>(new String[]{
                "Routine Consultation",
                "Vaccination",
                "Follow-up",
                "Urgent Consultation",
                "Specialist Consultation",
                "Annual health check",
                "Flu vaccination",
                "Blood pressure check"
        });
        cmbType.setEditable(true);

        cmbStatus = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled", "No Show"});
        txtReason = new JTextField("Checkup");
        txtNotes = new JTextField("None");

        // Load Dropdowns
        for (Patient p : patients) {
            cmbPatient.addItem(p.getPatientID() + " - " + p.getFirstName() + " " + p.getLastName());
        }
        for (Clinician c : clinicians) {
            cmbDoctor.addItem(c.getClinicianID() + " - Dr. " + c.getLastName());
        }
        for (Facility f : facilities) {
            cmbFacility.addItem(f.getFacilityID() + " - " + f.getName());
        }

        // Add to Panel
        formPanel.add(new JLabel("Select Patient:"));   formPanel.add(cmbPatient);
        formPanel.add(new JLabel("Select Doctor:"));    formPanel.add(cmbDoctor);
        formPanel.add(new JLabel("Select Facility:"));  formPanel.add(cmbFacility);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));formPanel.add(txtDate);
        formPanel.add(new JLabel("Time (HH:MM):"));     formPanel.add(txtTime);
        formPanel.add(new JLabel("Duration (mins):"));  formPanel.add(txtDuration);
        formPanel.add(new JLabel("Type:"));             formPanel.add(cmbType);
        formPanel.add(new JLabel("Status:"));           formPanel.add(cmbStatus);
        formPanel.add(new JLabel("Reason:"));           formPanel.add(txtReason);
        formPanel.add(new JLabel("Notes:"));            formPanel.add(txtNotes);

        add(formPanel, BorderLayout.CENTER);

        // 3 Button Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        btnPanel.add(btnCancel); btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.SOUTH);

        // 4 Pre-fill if Editing
        if (appt != null) {
            setSelectedStart(cmbPatient, appt.getPatientID());
            setSelectedStart(cmbDoctor, appt.getClinicianID());
            setSelectedStart(cmbFacility, appt.getFacilityID());

            txtDate.setText(appt.getDate());
            txtTime.setText(appt.getTime());
            txtDuration.setText(appt.getDuration());
            cmbType.setSelectedItem(appt.getType());
            cmbStatus.setSelectedItem(appt.getStatus());
            txtReason.setText(appt.getReason());
            txtNotes.setText(appt.getNotes());
        }
    }

    // Helper to select wiht ID
    private void setSelectedStart(JComboBox<String> box, String id) {
        if (id == null) return;
        for (int i = 0; i < box.getItemCount(); i++) {
            if (box.getItemAt(i).startsWith(id)) {
                box.setSelectedIndex(i);
                return;
            }
        }
    }

    // --------- Getters --------
    public boolean isSubmitted() { return submitted; }

    public String getSelectedPatient() { return (String) cmbPatient.getSelectedItem(); }
    public String getSelectedDoctor() { return (String) cmbDoctor.getSelectedItem(); }

    // Returns just the ID
    public String getFacilityID() {
        return cmbFacility.getSelectedItem().toString().split(" - ")[0];
    }

    public String getDate() { return txtDate.getText(); }
    public String getTime() { return txtTime.getText(); }
    public String getDuration() { return txtDuration.getText(); }

    public String getAppointmentType() { return cmbType.getSelectedItem().toString(); }

    public String getStatus() { return cmbStatus.getSelectedItem().toString(); }
    public String getReason() { return txtReason.getText(); }
    public String getNotes() { return txtNotes.getText(); }
}