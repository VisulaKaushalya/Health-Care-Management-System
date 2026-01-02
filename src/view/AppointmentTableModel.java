package view;

import model.Appointment;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AppointmentTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "Appt ID",
            "Patient ID",
            "Doctor ID",
            "Facility",
            "Date",
            "Time",
            "Duration",
            "Type",
            "Status",
            "Reason",
            "Notes",
            "Created",
            "Modified"
    };

    private final List<Appointment> appointmentList;

    public AppointmentTableModel(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int getRowCount() { return appointmentList.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int column) { return columnNames[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Appointment a = appointmentList.get(rowIndex);

        switch (columnIndex) {
            case 0: return a.getAppointmentID();
            case 1: return a.getPatientID();
            case 2: return a.getClinicianID();
            case 3: return a.getFacilityID();
            case 4: return a.getDate();
            case 5: return a.getTime();
            case 6: return a.getDuration();
            case 7: return a.getType();
            case 8: return a.getStatus();
            case 9: return a.getReason();
            case 10: return a.getNotes();
            case 11: return a.getCreatedDate();
            case 12: return a.getLastModified();


            default: return null;
        }
    }
}