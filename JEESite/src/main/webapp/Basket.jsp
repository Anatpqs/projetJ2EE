<%@ page import="Entity.Basket" %>
<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div id="basket_div">
    <h3>Basket</h3>

    <%-- Vérifiez si l'utilisateur est connecté --%>
    <% User user = (User) session.getAttribute("user");
        if (user != null) {
            try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {

                String hql = "FROM Basket WHERE idUser = :userId";
                Query<Basket> query = hibernateSession.createQuery(hql, Basket.class);
                query.setParameter("userId", user.getIdUser());

                List<Basket> basketItems = query.getResultList();


                if (!basketItems.isEmpty()) { %>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID Product</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Basket item : basketItems) { %>
        <tr>
            <td><%= item.getIdProduct() %></td>
            <td><%= item.getQuantity() %></td>

        </tr>
        <% } %>
        </tbody>
    </table>

    <form method="post" action="paymentServlet">
        <button type="submit" name="action" value="Payer" class="btn btn-success">Payer</button>
    </form>
    <% } else { %>
    <p>Le panier est vide.</p>
    <% }
    } catch (Exception e) {
        e.printStackTrace();
    }
    } else { %>
    <p>Connectez-vous pour voir votre panier.</p>
    <% } %>
</div>

</body>
</html>

