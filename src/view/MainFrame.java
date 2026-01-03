package view;

import model.Appointment;
import model.Clinician;
import model.Patient;
import util.CSVHandler;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // 1. Load ALL Data First (So it is available everywhere)
        CSVHandler loader = new CSVHandler();

        System.out.println("Loading data..."); // Debug print
        List<Patient> patients = loader.loadPatients("patients.csv");
        List<Clinician> clinicians = loader.loadClinicians("clinicians.csv");
        List<Appointment> appointments = loader.loadAppointments("appointments.csv");

        JTabbedPane tabbedPane = new JTabbedPane();

        // --- TAB 1: PATIENTS ---
        JPanel patientPanel = new JPanel(new BorderLayout());
        PatientTableModel patientModel = new PatientTableModel(patients);
        JTable patientTable = new JTable(patientModel);
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        patientPanel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        // --- TAB 2: DOCTORS ---
        JPanel doctorPanel = new JPanel(new BorderLayout());
        ClinicianTableModel docModel = new ClinicianTableModel(clinicians);
        JTable docTable = new JTable(docModel);
        docTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        doctorPanel.add(new JScrollPane(docTable), BorderLayout.CENTER);

        // --- TAB 3: APPOINTMENTS ---
        JPanel appointmentPanel = new JPanel(new BorderLayout());

        // Pass ALL lists to the new model
        AppointmentTableModel apptModel = new AppointmentTableModel(appointments, patients, clinicians);

        JTable apptTable = new JTable(apptModel);
        apptTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        appointmentPanel.add(new JScrollPane(apptTable), BorderLayout.CENTER);

        // NEW: Add the Button Panel at the bottom
        JPanel buttonPanel = new JPanel(); // Default FlowLayout (Centers the button)
        JButton bookButton = new JButton("Book Appointment");

        // Style tip: Make it look distinct
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(bookButton);
        appointmentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Button Action
        bookButton.addActionListener(e -> {
            AddAppointmentDialog dialog = new AddAppointmentDialog(this, patients, clinicians);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                // Extract IDs

                String rawPatient = dialog.getSelectedPatient();
                String patientID = rawPatient.split(" - ")[0];

                String rawDoctor = dialog.getSelectedDoctor();
                String doctorID = rawDoctor.split(" - ")[0];

                // Generate a new Appointment ID
                String newID = "A" + (appointments.size() + 100); // e.g., A105

                // Object
                Appointment newAppt = new Appointment(
                        newID,
                        patientID,
                        doctorID,
                        "General-Room", // Default facility
                        dialog.getDate(),
                        dialog.getTime(),
                        "30",           // Default duration
                        "Consultation", // Default type
                        "Active",       // Default status
                        dialog.getReason(),
                        "None",
                        "Today", "Today"
                );

                // Update the Table
                appointments.add(newAppt);
                apptModel.fireTableDataChanged(); // Tell table to refresh!

                // Save to File
                loader.addAppointment("appointments.csv", newAppt);

                JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!");
            }
        });



        // Add Tabs
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Doctors", doctorPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);

        add(tabbedPane);
        setVisible(true);
    }
}