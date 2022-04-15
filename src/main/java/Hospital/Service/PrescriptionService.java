package Hospital.Service;

import Hospital.Entity.Prescription;
import Hospital.Repository.PrescriptionRepository;

import java.util.List;

public class PrescriptionService extends BaseService<Prescription, PrescriptionRepository>{
    private final PrescriptionRepository prescriptionRepository=new PrescriptionRepository();
    public PrescriptionService() {
        super(new PrescriptionRepository());
    }
   public List<Prescription>PreviousVersions(Integer nationalcode){
        return prescriptionRepository.PreviousVersions(nationalcode);
   }
}
