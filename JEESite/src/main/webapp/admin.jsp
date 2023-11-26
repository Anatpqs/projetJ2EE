<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 18/11/2023
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>.
<head>
    <title>Admin</title>
    <style>
        #main{display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        margin: 50px;
        }
        .bout{
            display: flex;
            flex-direction: row;
            gap: 30px;
            border: 2px solid grey;
            padding: 30px;
            align-items: center;
            border-radius: 20px;
        }
        #title{
            display: flex;
            flex-direction: row;
            justify-content: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<div id="title"><h1>Dashboard</h1></div>
<div id="main">
    <form method="get" action="AdminProductServlet">
        <div class="bout">
            <h5>Manage products </h5>
            <button type="submit" class="btn btn-success">--></button>
        </div>
    </form>

    <form method="get" action="AdminUserServlet">
        <div class="bout">
            <h5>Manage users</h5>
            <button type="submit" class="btn btn-success">--></button>
        </div>
    </form>

    <form method="get" action="categoryServlet">
        <div class="bout">
            <h5>Manage categories</h5>
            <button type="submit" class="btn btn-success">--></button>
        </div>
    </form>
</div>
</body>
</html>
