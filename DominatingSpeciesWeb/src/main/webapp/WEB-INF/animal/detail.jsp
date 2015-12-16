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

            <ul class="nav nav-tabs">
                <li class="active"><a href="#characteristics" data-toggle="tab">Characteristics</a></li>
                <li><a href="#food" data-toggle="tab">Food Chain</a></li>
                <li><a href="#enviro" data-toggle="tab">Environment</a></li>
            </ul>
            <div class="tab-content">
                <div class="panel panel-default tab-pane active" id="characteristics">
                    <div class="panel-heading">Characteristics</div>
                    <div class="panel-body">
                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/">
                            <div class="form-group">
                                <label class="control-label col-xs-1">Name:</label>
                                <div class="col-xs-4">
                                    <domspec:authorized_textbox role="ADMIN" name="name" value="${animal.name}"/>
                                    <c:if test="${not empty name_error}"><label class="text-error">${name_error_message}</label></c:if>
                                    </div>
                                    <label class="control-label col-xs-1">Species:</label>
                                    <div class="col-xs-4">
                                    <domspec:authorized_textbox role="ADMIN" name="species" value="${animal.species}"/>
                                    <c:if test="${not empty species_error}"><label class="text-error">${species_error_message}</label></c:if>
                                    </div>
                                <domspec:authorized_hidden role="ADMIN">
                                    <jsp:attribute name="content">
                                        <div class="col-xs-2">
                                            <button type="submit" class="btn btn-default">Save Changes</button>
                                        </div>
                                    </jsp:attribute>
                                </domspec:authorized_hidden>
                            </div>
                        </form>
                        <hr/>
                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/">
                            <div class="form-group">
                                <label class="control-label col-xs-2">Food Needed:</label>
                                <div class="col-xs-5">
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
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-2">Reproduction Rate:</label>
                                    <div class="col-xs-5">
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
                                </div>
                                <div class="form-group">
                                <sec:authorize access="hasRole('USER')">
                                    <div class="col-xs-2"></div>
                                    <div class="col-xs-3">
                                        <button type="submit" class="btn btn-default">Save Changes</button>
                                    </div>
                                </sec:authorize>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panel panel-default tab-pane" id="food">
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

                <div class="panel panel-default tab-pane" id="enviro">
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
                                        <td>
                                            <form class="form-inline" method="POST" action="">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input type="number" step="any" name="count" style="width: 100px;" class="form-control" placeholder="unknown" value="${prey.animalCount}"/>
                                                        <span class="input-group-addon">%</span>
                                                    </div>
                                                    <button  type="submit" class="btn btn-default">Update</button>
                                                </div>
                                            </form>
                                        </td>
                                        <td>
                                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/animalenvironment/${animal.id}/${environment.id}/remove/animal">Remove for this animal</a>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animalenvironment/${animal.id}/envId/addEnvironment">
                            <div class="form-group">
                                <div class="col-xs-10" >
                                    <select class="form-control" name="envId">
                                        <c:forEach items="${allEnvironments}" var="environment">
                                            <option value="${environment.id}">${environment.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-default">Add Environment</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</jsp:attribute>
</domspec:maintemplate>>

