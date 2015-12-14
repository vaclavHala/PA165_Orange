<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="dominatingspicies" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<dominatingspicies:maintemplate>
    <jsp:attribute name="title">Dominating Species - Orange</jsp:attribute>
    <jsp:attribute name="content">

        <div class="jumbotron">
            <div class="container">
                <h3>Description</h3>
                <small class="text-sm">
                    There are several animals on the planet.
                    Animal is defined by its name, species (not an entity, only textual attribute).
                    Every animal eat or is eaten by other animals (cannibalism is also possible).
                    Every animal needs some environment for its life.
                    Example: a carp need 95 % of fresh water and 5 % of mug.
                    Environment is defined by name and description.
                    Aim of this project is to create system for management of food chain.
                    System Administrator could manage all entities (CRUD).
                    An laboratory worker (ordinal user) can enter his findings about food chain
                    and also living environment of all the animals in the system.
                </small>
                <h3>Team</h3>
                <ul>
                    <li>Václav Hála (<a href="https://github.com/vaclavHala">https://github.com/vaclavHala</a>) - team leader</li>
                    <li>Petr Domkař (<a href="https://github.com/pdomkar">https://github.com/pdomkar</a>)</li>
                    <li>Bc. Daniel Minárik (<a href="https://github.com/danielminarik">https://github.com/danielminarik</a>)</li>
                    <li>Bc. Ivan Králik (<a href="https://github.com/ivan-kralik">https://github.com/ivan-kralik</a>)</li>
                </ul>
            </div>
        </div>

    </jsp:attribute>
</dominatingspicies:maintemplate>