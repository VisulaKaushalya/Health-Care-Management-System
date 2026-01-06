package view;

import model.Referral;
import model.Patient;
import model.Clinician;
import model.Facility;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReferralDialog extends JDialog {
    // Dropdowns for the "Foreign Keys"
    private JComboBox<String> cmbPatient = new JComboBox<>();
    private JComboBox<String> cmbRefDoctor = new JComboBox<>(); // Referring Dr
    private JComboBox<String> cmbToDoctor = new JComboBox<>();  // Referred To Dr
    private JComboBox<String> cmbFromFacility = new JComboBox<>();
    private JComboBox<String> cmbToFacility = new JComboBox<>();

    // Text Fields for Medical Data
    private JTextField txtDate = new JTextField(java.time.LocalDate.now().toString());
    private JComboBox<String> cmbUrgency = new JComboBox<>(new String[]{"Routine", "Urgent", "Two Week Wait"});
    private JTextArea txtReason = new JTextArea(3, 20);
    private JTextArea txtSummary = new JTextArea(3, 20);
    private JTextField txtInvestigations = new JTextField();
    private JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Pending", "Accepted", "Rejected", "Completed"});
    private JTextField txtApptID = new JTextField("N/A");
    private JTextArea txtNotes = new JTextArea(3, 20);

    private boolean submitted = false;

    // Constructor: Takes lists of Patients, Doctors, and Facilities to fill dropdowns
    public ReferralDialog(Frame owner, Referral r, List<Patient> patients, List<Clinician> clinicians, List<Facility> facilities) {
        super(owner, (r == null) ? "Create Referral" : "Edit Referral", true);
        setSize(500, 750); // Tall window
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        // 1. Populate Dropdowns
        for (Patient p : patients) {
            cmbPatient.addItem(p.getPatientID() + " - " + p.getFirstName() + " " + p.getLastName());
        }

        for (Clinician c : clinicians) {
            String item = c.getClinicianID() + " - " + c.getLastName();
            cmbRefDoctor.addItem(item);
            cmbToDoctor.addItem(item);
        }

        for (Facility f : facilities) {
            String item = f.getFacilityID() + " - " + f.getName();
            cmbFromFacility.addItem(item);
            cmbToFacility.addItem(item);
        }

        // 2. Build the Form Panel
        JPanel formPanel = new JPanel(new GridLayout(14, 2, 5, 5));

        formPanel.add(new JLabel("Patient:"));              formPanel.add(cmbPatient);
        formPanel.add(new JLabel("Referring Doctor:"));     formPanel.add(cmbRefDoctor);
        formPanel.add(new JLabel("Referred To Doctor:"));   formPanel.add(cmbToDoctor);
        formPanel.add(new JLabel("From Facility:"));        formPanel.add(cmbFromFacility);
        formPanel.add(new JLabel("To Facility:"));          formPanel.add(cmbToFacility);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));    formPanel.add(txtDate);
        formPanel.add(new JLabel("Urgency:"));              formPanel.add(cmbUrgency);
        formPanel.add(new JLabel("Reason:"));               formPanel.add(new JScrollPane(txtReason));
        formPanel.add(new JLabel("Clinical Summary:"));     formPanel.add(new JScrollPane(txtSummary));
        formPanel.add(new JLabel("Investigations:"));       formPanel.add(txtInvestigations);
        formPanel.add(new JLabel("Status:"));               formPanel.add(cmbStatus);
        formPanel.add(new JLabel("Linked Appt ID:"));       formPanel.add(txtApptID);
        formPanel.add(new JLabel("Notes:"));                formPanel.add(new JScrollPane(txtNotes));

        add(formPanel, BorderLayout.CENTER);

        // 3. Add Buttons
        JPanel btnPanel = new JPanel();
        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        btnPanel.add(btnCancel); btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.SOUTH);

        // 4. Pre-fill data if Editing
        if (r != null) {
            setSelectedStart(cmbPatient, r.getPatientID());
            setSelectedStart(cmbRefDoctor, r.getReferringDoctorID());
            setSelectedStart(cmbToDoctor, r.getReferredToDoctorID());
            setSelectedStart(cmbFromFacility, r.getReferringFacilityID());
            setSelectedStart(cmbToFacility, r.getReferredToFacilityID());

            txtDate.setText(r.getDate());
            cmbUrgency.setSelectedItem(r.getUrgency());
            txtReason.setText(r.getReason());
            txtSummary.setText(r.getSummary());
            txtInvestigations.setText(r.getInvestigations());
            cmbStatus.setSelectedItem(r.getStatus());
            txtApptID.setText(r.getAppointmentID());
            txtNotes.setText(r.getNotes());
        }
    }

    // Helper to select the correct dropdown item based on ID
    private void setSelectedStart(JComboBox<String> box, String id) {
        for (int i = 0; i < box.getItemCount(); i++) {
            if (box.getItemAt(i).startsWith(id)) {
                box.setSelectedIndex(i);
                return;
            }
        }
    }

    public boolean isSubmitted() { return submitted; }

    // Getters for MainFrame to retrieve data
    public String getPatientID() { return cmbPatient.getSelectedItem().toString().split(" - ")[0]; }
    public String getRefDoctorID() { return cmbRefDoctor.getSelectedItem().toString().split(" - ")[0]; }
    public String getToDoctorID() { return cmbToDoctor.getSelectedItem().toString().split(" - ")[0]; }
    public String getFromFacilityID() { return cmbFromFacility.getSelectedItem().toString().split(" - ")[0]; }
    public String getToFacilityID() { return cmbToFacility.getSelectedItem().toString().split(" - ")[0]; }

    public String getDate() { return txtDate.getText(); }
    public String getUrgency() { return cmbUrgency.getSelectedItem().toString(); }
    public String getReason() { return txtReason.getText(); }
    public String getSummary() { return txtSummary.getText(); }
    public String getInvestigations() { return txtInvestigations.getText(); }
    public String getStatus() { return cmbStatus.getSelectedItem().toString(); }
    public String getApptID() { return txtApptID.getText(); }
    public String getNotes() { return txtNotes.getText(); }
}