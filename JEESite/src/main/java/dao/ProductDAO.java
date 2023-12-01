package dao;

import Entity.HibernateUtil;
import Entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    public void addProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int productId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, productId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getRandomProductsCategory(int n, int category) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sqlQuery = "SELECT * FROM product WHERE category = '" + category + "' ORDER BY RAND() LIMIT " + n;
            Query<Product> query = session.createNativeQuery(sqlQuery, Product.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getProductBySearch(String name, int categoryId) {
        if (categoryId == 0){
            return getProductByName(name);
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sqlQuery = "SELECT * FROM product WHERE idCategory = '" + categoryId + "' AND name LIKE '%" + name + "%'";
            Query<Product> query = session.createNativeQuery(sqlQuery, Product.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getProductByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sqlQuery = "SELECT * FROM product WHERE name LIKE '%" + name + "%'";
            Query<Product> query = session.createNativeQuery(sqlQuery, Product.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

