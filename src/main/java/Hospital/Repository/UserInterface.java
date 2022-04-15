package Hospital.Repository;

import Hospital.Entity.Admin;

public interface UserInterface<T> extends BaseRepository<T>{

    void save(T t);

    void update(T t);


    T login(Integer nationalCode, String password);
}
