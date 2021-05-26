<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://dohliam.github.io/dropin-minimal-css/min/bamboo.min.css">
</head>
<body>
<header>
    <h1 style="text-align: center">Hello, World!</h1>
</header>
<main>
    <section style="alignment: center; text-align: center; padding: 50px;">
        <a href="<c:url value="/list-products"/>"><button><h1>Manage products</h1></button></a>
    </section>
</main>
</body>
</html>