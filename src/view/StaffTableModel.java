package view;

import model.Facility;
import model.Staff;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID", "First Name", "Last Name", "Role", "Department",
            "Facility", "Phone", "Email", "Status", "Start Date",
            "Manager", "Access Lvl"
    };

    private final List<Staff> staffList;
    private final Map<String, String> facilityMap = new HashMap<>();

    // Constructor
    public StaffTableModel(List<Staff> staffList, List<Facility> facilities) {
        this.staffList = staffList;

        // lookup map (ID - Name)
        if (facilities != null) {
            for (Facility f : facilities) {
                facilityMap.put(f.getFacilityID(), f.getName());
            }
        }
    }

    @Override
    public int getRowCount() { return staffList.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Staff s = staffList.get(row);
        switch (col) {
            case 0: return s.getStaffID();
            case 1: return s.getFirstName();
            case 2: return s.getLastName();
            case 3: return s.getRole();
            case 4: return s.getDepartment();

            case 5: return facilityMap.getOrDefault(s.getFacilityID(), s.getFacilityID());

            case 6: return s.getPhone();
            case 7: return s.getEmail();
            case 8: return s.getStatus();
            case 9: return s.getStartDate();
            case 10: return s.getManager();
            case 11: return s.getAccessLevel();
            default: return null;
        }
    }
}