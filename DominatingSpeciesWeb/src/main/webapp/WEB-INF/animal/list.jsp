<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<my:basetemplate title="Categories">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>species</th>
                    <th>detail</th>
                        <sec:authorize access="hasRole('ADMIN')">
                        <th>delete</th>
                        </sec:authorize>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${animals}" var="animal">
                    <tr>
                        <td>${animal.id}</td>
                        <td>${animal.name}</td>
                        <td>${animal.species}</td>
                        <td>
                            <a href="<c:out value="/animal/detail/${animal.id}"/>" class="btn btn-primary">View</a>
                        </td>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td>
                                <a href="<c:out value="/animal/delete/${animal.id}"/>" class="btn btn-primary">Delete</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:basetemplate>

