package view;

import model.Staff;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StaffDialog extends JDialog {

    // UI Components
    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();

    private JComboBox<String> cmbRole = new JComboBox<>(new String[]{
            "Receptionist", "Practice Manager", "Medical Secretary", "Healthcare Assistant",
            "Hospital Administrator", "Ward Clerk", "Porter", "Cleaner", "Appointments Coordinator",
            "Medical Records Clerk", "Children's Unit Coordinator"
    });

    private JTextField txtDepartment = new JTextField();
    private JTextField txtFacilityID = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtEmail = new JTextField();

    // Employment Status
    private JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Full-time", "Part-time", "Locum"});

    private JTextField txtManager = new JTextField();

    private JComboBox<String> cmbAccess = new JComboBox<>(new String[]{"Basic", "Standard", "Manager"});

    private boolean submitted = false;

    public StaffDialog(Frame owner, Staff s) {
        super(owner, (s == null) ? "Add Staff" : "Edit Staff", true);

        setSize(450, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // custom roles
        cmbRole.setEditable(true);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("First Name:"));     formPanel.add(txtFirstName);
        formPanel.add(new JLabel("Last Name:"));      formPanel.add(txtLastName);
        formPanel.add(new JLabel("Role:"));           formPanel.add(cmbRole);
        formPanel.add(new JLabel("Department:"));     formPanel.add(txtDepartment);
        formPanel.add(new JLabel("Facility ID:"));    formPanel.add(txtFacilityID);
        formPanel.add(new JLabel("Phone:"));          formPanel.add(txtPhone);
        formPanel.add(new JLabel("Email:"));          formPanel.add(txtEmail);
        formPanel.add(new JLabel("Status:"));         formPanel.add(cmbStatus);
        formPanel.add(new JLabel("Manager Name:"));   formPanel.add(txtManager);
        formPanel.add(new JLabel("Access Level:"));   formPanel.add(cmbAccess);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
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

        // Pre-fill
        if (s != null) {
            txtFirstName.setText(s.getFirstName());
            txtLastName.setText(s.getLastName());
            cmbRole.setSelectedItem(s.getRole());
            txtDepartment.setText(s.getDepartment());
            txtFacilityID.setText(s.getFacilityID());
            txtPhone.setText(s.getPhone());
            txtEmail.setText(s.getEmail());
            cmbStatus.setSelectedItem(s.getStatus());
            txtManager.setText(s.getManager());
            cmbAccess.setSelectedItem(s.getAccessLevel());
        }
    }

    // Getters
    public boolean isSubmitted() { return submitted; }

    public String getFirstName() { return txtFirstName.getText(); }
    public String getLastName() { return txtLastName.getText(); }
    public String getRole() { return cmbRole.getSelectedItem().toString(); }
    public String getDepartment() { return txtDepartment.getText(); }
    public String getFacilityID() { return txtFacilityID.getText(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getStatus() { return cmbStatus.getSelectedItem().toString(); }
    public String getManager() { return txtManager.getText(); }
    public String getAccess() { return cmbAccess.getSelectedItem().toString(); }
}