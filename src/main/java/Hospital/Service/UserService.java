package Hospital.Service;

import Hospital.Repository.UserInterface;

public class UserService<E, R extends UserInterface<E>> extends BaseService<E, R> {
private  R r;
    public UserService(R r) {
        super(r);
        this.r = r;
    }
    public E login(Integer nationalCode, String password) {
        return r.login(nationalCode, password);
    }
}
