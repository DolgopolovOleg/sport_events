<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>Das ist header - ${title} |
    <sec:authorize access="isAnonymous()">
        <a href="/login">Login</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="/logout">Logout</a>
    </sec:authorize>


    <sec:authorize access="hasRole('ADMIN')">
        <b>Вы админ!</b>
    </sec:authorize>

</div>

