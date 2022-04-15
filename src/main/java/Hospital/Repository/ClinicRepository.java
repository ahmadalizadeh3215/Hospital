package Hospital.Repository;

import Hospital.DataBase.SessionFactorySingleton;
import Hospital.Entity.Admin;
import Hospital.Entity.Clinic;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ClinicRepository implements BaseRepository<Clinic>{
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    @Override
    public void save(Clinic clinic) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(clinic);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Clinic clinic) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(clinic);
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
                String sql = "delete from clinic where id=:id";
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
    public Clinic findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Clinic.class, id);
        }
    }

    @Override
    public List<Clinic> findAll() {
        List<Clinic> clinicList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM Hospital.Entity.Clinic";
            var query = session.createQuery(hql, Clinic.class);
            query.getResultStream().forEach(clinicList::add);
            return clinicList;
        }
    }
    public Boolean checkCapacity(int clinic_id){
        try(var session = sessionFactory.openSession()) {
            String sql="select * from clinic where id = ?1 and capacity>=1";
            try{
            var query=session.createNativeQuery(sql,Clinic.class);
            query.setParameter(1,clinic_id);
            Clinic result = query.getSingleResult();
            if (result==null){
                return false;
            }else
                return true;
            }catch (NoResultException e){
                return false;
            }

        }

    }
}
