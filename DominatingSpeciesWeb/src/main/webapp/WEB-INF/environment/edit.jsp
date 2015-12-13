<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<dominatingspicies:maintemplate>
    <jsp:attribute name="title">Environment</jsp:attribute>
    <jsp:attribute name="content">
        <c:choose>
            <c:when test="${not empty environment.id}">
                <c:set var="action" scope="request" value="${pageContext.request.contextPath}/environment/${environment.id}"/>
            </c:when>
            <c:otherwise>
                <c:set var="action" scope="request" value="${pageContext.request.contextPath}/environment/"/>
            </c:otherwise>
        </c:choose>
        <form:form method="post" action="${action}" modelAttribute="environment" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:textarea cols="80" rows="20" path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${maxAnimalCount_error?'has-error':''}" >
            <form:label path="maxAnimalCount" cssClass="col-sm-2 control-label">Max. animal count</form:label>
            <div class="col-sm-10">
                <form:input path="maxAnimalCount" cssClass="form-control"/>
                <form:errors path="maxAnimalCount" cssClass="help-block"/>
            </div>
        </div>
        <div class="text-center">
            <button class="btn btn-primary" type="submit">Save environment</button>
            <a href="${pageContext.request.contextPath}/environment/" class="btn btn-default">Back</a>
        </div>
    </form:form>
    <br />
    <div class="panel panel-default">
        <div class="panel-heading">Environment's animals</div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Species</th>
                        <th>Percentage</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${animals}" var="animal">
                        <tr>
                            <td><c:out value="${animal.name}"/></td>
                            <td><c:out value="${animal.species}"/></td>
                            <td>TODO</td>
                            <td>
                                <a class="btn btn-warning btn-xs" href="${pageContext.request.contextPath}/animalenvironment/${animal.id}/${environment.id}/remove/environment">Remove for this environment</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animalenvironment/animalId/${environment.id}/addAnimal">
                <div class="form-group">
                    <div class="col-xs-10" >
                        <select class="form-control" name="animalId">
                            <c:forEach items="${allAnimals}" var="animal">
                                <option value="${animal.id}">${animal.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">Add Animal</button>
                </div>
            </form>                    
        </div>
    </div>
    </jsp:attribute>
</dominatingspicies:maintemplate>