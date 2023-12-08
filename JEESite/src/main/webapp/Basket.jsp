<%@ page import="Entity.Basket" %>
<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.*" %>
<%@ page import="Entity.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <style>
        .quantity-input {
            width: 40px;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<div id="basket_div">
    <h3>Basket</h3>

    <%-- Vérifiez si l'utilisateur est connecté --%>
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {

                String hql = "SELECT b.idProduct, b.quantity, p.unitPrice, p.name, p.description " +
                        "FROM Basket b " +
                        "JOIN Product p ON b.idProduct = p.idProduct " +
                        "WHERE b.idUser = :userId";

                Query<Object[]> query = hibernateSession.createQuery(hql);
                query.setParameter("userId", user.getIdUser());

                List<Object[]> result = query.getResultList();

                if (!result.isEmpty()) {
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    BigDecimal unitPrice, quantity, lineTotal;

    %>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID Product</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Prix Unité</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Object[] row : result) {
                unitPrice = new BigDecimal(String.valueOf(row[2]));
                quantity = new BigDecimal(String.valueOf(row[1]));
                lineTotal = unitPrice.multiply(quantity);
                totalPrice = totalPrice.add(lineTotal);
        %>
        <tr>
            <td><%= row[0] %></td>
            <td><%= row[3] %></td>
            <td><%= row[4] %></td>
            <td>
                <form method="post" action="updateQuantityServlet">
                    <input type="hidden" name="productId" value="<%= row[0] %>">
                    <input class="quantity-input" type="number" name="quantity" value="<%= row[1] %>">
                    <button type="submit" name="action" value="increment">+</button>
                    <button type="submit" name="action" value="decrement">-</button>
                </form>
            </td>
            <td><%= row[2] %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <p>Total Price: <%= totalPrice %></p>

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
