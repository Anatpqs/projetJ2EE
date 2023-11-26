<%@ page import="Entity.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 17/11/2023
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        #categories_div {
            margin: 50px;
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
    </style>

</head>
<body>
<%@ include file="header.jsp" %>

<div id="categories_div">
    <h3>Categories</h3>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <% List<Category> categories = (List<Category>)request.getAttribute("categories");
            for (Category category : categories) { %>
        <tr>
            <td><%= category.getIdcategory() %></td>
            <td><%= category.getName() %></td>
            <td>
                <!-- Formulaire de suppression pour chaque catégorie -->
                <form method="post" action="categoryServlet" id="deleteCategory">
                    <input type="hidden" name="categoryId" value="<%= category.getIdcategory() %>">
                    <input type="submit" name="action" value="Supprimer">
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <form id="addCategory" method="post" action="categoryServlet" >
        <input type="text" placeholder="Enter the name" name="category_name">
        <button type="submit" name="action" class="btn btn-success" value="Add">Add</button>
    </form>

</div>

</body>
</html>
