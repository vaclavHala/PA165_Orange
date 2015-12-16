<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>
<%@ attribute name="role" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('${role}')">
    <input type="text" name="${name}" class="form-control" value="${value}"/>
</sec:authorize>
<sec:authorize access="not hasRole('${role}')">
    <input disabled type="text" name="${name}" class="form-control" value="${value}"/>
</sec:authorize>
