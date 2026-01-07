package view;

import model.Prescription;
import model.Patient;
import model.Clinician;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PrescriptionTableModel extends AbstractTableModel {
    // Showing 15 columns
    private final String[] columnNames = {
            "ID", "Patient", "Doctor", "Appt ID", "Date",
            "Medication", "Dosage", "Frequency", "Duration", "Quantity",
            "Instructions", "Pharmacy", "Status", "Issue Date", "Collection Date"
    };

    private final List<Prescription> list;
    private final Map<String, String> patientMap = new HashMap<>();
    private final Map<String, String> clinicianMap = new HashMap<>();

    public PrescriptionTableModel(List<Prescription> list, List<Patient> patients, List<Clinician> clinicians) {
        this.list = list;
        // Lookups for Names
        for (Patient p : patients) patientMap.put(p.getPatientID(), p.getFirstName() + " " + p.getLastName());
        for (Clinician c : clinicians) clinicianMap.put(c.getClinicianID(), "Dr. " + c.getLastName());
    }

    @Override
    public int getRowCount() { return list.size(); }
    @Override
    public int getColumnCount() { return columnNames.length; }
    @Override
    public String getColumnName(int col) { return columnNames[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Prescription p = list.get(row);
        switch (col) {
            case 0: return p.getPrescriptionID();
            case 1: return patientMap.getOrDefault(p.getPatientID(), p.getPatientID());
            case 2: return clinicianMap.getOrDefault(p.getClinicianID(), p.getClinicianID());
            case 3: return p.getAppointmentID();
            case 4: return p.getDate();
            case 5: return p.getMedication();
            case 6: return p.getDosage();
            case 7: return p.getFrequency();
            case 8: return p.getDuration();
            case 9: return p.getQuantity();
            case 10: return p.getInstructions();
            case 11: return p.getPharmacy();
            case 12: return p.getStatus();
            case 13: return p.getIssueDate();
            case 14: return p.getCollectionDate();
            default: return null;
        }
    }
}