<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<dominatingspicies:maintemplate>
    <jsp:attribute name="content">
        <h2>Environment</h2>
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
    </jsp:attribute>
</dominatingspicies:maintemplate>