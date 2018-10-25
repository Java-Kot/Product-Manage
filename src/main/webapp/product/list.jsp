<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Manage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="http://guitarplus.vn/css.css">
</head>
<body>
<div class="container">
    <div class="row">
        <caption>
            <h1 label="blue">Products</h1>
        </caption>
        <ul class="list-group">
            <li class="list-group-item active">
                <p>Product name</p>
                <p>Price</p>
                <p>Description</p>
                <p>Brand</p>
                <%--<button class="btn">Edit</button>--%>
                <%--<button class="btn">Delete</button>--%>
            </li>
            <c:forEach items='${requestScope["products"]}' var="product">
                <li class="list-group-item">
                    <p><a href="/product?action=view&id=${product.getId()}">${product.getProdname()}</a></p>
                    <p>${product.getPrice()}</p>
                    <p>${product.getDescription()}</p>
                    <p>${product.getVendorx()}</p>
                    <a class="btn btn-danger" href="/product?action=delete&id=${product.getId()}">delete</a>
                    <a class="btn btn-primary" href="/product?action=edit&id=${product.getId()}">edit</a>
                </li>
            </c:forEach>
        </ul>
        <a class="btn alert-success aaa" href="/product?action=add">Create new product</a>
    </div>
</div>
</body>
</html>
