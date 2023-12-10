package Controller;

import Entity.*;
import dao.BasketDAO;
import dao.CommandDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/paymentServlet")
public class paymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession();
        int idUser = ((User) sessionUser.getAttribute("user")).getIdUser();

        String mail_user = new UserDAO().getUserById(idUser).getMail();
        EmailTest email = new EmailTest();
        email.sendConfirmationEmail(mail_user, "Confirmation de votre commande","Votre commande a bien été validée");

        Command command = new Command();
        command.setIdUser(idUser);
        command.setDate(Date.valueOf(LocalDate.now()));
        CommandDAO commandDAO = new CommandDAO();
        commandDAO.addCommand(command);

        //To get the id of the command
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.createQuery("SELECT  LAST_INSERT_ID()").uniqueResult();
        int idCommand = id.intValue();
        transaction.commit();

        BasketDAO basketDAO=new BasketDAO();
        List<Basket> product_basket = new ArrayList<Basket>();
        product_basket = basketDAO.getBasketsByUserId(idUser);

        for(Basket basket : product_basket)
        {
            CommandLine commandLine = new CommandLine();
            commandLine.setIdCommand(idCommand);
            commandLine.setIdProduct(basket.getIdProduct());
            commandLine.setQuantity(basket.getQuantity());
            commandDAO.addCommandLine(commandLine);
        }

        basketDAO.RemoveBasketsByUserId(idUser);

        response.sendRedirect("HomeController");
    }


}
