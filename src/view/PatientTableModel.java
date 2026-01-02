package view;

import model.Patient;
import javax.swing.table.AbstractTableModel;
import java.util.List;



public class PatientTableModel extends AbstractTableModel {
    //column headers
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
            "GP ID"
    };
    private final List<Patient> patients;
    public PatientTableModel(List<Patient> patients) {
        this.patients = patients;
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

        //map columns
        switch(columnIndex) {
            case 0: return p.getPatientID();
            case 1: return p.getFirstName();
            case 2: return p.getLastName();
            case 3: return p.getDateOfBirth();
            case 4: return p.getNhsNumber();
            case 5: return p.getGender();
            case 6: return p.getPhoneNumber();
            case 7: return p.getEmail();
            case 8: return p.getAddress();
            case 9: return p.getPostcode();
            case 10: return p.getEmergencyName();
            case 11: return p.getEmergencyPhone();
            case 12: return p.getRegistrationDate();
            case 13: return p.getGpSurgeryID();
            default: return null;
        }
    }
}
