package view;

import model.Facility;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FacilityTableModel extends AbstractTableModel {
    // showing ALL 11 columns now
    private final String[] columnNames = {
            "ID", "Name", "Type", "Address", "Postcode",
            "Phone", "Email", "Hours", "Manager", "Capacity", "Specialities"
    };
    private final List<Facility> list;

    public FacilityTableModel(List<Facility> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() { return list.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Facility f = list.get(row);
        switch (col) {
            case 0: return f.getFacilityID();
            case 1: return f.getName();
            // Note: Use whatever getter name you have in Facility.java (getType or getFacilityType)
            case 2: return f.getType();
            case 3: return f.getAddress();
            case 4: return f.getPostcode();
            case 5: return f.getPhone();
            case 6: return f.getEmail();
            case 7: return f.getOpeningHours();
            case 8: return f.getManagerName();
            case 9: return f.getCapacity();
            case 10: return f.getSpecialities();
            default: return null;
        }
    }
}