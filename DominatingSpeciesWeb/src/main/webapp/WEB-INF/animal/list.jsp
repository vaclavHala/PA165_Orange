<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="domspec"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<domspec:maintemplate title="Categories">
    <jsp:attribute name="content">
        <sec:authorize access="hasRole('ADMIN')">
            <div class="pull-right">
                <a href="${pageContext.request.contextPath}/environment/new" class="btn btn-success">New Animal</a>
            </div>
        </sec:authorize>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Species</th>
                    <th>Options</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${animals}" var="animal">
                    <tr>
                        <td>${animal.id}</td>
                        <td><c:out value="${animal.name}"/></td>
                        <td><c:out value="${animal.species}"/></td>
                        <td>
                            <sec:authorize access="hasRole('USER')">
                                <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/animal/${animal.id}">View</a>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <a class="btn btn-warning btn-xs" href="${pageContext.request.contextPath}/animal/${animal.id}/edit">Edit</a>
                                <form style="display: inline-block" method="post" action="${pageContext.request.contextPath}/animal/${animal.id}/delete">
                                    <button type="submit" class="btn btn-danger btn-xs" onclick="return confirm('Are you sure wish to to delete this animal?');">Delete</button>
                                </form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</domspec:maintemplate>>

