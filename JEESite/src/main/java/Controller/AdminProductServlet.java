package Controller;

import Entity.Category;
import Entity.HibernateUtil;
import Entity.Product;
import Entity.User;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.UserDAO;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@MultipartConfig
@WebServlet(name = "AdminProductServlet", value = "/AdminProductServlet")
public class AdminProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        if("Add".equals(action))
        {


            // Gestion de/des images
            // Obtenez le champ de fichier du formulaire
            Part filePart = request.getPart("file");

            // Récupérez les données du formulaire
            String name = null;
            String description = null;
            double unit_price = 0.0;
            int stock = 0;
            int idCategory=0;

            // Obtenez les données du formulaire (y compris les champs de texte)
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if (!part.getName().equals("file")) {
                    String paramName = part.getName();
                    String paramValue = request.getParameter(paramName);

                    // Assurez-vous que les paramètres ne sont pas nuls
                    if (paramName != null && paramValue != null) {
                        switch (paramName) {
                            case "name":
                                name = paramValue;
                                break;
                            case "description":
                                description = paramValue;
                                break;
                            case "unit_price":
                                unit_price = Double.parseDouble(paramValue);
                                break;
                            case "stock":
                                stock = Integer.parseInt(paramValue);
                                break;
                            case "category":
                                idCategory = Integer.parseInt(paramValue);
                            // Ajoutez d'autres champs du formulaire au besoin
                        }
                    }
                }
            }

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setUnitPrice(unit_price);
            product.setStock(stock);
            product.setIdCategory(idCategory);
            new ProductDAO().addProduct(product);

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.createQuery("SELECT  LAST_INSERT_ID()").uniqueResult();
            int idProduct = id.intValue();
            transaction.commit();

            if (filePart != null && filePart.getSize() > 0) {
                // Traitement du fichier

                // Obtenez le nom d'origine du fichier

                // Déterminez où vous souhaitez sauvegarder le fichier
                String filePath = "C:\\Users\\CYTech Student\\IdeaProjects\\projetV2\\src\\main\\webapp\\images\\" + idProduct +".jpeg";

                // Copiez le contenu du fichier vers l'emplacement souhaité
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            request.setAttribute("messageConfirm", "The product has been added to the database + image downloaded");
            // Dispatchez vers la page JSP pour afficher les résultats
            doGet(request,response);
        } else if ("Supprimer".equals(action)) {
            int idProduct = Integer.parseInt(request.getParameter("IdProduct"));

            // Obtenez le chemin du fichier à supprimer
            String filePath = "C:\\Users\\CYTech Student\\IdeaProjects\\projetV2\\src\\main\\webapp\\images\\" + idProduct + ".jpeg";

            // Supprimez le fichier s'il existe
            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    System.out.println("File deleted successfully");
                } else {
                    System.out.println("Failed to delete the file");
                }
            } else {
                System.out.println("File does not exist");
            }

            // Supprimez le produit de la base de données
            new ProductDAO().deleteProduct(idProduct);

            // Redirigez vers la page doGet pour rafraîchir la liste des produits
            doGet(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

        List<Product> list_product= new ArrayList<Product>();
        list_product= new ProductDAO().getAllProducts();
        request.setAttribute("list_product",list_product);

        List<Category> list_category= new ArrayList<Category>();
        list_category=new CategoryDAO().getAllCategories();
        request.setAttribute("list_category",list_category);

        String messageConfirm = (String) request.getAttribute("messageConfirm");

        // Vérifiez si le message est présent
        if (messageConfirm != null) {
            request.setAttribute("messageConfirm", messageConfirm);
        }

        RequestDispatcher categoriesDispatcher= request.getRequestDispatcher("adminProduct.jsp");
        categoriesDispatcher.forward(request,response);

    }

}
