<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<dominatingspicies:maintemplate>
    <jsp:attribute name="title"><c:out value="${environment.name}" /></jsp:attribute>
    <jsp:attribute name="content">
        <c:set var="disabled" scope="request" value="true"/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:set var="disabled" scope="request" value="false"/>
        </sec:authorize>
        <div>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#information" aria-controls="information" role="tab" data-toggle="tab">Basic information</a></li>
                <li role="presentation"><a href="#animals" aria-controls="animals" role="tab" data-toggle="tab">Animals</a></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="information">
                    <div class="panel panel-default">
                        <div class="panel-heading">Basic information</div>
                        <div class="panel-body">
                            <form:form method="post" action="${action}" modelAttribute="environment" cssClass="form-horizontal">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group ${name_error?'has-error':''}">
                                            <form:label path="name" cssClass="col-md-4 control-label">Name</form:label>
                                                <div class="col-md-8">
                                                <form:input path="name" cssClass="form-control" disabled="${disabled}" />
                                                <form:errors path="name" cssClass="help-block"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group ${maxAnimalCount_error?'has-error':''}" >
                                            <form:label path="maxAnimalCount" cssClass="col-md-4 control-label">Max. animal count</form:label>
                                                <div class="col-md-8">
                                                <form:input path="maxAnimalCount" cssClass="form-control" disabled="${disabled}" />
                                                <form:errors path="maxAnimalCount" cssClass="help-block"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ${description_error?'has-error':''}">
                                    <form:label path="description" cssClass="col-md-2 control-label">Description</form:label>
                                        <div class="col-md-10">
                                        <form:textarea cols="80" rows="20" path="description" cssClass="form-control" disabled="${disabled}" />
                                        <form:errors path="description" cssClass="help-block"/>
                                    </div>
                                </div>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <div class="text-center">
                                        <button class="btn btn-primary" type="submit">Save environment</button>
                                    </div>
                                </sec:authorize>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="animals">
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
                </div>
            </div>

        </div>
    </jsp:attribute>
</dominatingspicies:maintemplate>