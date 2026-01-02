package view;

import model.Appointment;
import model.Clinician;
import model.Patient;
import model.Appointment;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "Appt ID",
            "Patient Name",
            "Doctor Name",
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

    // Lookup Maps: Key = ID, Value = Name
    private final Map<String, String> patientMap = new HashMap<>();
    private final Map<String, String> clinicianMap = new HashMap<>();

    public AppointmentTableModel(List<Appointment> appointments,
                                 List<Patient> patients,
                                 List<Clinician> clinicians) {
        this.appointmentList = appointments;

        // 1. Build the Patient Lookup Map
        for (Patient p : patients) {
            patientMap.put(p.getPatientID(), p.getFirstName() + " " + p.getLastName());
        }

        // 2. Build the Doctor Lookup Map
        for (Clinician c : clinicians) {
            clinicianMap.put(c.getClinicianID(), "Dr. " + c.getLastName());
        }
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
            //lookups
            case 0: return a.getAppointmentID();
            case 1: return patientMap.getOrDefault(a.getPatientID(), a.getPatientID());
            case 2: return clinicianMap.getOrDefault(a.getClinicianID(), a.getClinicianID());

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