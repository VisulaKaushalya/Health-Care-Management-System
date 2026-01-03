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

        //-------------------------APPOINTMENTS -----------------------------------
        JPanel appointmentPanel = new JPanel(new BorderLayout());

        //  Setup Model & Table
        AppointmentTableModel apptModel = new AppointmentTableModel(appointments, patients, clinicians);
        JTable apptTable = new JTable(apptModel);
        apptTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        appointmentPanel.add(new JScrollPane(apptTable), BorderLayout.CENTER);

        //  Button Panel
        JPanel buttonPanel = new JPanel();

        JButton bookButton = new JButton("Book Appointment");
        JButton cancelButton = new JButton("Cancel Appointment"); // <--- New Button

        // Styling
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setForeground(Color.RED);

        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton); // Add both to the panel

        appointmentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Book Appointment
        bookButton.addActionListener(e -> {
            AddAppointmentDialog dialog = new AddAppointmentDialog(this, patients, clinicians);
            dialog.setVisible(true);

            if (dialog.isSubmitted()) {
                String rawPatient = dialog.getSelectedPatient();
                String patientID = rawPatient.split(" - ")[0];
                String rawDoctor = dialog.getSelectedDoctor();
                String doctorID = rawDoctor.split(" - ")[0];
                String newID = "A" + (appointments.size() + 100);

                Appointment newAppt = new Appointment(
                        newID, patientID, doctorID, "General-Room",
                        dialog.getDate(), dialog.getTime(), "30", "Consultation",
                        "Active", dialog.getReason(), "None", "Today", "Today"
                );

                appointments.add(newAppt);
                apptModel.fireTableDataChanged();
                loader.addAppointment("appointments.csv", newAppt);

                JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!");
            }
        });

        // Cancel Appointment
        cancelButton.addActionListener(e -> {
            int selectedRow = apptTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment to cancel.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to cancel this appointment?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove from list
                    appointments.remove(selectedRow);
                    // Refresh table
                    apptModel.fireTableDataChanged();
                    // Save to file
                    loader.saveAllAppointments("appointments.csv", appointments);

                    JOptionPane.showMessageDialog(this, "Appointment Cancelled.");
                }
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