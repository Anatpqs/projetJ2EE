<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 08/11/2023
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <%
        // Récupération de la variable de session pour l'admin
        Byte isAdmin = (Byte) session.getAttribute("isAdmin");
        String connected = (String) session.getAttribute("connected");
    %>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="HomeController">
        <img src="images/logo.PNG" style="width: 150px;height: auto;" alt="Logo" />
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="HomeController">Home </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ProductListController?name=&idCategory=0">Search </a>
            </li>
            <%if(connected==null){%>
            <li class="nav-item">
                <a class="nav-link" href="inscription.jsp">Sign up </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Connexion.jsp">Sign in </a>
            </li>
             <% }else { %>
            <li class="nav-item">
                <a class="nav-link" href="Disconnect">Log out</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Basket.jsp">Basket</a>
            </li>
            <%}%>
            <% if(isAdmin!=null && isAdmin==1){%>
            <li class="nav-item">
                <a class="nav-link" href="admin.jsp">Admin</a>
            </li>
            <% }%>

        </ul>
    </div>
</nav>

</body>
</html>
