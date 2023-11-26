<%@ page import="Entity.Product" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 09/11/2023
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

<%
    List<Product> listM = (List<Product>) request.getAttribute("listM");
    List<Product> listW = (List<Product>) request.getAttribute("listW");
    List<Product> listK = (List<Product>) request.getAttribute("listK");
    int n1 = listM.size();
    int n2 = listW.size();
    int n3 = listK.size();
%>

<style>
    .custom-image {
        height: 400px; /* Ajustez la hauteur selon vos besoins */
        object-fit: cover; /* Assurez-vous que l'image couvre complètement le conteneur */
    }
    .custom-indicators li {
        background-color: black !important;
    }
    .custom-control {
        color: black !important;
        font-size: 30px !important;
    }
</style>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h2 class="text-center">Men</h2>
                <div id="myCarousel" class="carousel slide" data-ride="carousel" style="height: 530px;">
                    <!-- Indicators -->
                    <ol class="carousel-indicators custom-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <%
                            for(int i = 1;i<n1;i++){
                        %>
                        <li data-target="#myCarousel" data-slide-to=<%= i %>></li>
                        <% } %>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" >
                        <%
                            for(int i=0;i<n1;i++){
                                Product p = listM.get(i);
                        %>
                        <div class="item <%if(i==0){%>active<%}%>">
                            <div class="card w-100 ">
                                <img src="images/<%=p.getIdProduct()%>.jpg" class="card-img-top img-fluid custom-image" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title"><%=p.getName()%></h5>
                                    <p class="card-text"><%=p.getUnitPrice()%>€</p>
                                    <form action="http://localhost:8080/JEESite_war_exploded/ProductController" method="get">
                                        <input type="hidden" name="idProduct" value="<%=p.getIdProduct()%>">
                                        <input type="submit" class="btn btn-dark" value="Buy">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>

                    <!-- Left and right controls -->
                    <a class="carousel-control-prev custom-control" href="#myCarousel" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next custom-control" href="#myCarousel" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

            <div class="col-md-4">
                <h2 class="text-center">Women</h2>
                <div id="myCarousel2" class="carousel slide" data-ride="carousel" style="height: 530px;">
                    <!-- Indicators -->
                    <ol class="carousel-indicators custom-indicators">
                        <li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
                        <%
                            for(int i = 1;i<n2;i++){
                        %>
                        <li data-target="#myCarousel2" data-slide-to=<%= i %>></li>
                        <% } %>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" >
                        <%
                            for(int i=0;i<n2;i++){
                                Product p = listW.get(i);
                        %>
                        <div class="item <%if(i==0){%>active<%}%>">
                            <div class="card w-100 ">
                                <img src="images/<%=p.getIdProduct()%>.jpg" class="card-img-top img-fluid custom-image" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title"><%=p.getName()%></h5>
                                    <p class="card-text"><%=p.getUnitPrice()%>€</p>
                                    <form action="http://localhost:8080/JEESite_war_exploded/ProductController" method="get">
                                        <input type="hidden" name="idProduct" value="<%=p.getIdProduct()%>">
                                        <input type="submit" class="btn btn-dark" value="Buy">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>

                    <!-- Left and right controls -->
                    <a class="carousel-control-prev custom-control" href="#myCarousel2" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next custom-control" href="#myCarousel2" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

            <div class="col-md-4">
                <h2 class="text-center">Kids</h2>
                <div id="myCarousel3" class="carousel slide" data-ride="carousel" style="height: 530px;">
                    <!-- Indicators -->
                    <ol class="carousel-indicators custom-indicators">
                        <li data-target="#myCarousel3" data-slide-to="0" class="active"></li>
                        <%
                            for(int i = 1;i<n3;i++){
                        %>
                        <li data-target="#myCarousel3" data-slide-to=<%= i %>></li>
                        <% } %>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" >
                        <%
                            for(int i=0;i<n3;i++){
                                Product p = listK.get(i);
                        %>
                        <div class="item <%if(i==0){%>active<%}%>">
                            <div class="card w-100 ">
                                <img src="images/<%=p.getIdProduct()%>.jpg" class="card-img-top img-fluid custom-image" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title"><%=p.getName()%></h5>
                                    <p class="card-text"><%=p.getUnitPrice()%>€</p>
                                    <form action="http://localhost:8080/JEESite_war_exploded/ProductController" method="get">
                                        <input type="hidden" name="idProduct" value="<%=p.getIdProduct()%>">
                                        <input type="submit" class="btn btn-dark" value="Buy">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>

                    <!-- Left and right controls -->
                    <a class="carousel-control-prev custom-control" href="#myCarousel3" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next custom-control" href="#myCarousel3" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>