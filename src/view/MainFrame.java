package view;

import model.Patient;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        //window setup
        super("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);   //center

        //tab container
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel patientPanel = new JPanel();
        patientPanel.add(new JLabel("Patient List will go here"));

        JPanel doctorPanel = new JPanel();
        doctorPanel.add(new JLabel("Doctor List will go here"));

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
