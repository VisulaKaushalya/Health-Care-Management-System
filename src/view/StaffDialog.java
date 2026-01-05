package view;

import model.Staff;
import javax.swing.*;
import java.awt.*;

public class StaffDialog extends JDialog {
    // Fields
    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();
    private JComboBox<String> cmbRole = new JComboBox<>(new String[]{"Receptionist", "Nurse", "Manager", "Admin", "Cleaner"});
    private JTextField txtDepartment = new JTextField();
    private JTextField txtFacility = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Active", "On Leave", "Resigned"});
    private JTextField txtManager = new JTextField(); // Could be a dropdown in future
    private JComboBox<String> cmbAccess = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"}); // Security Level

    private boolean submitted = false;

    public StaffDialog(Frame owner, Staff s) {
        super(owner, (s == null) ? "Add Staff Member" : "Edit Staff Member", true);
        setSize(400, 550);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(11, 2, 5, 5));

        // Add Fields
        add(new JLabel("First Name:"));     add(txtFirstName);
        add(new JLabel("Last Name:"));      add(txtLastName);
        add(new JLabel("Role:"));           add(cmbRole);
        add(new JLabel("Department:"));     add(txtDepartment);
        add(new JLabel("Facility ID:"));    add(txtFacility);
        add(new JLabel("Phone:"));          add(txtPhone);
        add(new JLabel("Email:"));          add(txtEmail);
        add(new JLabel("Status:"));         add(cmbStatus);
        add(new JLabel("Manager Name:"));   add(txtManager);
        add(new JLabel("Access Level:"));   add(cmbAccess);

        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        add(btnCancel); add(btnSave);

        // Pre-fill if Editing
        if (s != null) {
            txtFirstName.setText(s.getFirstName());
            txtLastName.setText(s.getLastName());
            cmbRole.setSelectedItem(s.getRole());
            txtDepartment.setText(s.getDepartment());
            txtFacility.setText(s.getFacilityID());
            txtPhone.setText(s.getPhone());
            txtEmail.setText(s.getEmail());
            cmbStatus.setSelectedItem(s.getStatus());
            txtManager.setText(s.getManager());
            cmbAccess.setSelectedItem(s.getAccessLevel());
        }
    }

    public boolean isSubmitted() { return submitted; }

    // Getters
    public String getFirstName() { return txtFirstName.getText(); }
    public String getLastName() { return txtLastName.getText(); }
    public String getRole() { return cmbRole.getSelectedItem().toString(); }
    public String getDepartment() { return txtDepartment.getText(); }
    public String getFacility() { return txtFacility.getText(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getStatus() { return cmbStatus.getSelectedItem().toString(); }
    public String getManager() { return txtManager.getText(); }
    public String getAccess() { return cmbAccess.getSelectedItem().toString(); }
}