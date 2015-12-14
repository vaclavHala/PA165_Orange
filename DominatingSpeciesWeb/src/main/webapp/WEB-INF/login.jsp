<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login - Dominating Species</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
    </head>
    <body>

        <div class="container" style="margin: 0 auto; width:50%">
            <div class="page-header">
                <div class="panel panel-default">
                    <div class="panel-heading">Login</div>
                    <div class="panel-body">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="POST">
                            <div class="form-group">

                                <label class="col-xs-4 text-right">User Name :</label>
                                <input class="col-xs-6" type="text" name="username"/>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-4 text-right">Password:</label>
                                <input class="col-xs-6" type="password" name="password"/>
                            </div>
                            <c:if test="${not empty error}">
                                <div class="form-group">
                                    <div class="col-xs-4"></div>
                                    <div class="text-danger">${error}</div>
                                </div>
                            </c:if>
                            <c:if test="${not empty msg}">
                                <div class="form-group">
                                    <div class="col-xs-4"></div>
                                    <div class="text-info">${msg}</div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <div class="col-xs-4"></div>
                                <input type="submit" value="Sign In"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
