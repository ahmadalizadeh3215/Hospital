package Hospital.Repository;

import Hospital.DataBase.SessionFactorySingleton;
import Hospital.Entity.Admin;
import Hospital.Entity.Patient;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements UserInterface<Patient>{


    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Patient patient) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(patient);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Patient patient) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(patient);
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
                String sql = "delete from patient where nationalcode=:id";
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
    public Patient findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Patient.class, id);
        }
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patientList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "select * from  patient";
            var query = session.createNativeQuery(hql, Patient.class);
            query.getResultStream().forEach(patientList::add);
            return patientList;
        }
    }

    @Override
    public Patient login(Integer nationalCode, String password) {
        Patient patient = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM patient WHERE nationalcode = :nationalcode and password = :password";
            var query = session.createNativeQuery(sql, Patient.class);
            query.setParameter("nationalcode", nationalCode);
            query.setParameter("password", password);
            patient = query.getSingleResult();
            return patient;
        }


    }
}
