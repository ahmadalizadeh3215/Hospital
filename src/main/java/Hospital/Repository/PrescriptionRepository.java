package Hospital.Repository;

import Hospital.DataBase.SessionFactorySingleton;
import Hospital.Entity.Admin;
import Hospital.Entity.Prescription;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository implements BaseRepository<Prescription>{
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    @Override
    public void save(Prescription prescription) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(prescription);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public void update(Prescription prescription) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(prescription);
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
                String sql = "delete from prescription where id=:id";
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
    public Prescription findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Prescription.class, id);
        }
    }

    @Override
    public List<Prescription> findAll() {
        List<Prescription> prescriptionList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM Hospital.Entity.Prescription";
            var query = session.createQuery(hql, Prescription.class);
            query.getResultStream().forEach(prescriptionList::add);
            return prescriptionList;
        }
    }
    public List<Prescription> PreviousVersions(Integer nationalCode){
        List<Prescription> prescriptionList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String sql="select p.*,p3.firstname,p3.lastname,p3.expertise,p2.firstname,p2.lastname" +
                    ",p2.patientdossier from prescription p\n" +
                    "    inner join patient p2 on p2.nationalcode = p.patient_nationalcode\n" +
                    "inner join physician p3 on p3.nationalcode = p.physician_nationalcode\n" +
                    "where p2.nationalcode= ?1";
            var query=session.createNativeQuery(sql,Prescription.class);
            query.setParameter(1,nationalCode);
            query.getResultStream().forEach(prescriptionList::add);
            return prescriptionList;
        }
        }

    }

