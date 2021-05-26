<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eXCore
  Date: 21.05.2021
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All products</title>
    <link rel="stylesheet" href="https://dohliam.github.io/dropin-minimal-css/min/bamboo.min.css">
    <style>
        table {
            counter-reset: rowNumber;
        }

        :last-child {
            margin-bottom: 10px;
        }


        button, input, select {
            margin: 5px;
        }

        table tbody tr::before {
            display: table-cell;
            counter-increment: rowNumber;
            content: counter(rowNumber) ".";
            padding-right: 0.3em;
            text-align: right;
            vertical-align: inherit;
        }
    </style>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            const labels = document.getElementsByTagName('LABEL');
            for (let i = 0; i < labels.length; i++) {
                if (labels[i].htmlFor !== '') {
                    const elem = document.getElementById(labels[i].htmlFor);
                    if (elem)
                        elem.label = labels[i];
                }
            }

            const productType = document.getElementById("product");
            const wood = document.getElementById("wood");
            const weight = document.getElementById("weight");
            const dim1 = document.getElementById("dim1");
            const dim2 = document.getElementById("dim2");
            const dim3 = document.getElementById("dim3");

            productType.addEventListener("change", function () {
                switch (productType.value) {
                    case "waste":
                        wood.disabled = true;
                        wood.hidden = true;
                        wood.label.hidden = true;
                        weight.disabled = false;
                        weight.hidden = false;
                        weight.label.hidden = false;
                        dim1.disabled = true;
                        dim1.hidden = true;
                        dim1.label.hidden = true;
                        dim2.disabled = true;
                        dim2.hidden = true;
                        dim2.label.hidden = true;
                        dim3.disabled = true;
                        dim3.hidden = true;
                        dim3.label.hidden = true;
                        break;
                    case "cylinder":
                        wood.disabled = false;
                        wood.hidden = false;
                        wood.label.hidden = false;
                        weight.disabled = true;
                        weight.hidden = true;
                        weight.label.hidden = true;
                        dim1.disabled = false;
                        dim1.hidden = false;
                        dim1.label.hidden = false;
                        dim1.label.innerHTML = "Length";
                        dim2.disabled = false;
                        dim2.hidden = false;
                        dim2.label.hidden = false;
                        dim2.label.innerHTML = "Diameter";
                        dim3.disabled = true;
                        dim3.hidden = true;
                        dim3.label.hidden = true;
                        break;
                    case "timber":
                        wood.disabled = false;
                        wood.hidden = false;
                        wood.label.hidden = false;
                        weight.disabled = true;
                        weight.hidden = true;
                        weight.label.hidden = true;
                        dim1.disabled = false;
                        dim1.hidden = false;
                        dim1.label.hidden = false;
                        dim1.label.innerHTML = "Length";
                        dim2.disabled = false;
                        dim2.hidden = false;
                        dim2.label.hidden = false;
                        dim2.label.innerHTML = "Height";
                        dim3.disabled = false;
                        dim3.hidden = false;
                        dim3.label.hidden = false;
                        dim3.label.innerHTML = "Width";
                        break;
                }
            });
        });

        function deleteProduct(url, data, caller) {
            const req = new XMLHttpRequest();
            req.open("POST", url);
            req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            req.send("id=" + data);
            const p = caller.parentNode.parentNode;
            p.parentNode.removeChild(p);
        }
    </script>
</head>
<body style="padding: 1em">
<header style="background: midnightblue; border-radius: 10px">
    <h1 style="padding: 20px; text-align: center">Product Store</h1>
</header>
<main>
    <section>
        <header>
            <h2>Add product</h2>
        </header>
        <form action="<c:url value="/add-product"/>" method="POST">
            <label for="product">Product</label>
            <select name="product" id="product">
                <option value="timber">Timber</option>
                <option value="cylinder">Cylinder</option>
                <option value="waste">Waste</option>
            </select>
            <label for="weight" hidden>Weight</label>
            <input type="number" name="weight" id="weight" step="any" min="0.02" max="0.1" disabled hidden required>
            <label for="wood">Wood</label>
            <select id="wood" name="wood" required>
                <jsp:useBean id="woods" scope="request" type="java.util.List<com.excore.java_lab_8.model.Wood>"/>
                <c:forEach var="wood" items="${woods}">
                    <option value="${wood.id}">${wood.name}</option>
                </c:forEach>
            </select>

            <label for="dim1">Length</label>
            <input type="number" name="dim1" id="dim1" step="any" min="0.02" max="3" required>

            <label for="dim2">Height</label>
            <input type="number" name="dim2" id="dim2" step="any" min="0.02" max="3" required>

            <label for="dim3">Width</label>
            <input type="number" name="dim3" id="dim3" step="any" min="0.02" max="3" required>

            <input style="alignment: center; text-align: center" type="submit">
        </form>
    </section>
    <section>
        <header>
            <h2>Products</h2>
        </header>
        <table style="width: 100%;">
            <colgroup>
                <col span="1" style="width: 10%;">
                <col span="1" style="width: 30%;">
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 10%;">
            </colgroup>
            <thead>
            <tr>
                <td>id</td>
                <td>Product</td>
                <td>Wood</td>
                <td>Weight</td>
                <td style="text-align: center">Remove</td>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="items" scope="request" type="java.util.List<com.excore.java_lab_8.model.WoodProduct>"/>
            <c:forEach var="product" items="${items}">
                <tr>
                    <td>${product.name}</td>
                    <td>
                        <c:if test="${product.name != \"Waste\"}">${product.wood}</c:if>
                    </td>
                    <td>${product.weight}</td>
                    <td>
                        <button onclick="deleteProduct('<c:url value="/delete-product"/>', '${product.id}', this)">
                            Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</main>
<footer style="background: midnightblue; border-radius: 10px">
    <p style="padding: 5px; text-align: center">All rights reserved.®</p>
</footer>
</body>
</html>
