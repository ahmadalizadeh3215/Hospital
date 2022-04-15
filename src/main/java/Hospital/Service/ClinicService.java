package Hospital.Service;

import Hospital.Entity.Clinic;
import Hospital.Repository.ClinicRepository;

public class ClinicService extends BaseService<Clinic, ClinicRepository>{
    private final ClinicRepository clinicRepository= new ClinicRepository();
    public ClinicService() {
        super(new ClinicRepository());
    }
    public Boolean checkCapacity(int clinic_id){
return clinicRepository.checkCapacity(clinic_id);
    }
}
