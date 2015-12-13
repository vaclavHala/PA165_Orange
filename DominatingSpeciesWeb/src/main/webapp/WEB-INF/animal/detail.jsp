<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="domspec"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<domspec:maintemplate title="Animal Detail">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="/vendor/bootstrap-combobox/css/bootstrap-combobox.css">
    </jsp:attribute>

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
                                    <span class="input-group-addon">kg/day</span>
                                </div>
                            </sec:authorize>
                            <sec:authorize access="not hasRole('USER')">
                                <div class="input-group">
                                    <input disabled type="text" name="name" class="form-control" placeholder="unknown" value="${animal.foodNeeded}"/>
                                    <span class="input-group-addon">kg/day</span>
                                </div>
                            </sec:authorize>
                            <c:if test="${not empty foodNeeded_error}"><label class="text-error">${foodNeeded_error_message}</label></c:if>
                            </div>
                            <label class="control-label col-xs-1">Reproduction Rate:</label>
                            <div class="col-xs-4">
                            <sec:authorize access="hasRole('USER')">
                                <div class="input-group">
                                    <input type="number" name="species" class="form-control" placeholder="unknown" value="${animal.repreductionRate}"/>
                                    <span class="input-group-addon">%/year</span>
                                </div>
                            </sec:authorize>
                            <sec:authorize access="not hasRole('USER')">
                                <div class="input-group">
                                    <input disabled type="text" name="species" class="form-control" placeholder="unknown" value="${animal.repreductionRate}"/>
                                    <span class="input-group-addon">%/year</span>
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

                    <div class="col-xs-6">

                        <h4>Prey</h4>
                        <hr/>

                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/prey">
                            <div class="form-group">
                                <div class="col-xs-10" >
                                    <select class="form-control" name="prey">
                                        <option value="">Choose Prey</option>
                                        <c:forEach items="${allAnimals}" var="animal">
                                            <option value="${animal.id}">${animal.name} of ${animal.species}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-default">Add Prey</button>
                            </div>
                        </form>

                        </hr>

                        <table class="table table-striped">
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
                                        <td>
                                            <sec:authorize access="hasRole('USER')">
                                                <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/delete">
                                                    <div class="form-group">
                                                        <div class="input-group">
                                                            <input type="number" step="any" name="count" style="width: 100px;" class="form-control" placeholder="unknown" value="${prey.animalCount}"/>
                                                            <span class="input-group-addon">pcs/wk</span>
                                                        </div>
                                                        <button  type="submit" class="btn btn-default">Update</button>
                                                    </div>
                                                </form>
                                            </sec:authorize>
                                        </td>
                                        <td>
                                            <sec:authorize access="hasRole('ADMIN')">
                                                <form method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/delete">
                                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure wish to to delete this association?');">Delete</button>
                                                </form>
                                            </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>

                    <div class="col-xs-6">

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
                                        <td>
                                            <sec:authorize access="hasRole('USER')">
                                                <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/delete">
                                                    <div class="form-group">
                                                        <div class="input-group">
                                                            <input type="number" step="any" name="count" style="width: 100px;" class="form-control" placeholder="unknown" value="${prey.animalCount}"/>
                                                            <span class="input-group-addon">pcs/wk</span>
                                                        </div>
                                                        <button  type="submit" class="btn btn-default">Update</button>
                                                    </div>
                                                </form>
                                            </sec:authorize>
                                        </td>
                                        <td>
                                            <sec:authorize access="hasRole('ADMIN')">
                                                <form method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/delete">
                                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure wish to to delete this association?');">Delete</button>
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

            <div class="panel panel-default">
                <div class="panel-heading">Animal's environments</div>

                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Max. animal count</th>
                                <th>Percentage</th>
                                <th>Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${environments}" var="environment">
                                <tr>
                                    <td><c:out value="${environment.name}"/></td>
                                    <td><c:out value="${environment.description}"/></td>
                                    <td><c:out value="${environment.maxAnimalCount}"/></td>
                                    <td>TODO</td>
                                    <td>
                                        <a class="btn btn-warning btn-xs" href="${pageContext.request.contextPath}/animalenvironment/${animal.id}/${environment.id}/remove/animal">Remove for this animal</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Add environment</label>
                            <div class="col-sm-9">
                                <select id="env" name="env" class="form-control" path="env">
                                    <c:forEach items="${allEnvironments}" var="environment">
                                        <option value="${environment.id}">${environment.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-xs-1">
                                <a href="${pageContext.request.contextPath}/animalenvironment/${animal.id}/${env}/add/animal" class="btn btn-success">Add</a>
                            </div>
                        </div>

                </div>
            </div>
        </div>
    </div>
</jsp:attribute>
</domspec:maintemplate>>

