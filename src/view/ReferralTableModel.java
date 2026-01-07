package view;

import model.Clinician;
import model.Facility;
import model.Patient;
import model.Referral;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferralTableModel extends AbstractTableModel {

    // Columns to display
    private final String[] columnNames = {
            "ID", "Patient", "From Dr.", "To Dr.", "Date",
            "Urgency", "Reason", "Status", "Facility (To)", "Appt ID"
    };

    private final List<Referral> list;

    // Lookup Maps
    private final Map<String, String> patientMap = new HashMap<>();
    private final Map<String, String> clinicianMap = new HashMap<>();
    private final Map<String, String> facilityMap = new HashMap<>();

    // Constructor: lookups
    public ReferralTableModel(List<Referral> list,
                              List<Patient> patients,
                              List<Clinician> clinicians,
                              List<Facility> facilities) {
        this.list = list;

        // 1 Patient Lookup
        for (Patient p : patients) {
            patientMap.put(p.getPatientID(), p.getFirstName() + " " + p.getLastName());
        }

        // 2 Doctor Lookup
        for (Clinician c : clinicians) {
            clinicianMap.put(c.getClinicianID(), "Dr. " + c.getLastName());
        }

        // 3 Facility Lookup
        for (Facility f : facilities) {
            facilityMap.put(f.getFacilityID(), f.getName());
        }
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

            // Lookups for names
            case 1: return patientMap.getOrDefault(r.getPatientID(), r.getPatientID());
            case 2: return clinicianMap.getOrDefault(r.getReferringDoctorID(), r.getReferringDoctorID());
            case 3: return clinicianMap.getOrDefault(r.getReferredToDoctorID(), r.getReferredToDoctorID());

            case 4: return r.getDate();
            case 5: return r.getUrgency();
            case 6: return r.getReason();
            case 7: return r.getStatus();

            case 8: return facilityMap.getOrDefault(r.getReferredToFacilityID(), r.getReferredToFacilityID());
            case 9: return r.getAppointmentID();
            default: return null;
        }
    }
}