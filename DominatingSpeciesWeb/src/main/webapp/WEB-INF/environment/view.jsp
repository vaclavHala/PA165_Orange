<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<dominatingspicies:maintemplate>
    <jsp:attribute name="title"><c:out value="${environment.name}" /></jsp:attribute>
    <jsp:attribute name="content">
        <div class="pull-right">
            <a href="${pageContext.request.contextPath}/environment/" class="btn btn-default">Back</a>
        </div>
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <td><c:out value="${environment.name}" /></td>
            </tr>
            <tr>
                <th>Description</th>
                <td><c:out value="${environment.description}" /></td>
            </tr>
            <tr>
                <th>Max. animal count</th>
                <td><c:out value="${environment.maxAnimalCount}" /></td>
            </tr>
        </table>
            <div class="panel panel-default">
                <div class="panel-heading">Environment's animals</div>

                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Species</th>
                                <th>Percentage</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${animals}" var="animal">
                                <tr>
                                    <td><c:out value="${animal.name}"/></td>
                                    <td><c:out value="${animal.species}"/></td>
                                    <td>TODO</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
    </jsp:attribute>
</dominatingspicies:maintemplate>