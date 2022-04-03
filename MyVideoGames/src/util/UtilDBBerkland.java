/**
 */
package util;

import java.util.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.service.*;

import datamodel.VideoGame;

import org.hibernate.*;

/**
 * @since JavaSE-1.8
 */
public class UtilDBBerkland {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<VideoGame> listVideoGames() {
      List<VideoGame> resultList = new ArrayList<VideoGame>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> videoGames = session.createQuery("FROM VideoGame").list();
         for (Iterator<?> iterator = videoGames.iterator(); iterator.hasNext();) {
            VideoGame videoGame = (VideoGame) iterator.next();
            resultList.add(videoGame);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<VideoGame> listVideoGames(String keyword) {
      List<VideoGame> resultList = new ArrayList<VideoGame>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((VideoGame)session.get(VideoGame.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM VideoGame");
         List<?> videoGames = session.createQuery("FROM VideoGame").list();
         for (Iterator<?> iterator = videoGames.iterator(); iterator.hasNext();) {
            VideoGame videoGame = (VideoGame) iterator.next();
            if (videoGame.getName().startsWith(keyword)) {
               resultList.add(videoGame);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createVideoGames(String userName, String age, String phone) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new VideoGame(userName, Integer.valueOf(age), phone));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
