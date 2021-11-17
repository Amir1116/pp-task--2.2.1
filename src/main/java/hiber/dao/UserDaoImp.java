package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp
        implements UserDao {


    private SessionFactory sessionFactory;

    public UserDaoImp() {
    }

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCarModelSeries(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car c where c.model = :model and c.series =:series")
                .setParameter("model", model)
                .setParameter("series", series);
        Car car1 = (Car) query.getResultList().get(0);
        return car1.getUser();
    }
}
