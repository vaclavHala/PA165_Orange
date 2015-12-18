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

            <c:set var="disabled" scope="request" value="disabled"/>
            <sec:authorize access="hasAuthority('ADMIN')">
                <c:set var="disabled" scope="request" value=""/>
            </sec:authorize>

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
                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/identity">
                            <div class="form-group">
                                <label class="control-label col-xs-1">Name:</label>
                                <div class="col-xs-4">
                                    <input ${disabled} type="text" name="name" class="form-control" value="${animal.name}"/>
                                    <c:if test="${not empty name_error}"><label class="text-error">${name_error_message}</label></c:if>
                                    </div>
                                    <label class="control-label col-xs-1">Species:</label>
                                    <div class="col-xs-4">
                                        <input ${disabled} type="text" name="species" class="form-control" value="${animal.species}"/>
                                    <c:if test="${not empty species_error}"><label class="text-error">${species_error_message}</label></c:if>
                                    </div>
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <div class="col-xs-2">
                                        <button type="submit" class="btn btn-default">Save Changes</button>
                                    </div>
                                </sec:authorize>
                            </div>
                        </form>
                        <hr/>
                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/characteristics">
                            <div class="form-group">
                                <label class="control-label col-xs-2">Food Needed:</label>
                                <div class="col-xs-5">
                                    <div class="input-group">
                                        <input type="number" name="foodNeeded" class="form-control" placeholder="unknown" value="${animal.foodNeeded}"/>
                                        <span class="input-group-addon">kg/day</span>
                                    </div>
                                    <c:if test="${not empty foodNeeded_error}"><label class="text-error">${foodNeeded_error_message}</label></c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-2">Reproduction Rate:</label>
                                    <div class="col-xs-5">
                                        <div class="input-group">
                                            <input type="number" name="reproductionRate" class="form-control" placeholder="unknown" value="${animal.reproductionRate}"/>
                                        <span class="input-group-addon">%/year</span>
                                    </div>
                                    <c:if test="${not empty reproductionRate_error}"><label class="text-error">${reproductionRate_error_message}</label></c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-2"></div>
                                    <div class="col-xs-3">
                                        <button type="submit" class="btn btn-default">Save Changes</button>
                                    </div>
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
                                <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/prey">
                                <select class="form-control" name="prey">
                                    <option value="">Choose Prey</option>
                                    <c:forEach items="${addablePrey}" var="animal">
                                        <option value="${animal.id}">${animal.name} of ${animal.species}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" class="btn btn-default">Add Prey</button>
                            </form>
                            <hr/>
                            <form class="form-inline" method="POST" >
                                <label class="control-label">Selected Prey:</label>
                                <button type="submit" class="btn btn-default" formaction="${pageContext.request.contextPath}/animal/${animal.id}/eaten/update" >Update</button>
                                <button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/animal/${animal.id}/eaten/delete">Delete</button>

                                <div class="col-xs-12" style="height:20px;"></div>

                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Name</th>
                                            <th>Species</th>
                                            <th>Count</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${animal.prey}" var="prey">
                                            <tr>
                                                <td><input type="checkbox" name="check_${prey.id}"/></td>
                                                <td><c:out value="${prey.prey.name}"/></td>
                                                <td><c:out value="${prey.prey.species}"/></td>
                                                <td>
                                                    <div class="form-group">
                                                        <div class="input-group">
                                                            <input type="number" step="any" name="count_${prey.id}" style="width: 100px;" class="form-control" placeholder="unknown" value="${prey.animalCount}"/>
                                                            <div class="input-group-addon">pcs/wk</div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </form>
                        </div>

                        <div class="col-xs-6">
                            <h4>Predators</h4>
                            <hr/>
                            <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/animal/${animal.id}/predator">
                                <select class="form-control" name="predator">
                                    <option value="">Choose Predator</option>
                                    <c:forEach items="${addablePredators}" var="animal">
                                        <option value="${animal.id}">${animal.name} of ${animal.species}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" class="btn btn-default">Add Predator</button>
                            </form>
                            <hr/>
                            <form class="form-inline" method="POST" >
                                <label class="control-label">Selected Predators:</label>
                                <button type="submit" class="btn btn-default" formaction="${pageContext.request.contextPath}/animal/${animal.id}/eaten/update" >Update</button>
                                <button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/animal/${animal.id}/eaten/delete">Delete</button>

                                <div class="col-xs-12" style="height:20px;"></div>

                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Name</th>
                                            <th>Species</th>
                                            <th>Count</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${animal.predators}" var="predator">
                                            <tr>
                                                <td><input type="checkbox" name="check_${predator.id}"/></td>
                                                <td><c:out value="${predator.predator.name}"/></td>
                                                <td><c:out value="${predator.predator.species}"/></td>
                                                <td>
                                                    <div class="form-group">
                                                        <div class="input-group">
                                                            <input type="number" step="any" name="count_${predator.id}" style="width: 100px;" class="form-control" placeholder="unknown" value="${predator.animalCount}"/>
                                                            <div class="input-group-addon">pcs/wk</div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </form>
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
                                <c:forEach items="${aes}" var="ae">
                                    <tr>
                                        <td><c:out value="${ae.environment.name}"/></td>
                                        <td><c:out value="${ae.environment.description}"/></td>
                                        <td><c:out value="${ae.environment.maxAnimalCount}"/></td>
                                        <td>
                                            <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/animalenvironment/${animal.id}/${ae.id}/update/animal">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input type="number" step="any" max="100" min="0" name="percentage" style="width: 100px;" class="form-control" placeholder="unknown" value="${ae.percentage}"/>
                                                        <span class="input-group-addon">%</span>
                                                    </div>
                                                    <button  type="submit" class="btn btn-default">Update</button>
                                                </div>
                                            </form>
                                        </td>
                                        <td>
                                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/animalenvironment/${animal.id}/${ae.environment.id}/remove/animal">Remove for this animal</a>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/animalenvironment/${animal.id}/envId/addEnvironment">
                            <div class="form-group">
                                <div class="col-xs-10" >
                                    <select class="form-control" name="envId">
                                        <c:forEach items="${addableEnvs}" var="environment">
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

