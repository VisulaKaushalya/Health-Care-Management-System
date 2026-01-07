package view;

import model.Patient;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PatientDialog extends JDialog {
    // UI Components
    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();
    private JTextField txtDob = new JTextField();
    private JTextField txtNhsNumber = new JTextField();
    private JComboBox<String> cmbGender = new JComboBox<>(new String[]{"M", "F"});
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

        // 1 Layout Configuration
        setSize(450, 650);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // 2 Form Panel

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("First Name:"));         formPanel.add(txtFirstName);
        formPanel.add(new JLabel("Last Name:"));          formPanel.add(txtLastName);
        formPanel.add(new JLabel("DOB (YYYY-MM-DD):"));   formPanel.add(txtDob);
        formPanel.add(new JLabel("NHS Number:"));         formPanel.add(txtNhsNumber);
        formPanel.add(new JLabel("Gender:"));             formPanel.add(cmbGender);
        formPanel.add(new JLabel("Phone:"));              formPanel.add(txtPhone);
        formPanel.add(new JLabel("Email:"));              formPanel.add(txtEmail);
        formPanel.add(new JLabel("Address:"));            formPanel.add(txtAddress);
        formPanel.add(new JLabel("Postcode:"));           formPanel.add(txtPostcode);
        formPanel.add(new JLabel("Emergency Contact:"));  formPanel.add(txtEmgName);
        formPanel.add(new JLabel("Emergency Phone:"));    formPanel.add(txtEmgPhone);
        formPanel.add(new JLabel("Surgery ID:"));         formPanel.add(txtSurgeryID);

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

        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.SOUTH);

        // 4 Pre-fill Data ( Editing)
        if (p != null) {
            txtFirstName.setText(p.getFirstName());
            txtLastName.setText(p.getLastName());
            txtDob.setText(p.getDob());
            txtNhsNumber.setText(p.getNhsNumber());
            cmbGender.setSelectedItem(p.getGender());
            txtPhone.setText(p.getPhone());
            txtEmail.setText(p.getEmail());
            txtAddress.setText(p.getAddress());
            txtPostcode.setText(p.getPostcode());
            txtEmgName.setText(p.getEmgName());
            txtEmgPhone.setText(p.getEmgPhone());
            txtSurgeryID.setText(p.getSurgeryID());
        }
    }

    // ------------------ Getters --------------
    public boolean isSubmitted() { return submitted; }
    public String getFirstName() { return txtFirstName.getText(); }
    public String getLastName() { return txtLastName.getText(); }
    public String getDob() { return txtDob.getText(); }
    public String getNhsNumber() { return txtNhsNumber.getText(); }
    public String getGender() { return cmbGender.getSelectedItem().toString(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getAddress() { return txtAddress.getText(); }
    public String getPostcode() { return txtPostcode.getText(); }
    public String getEmgName() { return txtEmgName.getText(); }
    public String getEmgPhone() { return txtEmgPhone.getText(); }
    public String getSurgeryID() { return txtSurgeryID.getText(); }
}