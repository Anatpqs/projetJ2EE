package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Entity.*;
public class BasketDAO {

    public void addToBasket(User user, Product product, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Basket basketItem = new Basket();
            basketItem.setIdUser(user.getIdUser());
            basketItem.setIdProduct(product.getIdProduct());
            basketItem.setQuantity(quantity);

            session.save(basketItem);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void removeFromBasket(User user, Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int idUser = user.getIdUser();
        int idProduct= product.getIdProduct();


        try {
            transaction = session.beginTransaction();
            Basket basketItem = (Basket) session.createQuery(
                            "FROM Basket WHERE idUser = :idUser AND idProduct = :idProduct")
                    .setParameter("idUser", idUser)
                    .setParameter("idProduct", idProduct)
                    .uniqueResult();
            if (basketItem != null) {
                session.delete(basketItem);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
