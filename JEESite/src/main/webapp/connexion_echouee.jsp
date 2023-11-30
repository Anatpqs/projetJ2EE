package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Entity.HibernateUtil;
import Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("name");
        String password = request.getParameter("password");


        boolean isValidUser = validateUser(username, password);


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (isValidUser) {
            response.sendRedirect("connexion_reussie.jsp");
        } else {
            response.sendRedirect("connexion_echouee.jsp");
        }
    }

    private boolean validateUser(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM User WHERE username = :username";
            User user = session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .uniqueResult();

            transaction.commit();

            return user != null && user.getPassword().equals(password);
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Hibernate Exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unexpected Exception: " + e.getMessage());
            return false;
        }
    }


}
