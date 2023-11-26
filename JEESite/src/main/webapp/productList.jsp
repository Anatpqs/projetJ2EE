<%@ page import="Entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 24/11/2023
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> list = (List<Product>) request.getAttribute("list");
%>
<script src="https://cdn.tailwindcss.com"></script>

<html>
<head>
    <title>List</title>
</head>
<body>

<div class="bg-white">
    <div class="mx-auto max-w-2xl px-4 py-16 sm:px-6 sm:py-24 lg:max-w-7xl lg:px-8">
        <h2 class="sr-only">Products</h2>

        <div class="grid grid-cols-1 gap-x-6 gap-y-20 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8">
            <% for (Product p : list){ %>
            <a href="http://localhost:8080/JEESite_war_exploded/ProductController?idProduct=<%=p.getIdProduct()%>" class="group">
                <div class="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg bg-gray-200 xl:aspect-h-8 xl:aspect-w-7">
                    <img src="images/<%=p.getIdProduct()%>.jpg" alt="." class="h-full w-full object-cover object-center group-hover:opacity-75">
                </div>
                <h3 class="mt-4 text-sm text-gray-700"><%=p.getName()%></h3>
                <p class="mt-1 text-lg font-medium text-gray-900"><%=p.getUnitPrice()%> €</p>
            </a>
            <% } %>
        </div>
    </div>
</div>

</body>
</html>
