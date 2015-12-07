<%--
    Document   : list
    Created on : Dec 7, 2015, 3:19:44 PM
    Author     : hala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class="table">
            <thead>
                <tr>
                    <th>id</th>
                    <th>placed</th>
                    <th>state</th>
                    <th>email</th>
                    <th>customer name</th>
                    <th>address</th>
                    <th>phone</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${animals}" var="animal">
                <tr>
                    <td>${animal.id}</td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
