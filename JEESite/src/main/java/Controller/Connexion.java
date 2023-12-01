package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Entity.HibernateUtil;
import Entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM User WHERE username = :username AND isValidate = 1";
        User user = session.createQuery(hql, User.class)
                .setParameter("username", username)
                .uniqueResult();

        transaction.commit();

        boolean isValidUser = (user != null && user.getPassword().equals(password));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (isValidUser) {

            Byte isAdmin = user.getAdmin();
            HttpSession sessionUser = request.getSession();
            sessionUser.setAttribute("isAdmin", isAdmin);
            sessionUser.setAttribute("connected","ok");

            response.sendRedirect("HomeController");
        } else {
            request.setAttribute("msg_error", "Connexion échouée");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Connexion.jsp");
            dispatcher.forward(request, response);
        }

    }


}
