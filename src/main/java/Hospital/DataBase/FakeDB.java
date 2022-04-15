package Hospital.DataBase;

import Hospital.Entity.Clinic;
import Hospital.Service.*;
import Hospital.Entity.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeDB {
    private AminService aminService = new AminService();
    private ClinicService clinicService = new ClinicService();
    private PatientService patientService = new PatientService();
    private PhysicianService physicianService = new PhysicianService();
    private PrescriptionService prescriptionService = new PrescriptionService();
    private TurnRatingsService turnRatingsService = new TurnRatingsService();

    public void fillDB() {
        /////// Admin
        List<Admin> adminList = new ArrayList<>();
        Admin[] admins = {
                new Admin(1222222222, "admin1", "ladmin1", "0999999", "admi"),
                new Admin(1234509098, "admin2", "ladmin2", "0999999", "admi2"),
                new Admin(1234509099, "admin3", "ladmin3", "0999999", "admi3")
        };
        Collections.addAll(adminList, admins);
        adminList.forEach(aminService::save);
/////// Patient
        List<Patient> patientList = new ArrayList<>();
        Patient[] patients = {
                new Patient(1289876543, "ali ", "zamani", "077872"
                        , "12345", "colud"),
                new Patient(1289876544, "hassan", "zahedi", "077872"
                        , "12345", "covid"),
                new Patient(1289876545, "babak", "bagheri", "077872"
                        , "12345", "Omikron")
        };
        Collections.addAll(patientList, patients);
        patientList.forEach(patientService::save);
/////// Physician
        List<Physician> physicianList = new ArrayList<>();
        Physician[] physicians = {
                new Physician(2109887765, "yazdan ", "akbari", "09876765432"
                        , "12345", "kolye"),
                new Physician(2109887766, "mohammad ", "nazari ", "09876765443"
                        , "12345", "ghalb"),
                new Physician(2109887767, "mostafa ", "haghdost ", "09576765432"
                        , "12345", "kabed"),
                new Physician(2109887768, "akbar ", "jalali", "09576765432"
                        , "12345", "omomi"),
                new Physician(2109887769, "ehsan ", "mahmodi ", "09576765432"
                        , "12345", "ofoni"),
                new Physician(2110001010, "milad ", "moradi", "09576765432"
                        , "12345", "omomi")

        };
        Collections.addAll(physicianList, physicians);
        physicianList.forEach(physicianService::save);
////// Clinic
        List<Clinic> clinicList = new ArrayList<>();
        Clinic[] clinics = {
                new Clinic("ghalb", physicians[1], 5),
                new Clinic("kabed", physicians[2], 5),
                new Clinic("kolye", physicians[0], 5),
                new Clinic("ofoni", physicians[4], 5),
                new Clinic("omomi", physicians[3], 5)
        };
        Collections.addAll(clinicList, clinics);
        clinicList.forEach(clinicService::save);
//// Prescription
        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription[] prescriptions = {
                new Prescription("colud", patients[0], physicians[3]),
                new Prescription("covid", patients[1], physicians[3]),
                new Prescription("colud", patients[0], physicians[3]),
                new Prescription("colud", patients[0], physicians[4]),
                new Prescription("omikron", patients[2], physicians[4])
        };
        Collections.addAll(prescriptionList, prescriptions);
        prescriptionList.forEach(prescriptionService::save);
/////TurnRatings
        List<TurnRatings> turnRatingsList = new ArrayList<>();
        TurnRatings[] turnRatings = {
                new TurnRatings("2022/12/2", patients[1], clinics[4], prescriptions[0]),
                new TurnRatings("2022/12/4", patients[2], clinics[3], prescriptions[3]),
                new TurnRatings("2022/12/5", patients[0], clinics[4], prescriptions[1])
        };
        Collections.addAll(turnRatingsList, turnRatings);
        turnRatingsList.forEach(turnRatingsService::save);
    }
}
