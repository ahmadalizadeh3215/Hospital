package Hospital.Repository;

import Hospital.DataBase.SessionFactorySingleton;
import Hospital.Entity.Admin;
import Hospital.Entity.Physician;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PhysicianRepository implements UserInterface<Physician>{
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    @Override
    public int delete(Integer id) {
        int res = 0;
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from physician where nationalcode=:id";
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
    public Physician findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Physician.class, id);
        }
    }

    @Override
    public List<Physician> findAll() {
        List<Physician> physicianList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "from Hospital.Entity.Physician";
            var query = session.createQuery(hql, Physician.class);
            query.getResultStream().forEach(physicianList::add);
            return physicianList;
        }
    }

    @Override
    public void save(Physician physician) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(physician);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public void update(Physician physician) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(physician);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Physician login(Integer nationalCode, String password) {
       Physician physician = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM physician WHERE nationalcode = :nationalcode and password = :password";
            var query = session.createNativeQuery(sql, Physician.class);
            query.setParameter("nationalcode", nationalCode);
            query.setParameter("password", password);
            physician = query.getSingleResult();
            return physician;
        }
    }
    }
