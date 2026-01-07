package view;

import model.Facility;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FacilityDialog extends JDialog {
    // UI Components
    private JTextField txtName = new JTextField();

    // Editable Dropdown
    private JComboBox<String> cmbType = new JComboBox<>(new String[]{
            "GP Surgery", "Hospital", "Clinic", "Pharmacy", "Care Home", "Dental Practice"
    });

    private JTextField txtAddress = new JTextField();
    private JTextField txtPostcode = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtHours = new JTextField();
    private JTextField txtManager = new JTextField();
    private JTextField txtCapacity = new JTextField();


    private JTextArea txtSpecialities = new JTextArea(3, 20);

    private boolean submitted = false;

    public FacilityDialog(Frame owner, Facility f) {
        super(owner, (f == null) ? "Add Facility" : "Edit Facility", true);

        // 1 Layout Config
        setSize(450, 650);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // custom types
        cmbType.setEditable(true);
        txtSpecialities.setLineWrap(true);
        txtSpecialities.setWrapStyleWord(true);

        // 2 Form Panel
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Facility Name:")); formPanel.add(txtName);
        formPanel.add(new JLabel("Type:"));          formPanel.add(cmbType);
        formPanel.add(new JLabel("Address:"));       formPanel.add(txtAddress);
        formPanel.add(new JLabel("Postcode:"));      formPanel.add(txtPostcode);
        formPanel.add(new JLabel("Phone:"));         formPanel.add(txtPhone);
        formPanel.add(new JLabel("Email:"));         formPanel.add(txtEmail);
        formPanel.add(new JLabel("Opening Hours:")); formPanel.add(txtHours);
        formPanel.add(new JLabel("Manager Name:"));  formPanel.add(txtManager);
        formPanel.add(new JLabel("Capacity:"));      formPanel.add(txtCapacity);
        formPanel.add(new JLabel("Specialities:"));  formPanel.add(new JScrollPane(txtSpecialities));

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
        if (f != null) {
            txtName.setText(f.getName());
            cmbType.setSelectedItem(f.getType());
            txtAddress.setText(f.getAddress());
            txtPostcode.setText(f.getPostcode());
            txtPhone.setText(f.getPhone());
            txtEmail.setText(f.getEmail());
            txtHours.setText(f.getOpeningHours());
            txtManager.setText(f.getManagerName());
            txtCapacity.setText(f.getCapacity());
            txtSpecialities.setText(f.getSpecialities());
        }
    }

    // --------------------- Getters ---
    public boolean isSubmitted() { return submitted; }


    public String getName() { return txtName.getText(); }
    public String getFacilityType() { return cmbType.getSelectedItem().toString(); }
    public String getAddress() { return txtAddress.getText(); }
    public String getPostcode() { return txtPostcode.getText(); }
    public String getPhone() { return txtPhone.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getHours() { return txtHours.getText(); }
    public String getManager() { return txtManager.getText(); }
    public String getCapacity() { return txtCapacity.getText(); }
    public String getSpecialities() { return txtSpecialities.getText(); }
}