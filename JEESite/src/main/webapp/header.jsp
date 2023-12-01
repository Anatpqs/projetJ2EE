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
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="HomeController">Home </a>
            </li>
            <%if(connected==null){%>
            <li class="nav-item">
                <a class="nav-link" href="inscription.jsp">Sign up </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Connexion.jsp">Sign in </a>
            </li>
            <% } %>
            <% if(isAdmin!=null && isAdmin==1){%>
            <li class="nav-item">
                <a class="nav-link" href="admin.jsp">Admin</a>
            </li>
            <% }%>

        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

</body>
</html>
