<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<dominatingspicies:maintemplate>
    <jsp:attribute name="title">Environment</jsp:attribute>
    <jsp:attribute name="content">
        <div class="pull-right">
            <a href="${pageContext.request.contextPath}/environment/new" class="btn btn-success">New environment</a>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Max. animal count</th>
                    <th>Options</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${environments}" var="environment">
                    <tr>
                        <td>${environment.id}</td>
                        <td><c:out value="${environment.name}"/></td>
                        <td><c:out value="${environment.description}"/></td>
                        <td><c:out value="${environment.maxAnimalCount}"/></td>
                        <td>
                            <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/environment/${environment.id}">View</a>
                            <a class="btn btn-warning btn-xs" href="${pageContext.request.contextPath}/environment/${environment.id}/edit">Edit</a>
                            <form style="display: inline-block" method="post" action="${pageContext.request.contextPath}/environment/${environment.id}/delete">
                                <button type="submit" class="btn btn-danger btn-xs" onclick="return confirm('Are you sure to delete this environment?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</dominatingspicies:maintemplate>