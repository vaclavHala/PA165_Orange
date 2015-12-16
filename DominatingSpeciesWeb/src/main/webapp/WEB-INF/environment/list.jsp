<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<dominatingspicies:maintemplate>
    <jsp:attribute name="title">Environments</jsp:attribute>
    <jsp:attribute name="content">
        <sec:authorize access="hasRole('ADMIN')">
            <hr/>
            <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/environment/">
                <div class="form-group">
                    <label class="control-label col-xs-2">New environment</label>
                    <div class="col-xs-4">
                        <input type="text" name="name" class="form-control" placeholder="Name" value="${newEnvironment.name}"/>
                        <c:if test="${not empty name_error}"><label class="text-error">${name_error_message}</label></c:if>
                    </div>
                    <div class="col-xs-4">
                        <input type="text" name="maxAnimalCount" class="form-control"  placeholder="Max. animal count" value="${newEnvironment.maxAnimalCount}"/>
                        <c:if test="${not empty maxAnimalCount_error}"><label class="text-error">${maxAnimalCount_error_message}</label></c:if>
                    </div>
                    <div class="col-xs-2">
                        <button type="submit" class="btn btn-default">Create</button>
                    </div>
                </div>
            </form>
        </sec:authorize>
        <hr>
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
                            <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/environment/${environment.id}">Detail</a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <form style="display: inline-block" method="post" action="${pageContext.request.contextPath}/environment/${environment.id}/delete">
                                    <button type="submit" class="btn btn-danger btn-xs" onclick="return confirm('Are you sure to delete this environment and all assigned animal environments?');">Delete</button>
                                </form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</dominatingspicies:maintemplate>