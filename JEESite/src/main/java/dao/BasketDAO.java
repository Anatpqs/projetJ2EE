package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Entity.*;
import org.hibernate.query.Query;

import java.util.List;

public class BasketDAO {

    public void addToBasket(Basket basket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(basket);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Basket getBasketById(int basketId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Basket.class, basketId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateBasket(Basket basket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(basket);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Basket> getBasketsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Basket b WHERE b.idUser = :userId";
            Query<Basket> query = session.createQuery(hql, Basket.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            // Gérer les exceptions (log, throw, etc.) selon les besoins de votre application
            e.printStackTrace();
            return null;
        }
    }

    public void RemoveBasketsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "DELETE FROM Basket b WHERE b.idUser = :userId";
            Query<Basket> query = session.createQuery(hql, Basket.class);
            query.setParameter("userId", userId);
            query.executeUpdate();
        } catch (Exception e) {
            // Gérer les exceptions (log, throw, etc.) selon les besoins de votre application
            e.printStackTrace();
        }
    }

    public void RemoveBasketsById(int basketId){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Basket basket = session.get(Basket.class, basketId);
            if (basket != null) {
                session.delete(basket);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
