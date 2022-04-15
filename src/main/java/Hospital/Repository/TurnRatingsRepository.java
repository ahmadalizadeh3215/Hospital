package Hospital.Repository;

import Hospital.DataBase.SessionFactorySingleton;
import Hospital.Entity.Prescription;
import Hospital.Entity.TurnRatings;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class TurnRatingsRepository implements BaseRepository<TurnRatings>{
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    @Override
    public void save(TurnRatings turnRatings) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(turnRatings);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }


    @Override
    public void update(TurnRatings turnRatings) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(turnRatings);
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
                String sql = "delete from turnRatings where id=:id";
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
    public TurnRatings findById(Integer id) {

        try (var session = sessionFactory.openSession()) {
            return session.find(TurnRatings.class, id);
        }
    }

    @Override
    public List<TurnRatings> findAll() {
        List<TurnRatings> turnRatingsList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM Hospital.Entity.TurnRatings";
            var query = session.createQuery(hql, TurnRatings.class);
            query.getResultStream().forEach(turnRatingsList::add);
            return turnRatingsList;
        }
    }
    public List<TurnRatings> findByClinicId(Integer clinic_id) {
        List<TurnRatings> turnRatingsList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql="select * from turnratings where clinic_id= ?1";
            var query=session.createNativeQuery(sql,TurnRatings.class);
            query.setParameter(1,clinic_id);
           query.getResultStream().forEach(turnRatingsList::add);
            return turnRatingsList ;
        }
    }
}
