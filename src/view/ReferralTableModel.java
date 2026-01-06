package view;

import model.Referral;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ReferralTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID", "Patient", "From Dr.", "To Dr.", "Date",
            "Urgency", "Reason", "Status", "Facility (To)", "Appt ID"
    };
    private final List<Referral> list;

    public ReferralTableModel(List<Referral> list) {
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
        Referral r = list.get(row);
        switch (col) {
            case 0: return r.getReferralID();
            case 1: return r.getPatientID();
            case 2: return r.getReferringDoctorID();
            case 3: return r.getReferredToDoctorID();
            case 4: return r.getDate();
            case 5: return r.getUrgency();
            case 6: return r.getReason();
            case 7: return r.getStatus();
            case 8: return r.getReferredToFacilityID();
            case 9: return r.getAppointmentID();
            default: return null;
        }
    }
}