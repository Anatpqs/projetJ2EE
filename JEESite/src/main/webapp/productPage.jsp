<%@ page import="Entity.Product" %>
<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.BasketDAO" %>
<%@ page import="dao.UserDAO" %>

<%
    Product product = (Product) request.getAttribute("product");
    User currentUser = (User) request.getSession().getAttribute("user");
%>
<script src="https://cdn.tailwindcss.com"></script>
<html>
<head>
    <title><%= product.getName( ) %></title>
</head>
<body>
<%@ include file="header2.jsp" %>
<div class="bg-white">
    <div class="pt-6">

        <!-- Image gallery -->
        <div class="mx-auto mt-6 max-w-2xl sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-3 lg:gap-x-8 lg:px-8">
            <div class="aspect-h-4 aspect-w-3 hidden overflow-hidden rounded-lg lg:block">
                <img src="images/<%=product.getIdProduct()%>.jpg" alt="" class="h-300 w-full object-cover object-center">
            </div>
        </div>

        <!-- Product info -->
        <div class="mx-auto max-w-2xl px-4 pb-16 pt-10 sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-3 lg:grid-rows-[auto,auto,1fr] lg:gap-x-8 lg:px-8 lg:pb-24 lg:pt-16">
            <div class="lg:col-span-2 lg:border-r lg:border-gray-200 lg:pr-8">
                <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl"><%= product.getName( ) %></h1>
            </div>

            <!-- Options -->
            <div class="mt-4 lg:row-span-3 lg:mt-0">
                <h2 class="sr-only">Product information</h2>
                <p class="text-3xl tracking-tight text-gray-900"><%= product.getUnitPrice() %> €</p>


                <form method="post" action="BasketController">
                    <input type="hidden" name="productId" value="<%= product.getIdProduct() %>">
                    <button type="submit" class="mt-10 flex w-full items-center justify-center rounded-md border border-transparent bg-gray-700 px-8 py-3 text-base font-medium text-white hover:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2">Add to Bag</button>
                </form>
            </div>

            <div class="py-10 lg:col-span-2 lg:col-start-1 lg:border-r lg:border-gray-200 lg:pb-16 lg:pr-8 lg:pt-6">

                <div>
                    <h3 class="sr-only">Description</h3>
                    <div class="space-y-6">
                        <p class="text-base text-gray-900"><%= product.getDescription() %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
