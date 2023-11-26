package Controller;

import java.io.*;

import Entity.HibernateUtil;
import Entity.User;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;

@WebServlet(name = "SignUpServlet", value = "/inscription")
public class SignUpServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Récupérez les données du formulaire
        String pseudo = request.getParameter("Pseudo");
        String mail = request.getParameter("email");
        String password = request.getParameter("Mdp");
        String passwordVerif = request.getParameter("Mdpverif");

        // Vérifiez si les mots de passe correspondent
        if (!password.equals(passwordVerif)) {

            request.setAttribute("messageError", "Passwords do not match");
            // Dispatchez vers la page JSP pour afficher les résultats
            RequestDispatcher dispatcherMDP = request.getRequestDispatcher("inscription.jsp");
            dispatcherMDP.forward(request, response);
        }

        // Obtenez la SessionFactory
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            // Créez un objet User avec les données du formulaire
            User user = new User();
            user.setUsername(pseudo);
            user.setMail(mail);
            user.setPassword(password);
            user.setLoyaltyPoint(0);
            user.setAdmin((byte) 0);
            user.setIsValidate((byte) 0);

            //Enregistrez l'utilisateur dans la base de données
            new UserDAO().addUser(user);

            int IdUser = ((Integer) session.createNativeQuery("SELECT IdUser FROM user ORDER BY IdUser DESC LIMIT 1").uniqueResult());

            EmailTest email = new EmailTest();
            email.sendConfirmationEmail(mail, "http://localhost:8080/JEESite_war_exploded/confirmation?IdUser=" + IdUser);

            String messageReturn ="Your account has been created, to validate it a confirmation email has been sent to you";

            request.setAttribute("messageConfirm", messageReturn);
            // Dispatchez vers la page JSP pour afficher les résultats
            RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Gérez les erreurs
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            response.getWriter().println("Erreur lors de l'enregistrement de l'utilisateur.");
        } finally {
            // Fermez la session (si elle est ouverte)
            if (session.isOpen()) {
                session.close();
            }
        }

    }
}