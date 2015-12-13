<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="domspec"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<domspec:maintemplate title="Animal Detail">
    <jsp:attribute name="content">
        <div class="container">

            <hr/>

            <div class="panel panel-default">
                <div class="panel-heading">Identity</div>
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/">
                    <div class="form-group panel-body">
                        <label class="control-label col-xs-1">Name:</label>
                        <div class="col-xs-4">
                            <sec:authorize access="hasRole('ADMIN')">
                                <input type="text" name="name" class="form-control" value="${animal.name}"/>
                            </sec:authorize>
                            <sec:authorize access="not hasRole('ADMIN')">
                                <input disabled type="text" name="name" class="form-control" value="${animal.name}"/>
                            </sec:authorize>
                            <c:if test="${not empty name_error}"><label class="text-error">${name_error_message}</label></c:if>
                            </div>
                            <label class="control-label col-xs-1">Species:</label>
                            <div class="col-xs-4">
                            <sec:authorize access="hasRole('ADMIN')">
                                <input type="text" name="species" class="form-control" value="${animal.species}"/>
                            </sec:authorize>
                            <sec:authorize access="not hasRole('ADMIN')">
                                <input disabled type="text" name="species" class="form-control" value="${animal.species}"/>
                            </sec:authorize>
                            <c:if test="${not empty species_error}"><label class="text-error">${species_error_message}</label></c:if>
                            </div>
                        <sec:authorize access="hasRole('ADMIN')">
                            <div class="col-xs-2">
                                <button type="submit" class="btn btn-default">Save Changes</button>
                            </div>
                        </sec:authorize>
                    </div>
                </form>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Characteristics</div>
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/">
                    <div class="form-group panel-body">
                        <label class="control-label col-xs-1">Food Needed:</label>
                        <div class="col-xs-4">
                            <sec:authorize access="hasRole('USER')">
                                <div class="input-group">
                                    <input type="number" name="name" class="form-control" placeholder="unknown" value="${animal.foodNeeded}"/>
                                    <span class="input-group-addon">kg / day</span>
                                </div>
                            </sec:authorize>
                            <sec:authorize access="not hasRole('USER')">
                                <div class="input-group">
                                    <input disabled type="text" name="name" class="form-control" placeholder="unknown" value="${animal.foodNeeded}"/>
                                    <span class="input-group-addon">kg / day</span>
                                </div>
                            </sec:authorize>
                            <c:if test="${not empty foodNeeded_error}"><label class="text-error">${foodNeeded_error_message}</label></c:if>
                            </div>
                            <label class="control-label col-xs-1">Reproduction Rate:</label>
                            <div class="col-xs-4">
                            <sec:authorize access="hasRole('USER')">
                                <div class="input-group">
                                    <input type="number" name="species" class="form-control" placeholder="unknown" value="${animal.repreductionRate}"/>
                                    <span class="input-group-addon">% / year</span>
                                </div>
                            </sec:authorize>
                            <sec:authorize access="not hasRole('USER')">
                                <div class="input-group">
                                    <input disabled type="text" name="species" class="form-control" placeholder="unknown" value="${animal.repreductionRate}"/>
                                    <span class="input-group-addon">% / year</span>
                                </div>
                            </sec:authorize>
                            <c:if test="${not empty reproductionRate_error}"><label class="text-error">${reproductionRate_error_message}</label></c:if>
                            </div>
                        <sec:authorize access="hasRole('USER')">
                            <div class="col-xs-2">
                                <button type="submit" class="btn btn-default">Save Changes</button>
                            </div>
                        </sec:authorize>
                    </div>
                </form>
            </div>

            <hr/>

            <div class="panel panel-default">
                <div class="panel-heading">Food Chain</div>

                <div class="panel-body">

                    <table class="table table-striped">
                        <caption>Prey</caption>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Species</th>
                                <th>Count</th>
                                <th>Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${animal.prey}" var="prey">
                                <tr>
                                    <td><c:out value="${prey.prey.name}"/></td>
                                    <td><c:out value="${prey.prey.species}"/></td>
                                    <td><c:out value="${prey.animalCount}"/></td>
                                    <td>
                                        <sec:authorize access="hasRole('ADMIN')">
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
            </div>
        </div>
    </div>
</jsp:attribute>
</domspec:maintemplate>>

