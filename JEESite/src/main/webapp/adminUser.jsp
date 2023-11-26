<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 17/11/2023
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #user_div{
            display: flex;
            flex-direction: column;
            margin: 50px;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<div id="user_div">
    <h3>Users</h3>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Loyalty Point</th>
        </tr>
        </thead>
        <tbody>

        <% List<User> list_users = (List<User>)request.getAttribute("list_users");
            for (User user : list_users) { %>
        <tr>
            <td><%= user.getIdUser() %></td>
            <td><%= user.getUsername() %></td>
            <td>
                <form method="post" action="AdminUserServlet">
                    <input type="hidden" name="userId" value="<%= user.getIdUser() %>">
                    <%= user.getLoyaltyPoint()%>
                    <button type="submit" name="action" value="increment">+</button>
                    <button type="submit" name="action" value="decrement">-</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
