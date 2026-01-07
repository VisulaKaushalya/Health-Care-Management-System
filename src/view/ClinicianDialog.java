package view;

import model.Clinician;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClinicianDialog extends JDialog {
    // UI Components
    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();

    // Matched to clinicians.csv
    private JComboBox<String> cmbTitle = new JComboBox<>(new String[]{
            "GP", "Consultant", "Senior Nurse", "Practice Nurse", "Staff Nurse", "Specialist"
    });

    private JTextField txtSpeciality = new JTextField();
    private JTextField txtGmc = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtWorkplaceID = new JTextField();


    private JComboBox<String> cmbWorkplaceType = new JComboBox<>(new String[]{
            "GP Surgery", "Hospital", "Clinic", "Care Home"
    });


    private JComboBox<String> cmbStatus = new JComboBox<>(new String[]{
            "Full-time", "Part-time", "Locum"
    });

    private boolean submitted = false;

    public ClinicianDialog(Frame owner, Clinician c) {
        super(owner, (c == null) ? "Add Doctor" : "Edit Doctor", true);

        // 1 Layout Config
        setSize(450, 550);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // EDITING FOR DROPDOWNS
        cmbTitle.setEditable(true);
        cmbWorkplaceType.setEditable(true);
        cmbStatus.setEditable(true);

        // 2 Form Panel
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("First Name:"));     formPanel.add(txtFirstName);
        formPanel.add(new JLabel("Last Name:"));      formPanel.add(txtLastName);
        formPanel.add(new JLabel("Job Role:"));       formPanel.add(cmbTitle);
        formPanel.add(new JLabel("Speciality:"));     formPanel.add(txtSpeciality);
        formPanel.add(new JLabel("GMC Number:"));     formPanel.add(txtGmc);
        formPanel.add(new JLabel("Phone:"));          formPanel.add(txtPhone);
        formPanel.add(new JLabel("Email:"));          formPanel.add(txtEmail);
        formPanel.add(new JLabel("Workplace ID:"));   formPanel.add(txtWorkplaceID);
        formPanel.add(new JLabel("Type:"));           formPanel.add(cmbWorkplaceType);
        formPanel.add(new JLabel("Status:"));         formPanel.add(cmbStatus);

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

        // 4 Pre-fill
        if (c != null) {
            txtFirstName.setText(c.getFirstName());
            txtLastName.setText(c.getLastName());
            cmbTitle.setSelectedItem(c.getTitle());
            txtSpeciality.setText(c.getSpeciality());
            txtGmc.setText(c.getGmcNumber());
            txtPhone.setText(c.getPhone());
            txtEmail.setText(c.getEmail());
            txtWorkplaceID.setText(c.getWorkplaceID());
            cmbWorkplaceType.setSelectedItem(c.getWorkplaceType());
            cmbStatus.setSelectedItem(c.getStatus());
        }
    }

    // Getters
    public boolean isSubmitted() { return submitted; }
    public String getFirstName() { return txtFirstName.getText(); }
    public String getLastName() { return txtLastName.getText(); }
    public String getTitle() { return cmbTitle.getSelectedItem().toString(); }
    public String getSpeciality() { return txtSpeciality.getText(); }
    public String getGmc() { return txtGmc.getText(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getWorkplaceID() { return txtWorkplaceID.getText(); }
    public String getWorkplaceType() { return cmbWorkplaceType.getSelectedItem().toString(); }
    public String getStatus() { return cmbStatus.getSelectedItem().toString(); }
}