package Hospital.Service;

import Hospital.Entity.Physician;
import Hospital.Repository.PhysicianRepository;

public class PhysicianService extends UserService<Physician, PhysicianRepository>{
    public PhysicianService() {
        super(new PhysicianRepository());
    }
}
