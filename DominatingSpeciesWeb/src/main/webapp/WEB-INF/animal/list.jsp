<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="domspec"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<domspec:maintemplate title="Animals">
    <jsp:attribute name="content">

        <div class="container">

            <sec:authorize access="hasAuthority('ADMIN')">
                <hr/>
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/">
                    <div class="form-group">
                        <label class="control-label col-xs-2">New animal</label>
                        <div class="col-xs-4">
                            <input type="text" name="name" class="form-control" placeholder="Name" value="${newAnimal.name}"/>
                            <c:if test="${not empty name_error}"><label class="text-error">${name_error_message}</label></c:if>
                            </div>
                            <div class="col-xs-4">
                                <input type="text" name="species" class="form-control"  placeholder="Species" value="${newAnimal.species}"/>
                            <c:if test="${not empty species_error}"><label class="text-error">${species_error_message}</label></c:if>
                            </div>
                            <div class="col-xs-2">
                                <button type="submit" class="btn btn-default">Create</button>
                            </div>
                        </div>
                    </form>
            </sec:authorize>

            <hr/>
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
                                <sec:authorize access="hasAuthority('USER')">
                                    <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/animal/${animal.id}">Detail</a>
                                </sec:authorize>
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <form style="display: inline-block" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/delete">
                                        <button type="submit" class="btn btn-danger btn-xs" onclick="return confirm('Are you sure wish to to delete this animal?');">Delete</button>
                                    </form>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </jsp:attribute>
</domspec:maintemplate>>

