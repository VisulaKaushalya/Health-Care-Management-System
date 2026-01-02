package view;

import model.Clinician;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClinicianTableModel extends AbstractTableModel {

    // 1. ALL Headers matching your CSV order (12 columns)
    private final String[] columnNames = {
            "ID",
            "First Name",
            "Last Name",
            "Title",
            "Speciality",
            "GMC No.",
            "Phone",
            "Email",
            "Workplace ID",
            "Type",
            "Status",
            "Start Date"
    };

    private final List<Clinician> clinicianList;

    public ClinicianTableModel(List<Clinician> clinicianList) {
        this.clinicianList = clinicianList;
    }

    @Override
    public int getRowCount() {
        return clinicianList.size();
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
        Clinician c = clinicianList.get(rowIndex);

        switch (columnIndex) {
            case 0: return c.getClinicianID();
            case 1: return c.getFirstName();
            case 2: return c.getLastName();
            case 3: return c.getTitle();
            case 4: return c.getSpeciality();
            case 5: return c.getGmcNumber();
            case 6: return c.getPhoneNumber();
            case 7: return c.getEmail();
            case 8: return c.getWorkplaceID();
            case 9: return c.getWorkplaceType();
            case 10: return c.getEmploymentStatus();
            case 11: return c.getStartDate();
            default: return null;
        }
    }
}