package view;

import model.Patient;
import javax.swing.*;
import java.awt.*;

public class PatientDialog extends JDialog {
    // Fields for ALL data
    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();
    private JTextField txtDob = new JTextField();
    private JTextField txtNhsNumber = new JTextField();
    private JTextField txtGender = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtAddress = new JTextField();
    private JTextField txtPostcode = new JTextField();
    private JTextField txtEmgName = new JTextField();
    private JTextField txtEmgPhone = new JTextField();
    private JTextField txtSurgeryID = new JTextField();

    private boolean submitted = false;

    public PatientDialog(Frame owner, Patient p) {
        super(owner, (p == null) ? "Add Patient" : "Edit Patient", true);
        setSize(400, 600); // Taller window for more fields
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(13, 2, 5, 5)); // 13 Rows

        // Add Labels and Fields
        add(new JLabel("First Name:"));         add(txtFirstName);
        add(new JLabel("Last Name:"));          add(txtLastName);
        add(new JLabel("DOB (YYYY-MM-DD):"));   add(txtDob);
        add(new JLabel("NHS Number:"));         add(txtNhsNumber);
        add(new JLabel("Gender (M/F):"));       add(txtGender);
        add(new JLabel("Phone:"));              add(txtPhone);
        add(new JLabel("Email:"));              add(txtEmail);
        add(new JLabel("Address:"));            add(txtAddress);
        add(new JLabel("Postcode:"));           add(txtPostcode);
        add(new JLabel("Emergency Contact:"));  add(txtEmgName);
        add(new JLabel("Emergency Phone:"));    add(txtEmgPhone);
        add(new JLabel("Surgery ID:"));         add(txtSurgeryID);

        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        add(btnCancel); add(btnSave);

        // Pre-fill if editing
        if (p != null) {
            txtFirstName.setText(p.getFirstName());
            txtLastName.setText(p.getLastName());
            txtDob.setText(p.getDateOfBirth());
            txtNhsNumber.setText(p.getNhsNumber());
            txtGender.setText(p.getGender());
            txtPhone.setText(p.getPhoneNumber());
            txtEmail.setText(p.getEmail());
            txtAddress.setText(p.getAddress());
            txtPostcode.setText(p.getPostcode());
            txtEmgName.setText(p.getEmergencyName());
            txtEmgPhone.setText(p.getEmergencyPhone());
            txtSurgeryID.setText(p.getGpSurgeryID());
        }
    }

    // Getters for ALL fields
    public boolean isSubmitted() { return submitted; }
    public String getFirstName() { return txtFirstName.getText(); }
    public String getLastName() { return txtLastName.getText(); }
    public String getDob() { return txtDob.getText(); }
    public String getNhsNumber() { return txtNhsNumber.getText(); }
    public String getGender() { return txtGender.getText(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getAddress() { return txtAddress.getText(); }
    public String getPostcode() { return txtPostcode.getText(); }
    public String getEmgName() { return txtEmgName.getText(); }
    public String getEmgPhone() { return txtEmgPhone.getText(); }
    public String getSurgeryID() { return txtSurgeryID.getText(); }
}