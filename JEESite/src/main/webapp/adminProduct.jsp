
<%@ page import="java.util.List" %>
<%@ page import="Entity.Product" %>
<%@ page import="Entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 13/11/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin</title>

    <style>
        #main{
            display: flex;
            flex-direction: row;
            margin-top: 30px;
            justify-content: space-evenly;

        }
        #add_article_div
        {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
            margin: 15px;
        }
        #add_div{
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 30%;
        }
        .input_add{
            display: flex;
            flex-direction: column;
            width: 100%;
        }
        .input_add input{
            width: 100%;
        }
        #product_div{
            width: 30%;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<div id="main">
    <div id="add_div">
        <h3>Add new product</h3>
        <form method="post" action="AdminProductServlet" enctype="multipart/form-data" id="addProduct">
            <div id="add_article_div">
                <div class="input_add">
                    <label for="name">Name of the article</label>
                    <input type="text" name="name" id="name">
                </div>
                <div class="input_add">
                    <label for="description">Description</label>
                    <input type="text" name="description" id="description">
                </div>
                <div class="input_add">
                    <label for="category">Category</label>
                    <select  name="category" id="category">
                        <% List<Category> list_category = (List<Category>)request.getAttribute("list_category");
                            for (Category category : list_category) {  %>
                        <option value="<%=category.getIdcategory()%>"><%=category.getName()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="input_add">
                    <label for="unit_price">Unit price</label>
                    <input type="number" name="unit_price" id="unit_price">
                </div>
                <div class="input_add">
                    <label for="stock">Stock</label>
                    <input type="number" name="stock" id="stock">
                </div>
                <input type="file" name="file" alt="image">

                <input type="submit" name="action" value="Add">
                <!-- Pouvoir mettre plusieur image -->
            </div>
        </form>

        <span class="msg text-success">${messageConfirm!=null ? messageConfirm : ""}</span>
    </div>

    <div id="product_div">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <% List<Product> list_product = (List<Product>)request.getAttribute("list_product");
                for (Product product : list_product) { %>
            <tr>
                <td><%= product.getIdProduct() %></td>
                <td><%= product.getName() %></td>
                <td>
                    <form method="post" action="AdminProductServlet" id="deleteProduct">
                        <input type="hidden" name="IdProduct" value="<%= product.getIdProduct() %>">
                        <input type="submit" name="action" value="Supprimer">
                    </form>

                </td>
            </tr>
            <% } %>
            </tbody>
        </table>

    </div>

</div>
</body>
</html>
