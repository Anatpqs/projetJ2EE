package Controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Entity.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Entity.*;


@WebServlet("/updateQuantityServlet")
public class updateQuantityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int newQuantity;

        if ("increment".equals(action)) {
            newQuantity = getCurrentQuantity(request) + 1;
            System.out.println("eroeorj");
        } else if ("decrement".equals(action)) {
            newQuantity = getCurrentQuantity(request) - 1;
        } else {

            return;
        }


        updateQuantityInDatabase(productId, newQuantity);


        response.sendRedirect(request.getContextPath() + "/Basket.jsp");
    }

    private int getCurrentQuantity(HttpServletRequest request) {
        String quantityParam = request.getParameter("quantity");
        return Integer.parseInt(quantityParam);
    }

    private void updateQuantityInDatabase(int productId, int newQuantity) {
        try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
            List<Basket> basketItems = hibernateSession.createQuery("FROM Basket WHERE idProduct = :productId", Basket.class)
                    .setParameter("productId", productId)
                    .list();

            if (!basketItems.isEmpty()) {
                Basket basketItem = basketItems.get(0);

                if (newQuantity > 0) {
                    basketItem.setQuantity(newQuantity);
                } else {

                    hibernateSession.beginTransaction();
                    hibernateSession.delete(basketItem);
                    hibernateSession.getTransaction().commit();
                }
            }
        }
    }

}
