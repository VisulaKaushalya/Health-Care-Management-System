package view;

import model.Patient;
import model.Clinician;
import util.CSVHandler;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    public MainFrame() {
        //window setup
        super("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);   //center

        //tab container
        JTabbedPane tabbedPane = new JTabbedPane();

        //--------------patient tab-----------------

        JPanel patientPanel = new JPanel(new BorderLayout());

        //import from patient.csv
        CSVHandler loader = new CSVHandler();
        List<Patient> patients = loader.loadPatients("patients.csv");

        //model and tables
        PatientTableModel patientTableModel = new PatientTableModel(patients);
        JTable patientTable = new JTable(patientTableModel);

        //enable horizontal scroll, scrollpane
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        patientPanel.add(scrollPane, BorderLayout.CENTER);

        //--------------------------doctor tab-------------------------------

        JPanel doctorPanel = new JPanel(new BorderLayout());

        // A. Load data using the NEW method in CSVHandler
        // (Note: 'loader' was already created for patients, so we can reuse it!)
        List<Clinician> clinicians = loader.loadClinicians("clinicians.csv");

        // B. Create Model & Table
        ClinicianTableModel docModel = new ClinicianTableModel(clinicians);
        JTable docTable = new JTable(docModel);

        // C. Enable scrolling (Essential for 12 columns)
        docTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // D. Add to ScrollPane and Panel
        JScrollPane docScroll = new JScrollPane(docTable);
        doctorPanel.add(docScroll, BorderLayout.CENTER);


        //----------------------appointment tab----------------------------------
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.add(new JLabel("Appointment List will go here"));

        //panels to tabs
        tabbedPane.addTab("Patient List", patientPanel);
        tabbedPane.addTab("Doctor List", doctorPanel);
        tabbedPane.addTab("Appointment List", appointmentPanel);

        add(tabbedPane);
        setVisible(true);



    }
}
