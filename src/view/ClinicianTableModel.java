package view;

import model.Clinician;
import model.Facility;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicianTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "ID",
            "First Name",
            "Last Name",
            "Title",
            "Speciality",
            "GMC No.",
            "Phone",
            "Email",
            "Workplace",
            "Type",
            "Status",
            "Start Date"
    };

    private final List<Clinician> clinicianList;
    private final Map<String, String> facilityMap = new HashMap<>();

    // CONSTRUCTOR
    public ClinicianTableModel(List<Clinician> clinicianList, List<Facility> facilities) {
        this.clinicianList = clinicianList;

        // lookup ID - Name
        if (facilities != null) {
            for (Facility f : facilities) {
                facilityMap.put(f.getFacilityID(), f.getName());
            }
        }
    }

    @Override
    public int getRowCount() { return clinicianList.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int column) { return columnNames[column]; }

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
            case 6: return c.getPhone();
            case 7: return c.getEmail();


            case 8: return facilityMap.getOrDefault(c.getWorkplaceID(), c.getWorkplaceID());

            case 9: return c.getWorkplaceType();
            case 10: return c.getStatus();
            case 11: return c.getStartDate();
            default: return null;
        }
    }
}