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

        // Add Tabs
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Doctors", doctorPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);

        add(tabbedPane);
        setVisible(true);
    }
}