<%@tag dynamic-attributes="dynattrs" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <title><c:out value="${title}"/></title>
    </head>
    <body>
        <div id="body">
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>