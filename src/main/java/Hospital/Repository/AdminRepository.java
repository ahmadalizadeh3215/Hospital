package Hospital.Repository;

import Hospital.DataBase.SessionFactorySingleton;
import Hospital.Entity.Admin;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements UserInterface<Admin> {


    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Admin admin) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(admin);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Admin admin) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(admin);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }


    @Override
    public int delete(Integer id) {
        int res = 0;
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from admin where nationalcode=:id";
                var query = session.createNativeQuery(sql);
                query.setParameter("id", id);
                res = query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
        return res;
    }

    @Override
    public Admin findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Admin.class, id);
        }
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "from Hospital.Entity.Admin";
            var query = session.createQuery(hql, Admin.class);
            query.getResultStream().forEach(adminList::add);
            return adminList;
        }
    }

    @Override
    public Admin login(Integer nationalCode, String password) {
        Admin admin = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM admin WHERE nationalcode = :nationalCode and password = :password";
            var query = session.createNativeQuery(sql, Admin.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("password", password);
            admin = query.getSingleResult();
            return admin;
        }
    }
}

