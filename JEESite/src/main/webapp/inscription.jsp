<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 09/11/2023
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>

    <link rel="stylesheet" href="css/inscription.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div id="form_div">
    <h3>Enter your information</h3>
    <form method="post" action="inscription">
        <input type="text" name="Pseudo" id="Pseudo" class="form-control" placeholder="Enter your Pseudo" required><br>
        <input type="text" name="email" id="email" class="form-control" placeholder="Enter your email" required><br>
        <input type="password" name="Mdp" id="Mdp" class="form-control"  placeholder="Enter your password" required><br>
        <input type="password" name="Mdpverif" id="Mdpverif" class="form-control" placeholder="Confirm your password" required>
        <input type="submit" name="formsend" id="formsend" class="btn btn-primary" value="Sign Up">
    </form>
    <span class="msg text-success">${messageConfirm!=null ? messageConfirm : ""}</span>
    <span class="msg text-danger">${messageError!=null ? messageError : ""}</span>
</div>

</body>
</html>
