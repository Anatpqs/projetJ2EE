<%@ page import="Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Category" %>
<%@ page import="dao.CategoryDAO" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 01/12/2023
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> list1 = (List<Product>) request.getAttribute("list1");
    List<Product> list2 = (List<Product>) request.getAttribute("list2");
    List<Product> list3 = (List<Product>) request.getAttribute("list3");
    int n1 = list1.size();
    int n2 = list2.size();
    int n3 = list3.size();
%>
<html>
<head>
    <title>Home</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.2.0/flowbite.min.css" rel="stylesheet" />
</head>
<body>
<%@ include file="header2.jsp" %>
<form class="flex flex-col mt-5 md:flex-row gap-3 justify-center items-center" method="get" action="ProductListController">
    <div class="flex">
        <input type="search" id="name" name="name" placeholder="Search for a product..." class="w-full md:w-80 px-3 h-10 rounded-l border-2 border-gray-800 focus:outline-none focus:border-sky-500">
        <button type="submit" class="bg-gray-800 text-white rounded-r px-2 md:px-3 py-0 md:py-1">Search</button>
    </div>
    <select name="idCategory" id="idCategory">
        <option value="0"  selected="selected">All</option>
        <% List<Category> list_category = (List<Category>)request.getAttribute("list_category");
            for (Category category : list_category) {  %>
        <option value="<%=category.getIdcategory()%>"><%=category.getName()%></option>
        <% } %>
    </select>
</form>

<div class="flex mt-20">
    <div class="carousel-container mx-auto ">
        <% int idCategory1 = list1.get(0).getIdCategory(); %>
        <a href="ProductListController?idCategory=<%=idCategory1%>&name=">
            <h2 class="text-2xl font-bold mb-4 text-center"><%= new CategoryDAO().getCategoryById(idCategory1).getName()%></h2>
        </a>
        <div id="default-carousel1" class="relative w-96 h-50" data-carousel="slide">
            <!-- Carousel wrapper -->
            <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
                <% for (Product p : list1){%>
                <a href="ProductController?idProduct=<%=p.getIdProduct()%>">
                    <div class="hidden duration-700 ease-in-out" data-carousel-item>
                        <img src="images/<%= p.getIdProduct() %>.jpg" class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="...">
                    </div>
                </a>
                <%}%>
            </div>
            <!-- Slider indicators -->
            <div class="absolute z-30 flex -translate-x-1/2 bottom-5 left-1/2 space-x-3 rtl:space-x-reverse">
                <button type="button" class="w-3 h-3 rounded-full" aria-current="true"  data-carousel-slide-to="0"></button>
                <% for (int i=1;i<n1;i++){%>
                <button type="button" class="w-3 h-3 rounded-full" aria-current="false"  data-carousel-slide-to="<%= i %>"></button>
                <% } %>
            </div>
            <!-- Slider controls -->
            <button type="button" class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-prev>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
            </svg>
            <span class="sr-only">Previous</span>
        </span>
            </button>
            <button type="button" class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-next>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
            </svg>
            <span class="sr-only">Next</span>
        </span>
            </button>
        </div>
    </div>

    <div class="carousel-container mx-auto">
        <% int idCategory2 = list2.get(0).getIdCategory(); %>
        <a href="ProductListController?idCategory=<%=idCategory2%>&name=">
            <h2 class="text-2xl font-bold mb-4 text-center"><%= new CategoryDAO().getCategoryById(idCategory2).getName() %></h2>
        </a>
        <div id="default-carousel2" class="relative w-96 h-50" data-carousel="slide">
            <!-- Carousel wrapper -->
            <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
                <% for (Product p : list2){%>
                <a href="ProductController?idProduct=<%=p.getIdProduct()%>">
                    <div class="hidden duration-700 ease-in-out" data-carousel-item>
                        <img src="images/<%= p.getIdProduct() %>.jpg" class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="...">
                    </div>
                </a>
                <%}%>
            </div>
            <!-- Slider indicators -->
            <div class="absolute z-30 flex -translate-x-1/2 bottom-5 left-1/2 space-x-3 rtl:space-x-reverse">
                <button type="button" class="w-3 h-3 rounded-full" aria-current="true"  data-carousel-slide-to="0"></button>
                <% for (int i=1;i<n2;i++){%>
                <button type="button" class="w-3 h-3 rounded-full" aria-current="false"  data-carousel-slide-to="<%= i %>"></button>
                <% } %>
            </div>
            <!-- Slider controls -->
            <button type="button" class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-prev>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
            </svg>
            <span class="sr-only">Previous</span>
        </span>
            </button>
            <button type="button" class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-next>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
            </svg>
            <span class="sr-only">Next</span>
        </span>
            </button>
        </div>
    </div>

    <div class="carousel-container mx-auto">
        <% int idCategory3 = list3.get(0).getIdCategory(); %>
        <a href="ProductListController?idCategory=<%=idCategory3%>&name=">
            <h2 class="text-2xl font-bold mb-4 text-center"><%= new CategoryDAO().getCategoryById(idCategory3).getName() %></h2>
        </a>
        <div id="default-carousel3" class="relative w-96 h-50" data-carousel="slide">
            <!-- Carousel wrapper -->
            <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
                <% for (Product p : list3){%>
                <a href="ProductController?idProduct=<%=p.getIdProduct()%>">
                    <div class="hidden duration-700 ease-in-out" data-carousel-item>
                        <img src="images/<%= p.getIdProduct() %>.jpg" class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="...">
                    </div>
                </a>
                <%}%>
            </div>
            <!-- Slider indicators -->
            <div class="absolute z-30 flex -translate-x-1/2 bottom-5 left-1/2 space-x-3 rtl:space-x-reverse">
                <button type="button" class="w-3 h-3 rounded-full" aria-current="true"  data-carousel-slide-to="0"></button>
                <% for (int i=1;i<n3;i++){%>
                <button type="button" class="w-3 h-3 rounded-full" aria-current="false"  data-carousel-slide-to="<%= i %>"></button>
                <% } %>
            </div>
            <!-- Slider controls -->
            <button type="button" class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-prev>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
            </svg>
            <span class="sr-only">Previous</span>
        </span>
            </button>
            <button type="button" class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-next>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
            </svg>
            <span class="sr-only">Next</span>
        </span>
            </button>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.2.0/flowbite.min.js"></script>
<script src="https://cdn.tailwindcss.com"></script>
</body>

</html>
