package Hospital.Service;

import Hospital.Entity.Patient;
import Hospital.Repository.PatientRepository;

public class PatientService extends UserService<Patient, PatientRepository>{
    public PatientService() {
        super(new PatientRepository());
    }
}
