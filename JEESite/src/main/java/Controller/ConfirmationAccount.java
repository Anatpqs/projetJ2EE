package Controller;

import Entity.HibernateUtil;
import Entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "ConfirmationAccount", value = "/confirmation")
public class ConfirmationAccount extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String IdUserParam = request.getParameter("IdUser");

        if (IdUserParam != null && !IdUserParam.isEmpty()) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Utilisez la méthode get pour obtenir l'objet User correspondant à l'IdUser
                User user = session.get(User.class, Integer.parseInt(IdUserParam));

                if (user != null) {
                    // Mettez à jour la propriété isValidate de l'utilisateur
                    user.setIsValidate((byte) 1);

                    response.getWriter().println("Votre compte est enregistré !");
                } else {
                    response.getWriter().println("Aucun utilisateur trouvé avec l'ID spécifié.");
                }

                // Commit de la transaction
                transaction.commit();
            } catch (NumberFormatException e) {
                // Gestion d'une exception si la conversion de la chaîne en entier échoue
                response.getWriter().println("ID d'utilisateur non valide.");
            } finally {
                // Fermeture de la session
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
    }
}

