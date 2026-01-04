package view;

import model.Clinician;
import javax.swing.*;
import java.awt.*;

public class ClinicianDialog extends JDialog {
    // Fields input
    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();
    private JTextField txtTitle = new JTextField();
    private JTextField txtSpeciality = new JTextField();
    private JTextField txtGmc = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtWorkplaceID = new JTextField();
    private JTextField txtWorkplaceType = new JTextField();
    private JTextField txtStatus = new JTextField();

    private boolean submitted = false;

    public ClinicianDialog(Frame owner, Clinician c) {
        super(owner, (c == null) ? "Add Doctor" : "Edit Doctor", true);
        setSize(400, 500);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(11, 2, 5, 5));

        add(new JLabel("First Name:"));     add(txtFirstName);
        add(new JLabel("Last Name:"));      add(txtLastName);
        add(new JLabel("Title (Dr/Mr):"));  add(txtTitle);
        add(new JLabel("Speciality:"));     add(txtSpeciality);
        add(new JLabel("GMC Number:"));     add(txtGmc);
        add(new JLabel("Phone:"));          add(txtPhone);
        add(new JLabel("Email:"));          add(txtEmail);
        add(new JLabel("Workplace ID:"));   add(txtWorkplaceID);
        add(new JLabel("Type (GP/Hosp):")); add(txtWorkplaceType);
        add(new JLabel("Status (Full/Part):")); add(txtStatus);

        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        add(btnCancel); add(btnSave);

        // Pre-fill if editing
        if (c != null) {
            txtFirstName.setText(c.getFirstName());
            txtLastName.setText(c.getLastName());
            txtTitle.setText(c.getTitle());
            txtSpeciality.setText(c.getSpeciality());
            txtGmc.setText(c.getGmcNumber());
            txtPhone.setText(c.getPhoneNumber());
            txtEmail.setText(c.getEmail());
            txtWorkplaceID.setText(c.getWorkplaceID());
            txtWorkplaceType.setText(c.getWorkplaceType());
            txtStatus.setText(c.getEmploymentStatus());
        }
    }

    public boolean isSubmitted() { return submitted; }

    // Getters
    public String getFirstName() { return txtFirstName.getText(); }
    public String getLastName() { return txtLastName.getText(); }
    public String getTitle() { return txtTitle.getText(); }
    public String getSpeciality() { return txtSpeciality.getText(); }
    public String getGmc() { return txtGmc.getText(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getWorkplaceID() { return txtWorkplaceID.getText(); }
    public String getWorkplaceType() { return txtWorkplaceType.getText(); }
    public String getStatus() { return txtStatus.getText(); }
}
