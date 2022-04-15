package Hospital.Service;

import Hospital.Entity.TurnRatings;
import Hospital.Repository.TurnRatingsRepository;

import java.util.List;

public class TurnRatingsService extends BaseService<TurnRatings, TurnRatingsRepository>{
    TurnRatingsRepository turnRatingsRepository= new TurnRatingsRepository();
    public TurnRatingsService() {
        super(new TurnRatingsRepository());
    }
    public List<TurnRatings> findByClinicId(Integer clinic_id){
        return turnRatingsRepository.findByClinicId(clinic_id);
    }

}
