package dao;

import Entity.Category;
import Entity.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDAO {

    public void addCategory(Category category) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(int categoryId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> getAllCategories() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Category", Category.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCategory(Category category) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int categoryId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                session.delete(category);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
