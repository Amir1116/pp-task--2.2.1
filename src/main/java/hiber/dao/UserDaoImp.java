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
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelSeries(String model, int series) {
      Session session  = sessionFactory.openSession();
      Query query = session.createQuery("from Car c where c.model = :model and c.series =:series")
              .setParameter("model", model)
              .setParameter("series", series);
      Car car1 = (Car)query.getResultList().get(0);
      User userout = car1.getUser();
      session.close();
      return userout;
   }
}
