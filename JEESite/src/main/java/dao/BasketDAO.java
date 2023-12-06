package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Entity.*;
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

    public void updateBasket(Basket basket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(basket);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBasket(int IdUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Basket basket = session.get(Basket.class, IdUser);
            if (basket != null) {
                session.delete(basket);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
