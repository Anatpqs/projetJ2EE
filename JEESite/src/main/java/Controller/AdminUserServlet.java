package Controller;

import Entity.Category;
import Entity.User;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminUserServlet", value = "/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        if ("increment".equals(action) || "decrement".equals(action)) {
            // Action d'incrémentation ou de décrémentation
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = new UserDAO().getUserById(userId);

            if ("increment".equals(action)) {
                user.setLoyaltyPoint(user.getLoyaltyPoint() + 1);
            } else {
                user.setLoyaltyPoint(user.getLoyaltyPoint() - 1);
            }

            new UserDAO().updateUser(user);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<User> list_users= new ArrayList<User>();
        list_users= new UserDAO().getAllUsers();

        request.setAttribute("list_users",list_users);
        RequestDispatcher categoriesDispatcher= request.getRequestDispatcher("adminUser.jsp");
        categoriesDispatcher.forward(request,response);
    }
}
