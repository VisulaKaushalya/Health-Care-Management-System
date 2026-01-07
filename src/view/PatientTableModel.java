package view;

import model.Facility;
import model.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientTableModel extends AbstractTableModel {
    // column headers
    private final String[] columnNames = {
            "ID",
            "First Name",
            "Last Name",
            "DOB",
            "NHS No.",
            "Gender",
            "Phone",
            "Email",
            "Address",
            "Postcode",
            "Emerg. Name",
            "Emerg. Phone",
            "Reg. Date",
            "GP Surgery"
    };

    private final List<Patient> patients;
    private final Map<String, String> facilityMap = new HashMap<>();

    // CONSTRUCTOR
    public PatientTableModel(List<Patient> patients, List<Facility> facilities) {
        this.patients = patients;

        // lookup map (ID - Name)
        if (facilities != null) {
            for (Facility f : facilities) {
                facilityMap.put(f.getFacilityID(), f.getName());
            }
        }
    }

    @Override
    public int getRowCount() {
        return patients.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient p = patients.get(rowIndex);

        // map columns
        switch(columnIndex) {
            case 0: return p.getPatientID();
            case 1: return p.getFirstName();
            case 2: return p.getLastName();
            case 3: return p.getDob();
            case 4: return p.getNhsNumber();
            case 5: return p.getGender();
            case 6: return p.getPhone();
            case 7: return p.getEmail();
            case 8: return p.getAddress();
            case 9: return p.getPostcode();
            case 10: return p.getEmgName();
            case 11: return p.getEmgPhone();
            case 12: return p.getRegistrationDate();
            case 13: return facilityMap.getOrDefault(p.getGpSurgeryID(), p.getGpSurgeryID());

            default: return null;
        }
    }
}