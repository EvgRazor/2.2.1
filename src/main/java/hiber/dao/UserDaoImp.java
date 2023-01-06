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
   public List<User> listUsersAuto(String name, int seria) {
        Session   session = sessionFactory.openSession();
        Query  query;

        query =  session.createQuery("FROM Car C where C.model= :nameAuto or C.series = :seriaAuto");
        List <Car> listC= query.setParameter("nameAuto", name).setParameter("seriaAuto", seria).getResultList();

        query =  session.createQuery("FROM User U where U.id = :idUser");
        List <User>  userList =   query.setParameter("idUser", listC.get(0).getUser().getId()).getResultList();

     session.close();
     return userList;
   }


}
