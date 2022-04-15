package Hospital.Service;

import Hospital.Entity.Admin;
import Hospital.Repository.AdminRepository;

public class AminService extends UserService<Admin, AdminRepository>{
    public AminService() {
        super(new AdminRepository());
    }
}
