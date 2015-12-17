<%@tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="content" fragment="true" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">DominatingSpecies</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/environment/">Environments</a></li>
                        <li><a href="${pageContext.request.contextPath}/animal/">Animals</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a><sec:authentication property="principal.username"/></a></li>
                        <li><a href="<c:url value="/logout" />">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">

            <h2><c:out value="${title}"/></h2>

            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/>
                </div>
            </c:if>
            <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <jsp:invoke fragment="content"/>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script>
            //from http://www.aidanlister.com/2014/03/persisting-the-tab-state-in-bootstrap/
            (function ($) {
                $.fn.stickyTabs = function (options) {
                    var context = this

                    var settings = $.extend({
                        getHashCallback: function (hash, btn) {
                            return hash
                        }
                    }, options);

                    // Show the tab corresponding with the hash in the URL, or the first tab.
                    var showTabFromHash = function () {
                        var hash = window.location.hash;
                        var selector = hash ? 'a[href="' + hash + '"]' : 'li.active > a';
                        $(selector, context).tab('show');
                    }

                    // We use pushState if it's available so the page won't jump, otherwise a shim.
                    var changeHash = function (hash) {
                        if (history && history.pushState) {
                            history.pushState(null, null, '#' + hash);
                        } else {
                            scrollV = document.body.scrollTop;
                            scrollH = document.body.scrollLeft;
                            window.location.hash = hash;
                            document.body.scrollTop = scrollV;
                            document.body.scrollLeft = scrollH;
                        }
                    }

                    // Set the correct tab when the page loads
                    showTabFromHash(context)

                    // Set the correct tab when a user uses their back/forward button
                    $(window).on('hashchange', showTabFromHash);

                    // Change the URL when tabs are clicked
                    $('a', context).on('click', function (e) {
                        var hash = this.href.split('#')[1];
                        var adjustedhash = settings.getHashCallback(hash, this);
                        changeHash(adjustedhash);
                    });

                    return this;
                };
            }(jQuery));

            $('.nav-tabs').stickyTabs();
        </script>
    </body>
</html>
