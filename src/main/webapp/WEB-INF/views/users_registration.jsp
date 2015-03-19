<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setLocale value="ru"/>
<fmt:setBundle basename="languages.lang"/>
<fmt:requestEncoding value="UTF-8" />

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <%--<div class="body">--%>
            <%--<h1>Create user</h1>--%>
            <%--<div><fmt:message key="first"/></div>--%>

            <%--<f:form method="POST" modelAttribute="user" action="register">--%>
                <%--<div>--%>
                    <%--<label for="user_name">Name* : </label>--%>
                    <%--<f:input path="username" id="user_name" size="10"/>--%>
                    <%--<f:errors path="username" cssClass="error" />--%>
                <%--</div>--%>

                <%--<div>--%>
                    <%--<label for="user_password">Password* : </label>--%>
                    <%--<f:input path="password" id="user_password" size="10"/>--%>
                    <%--<f:errors path="password" cssClass="error" />--%>
                <%--</div>--%>

                <%--<input type="submit">--%>
            <%--</f:form>--%>

        <%--</div>--%>


        <sec:authorize access="isAnonymous()">
            <div class="body">
                <form:form action="${pageContext.request.contextPath}/user/register" commandName="user" method="POST" enctype="utf8" role="form">

                    <!-- Add CSRF token to the request. -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <!--
                    If the user is using social sign in, add the signInProvider
                    as a hidden field.
                    -->
                    <c:if test="${user.signInProvider != null}">
                        <form:hidden path="signInProvider"/>
                    </c:if>

                    <p>
                        firstName:
                        <form:input id="user-firstName" path="firstName" />
                    </p>

                    <p>
                        lastName:
                        <form:input id="user-lastName" path="lastName" />
                    </p>

                    <p>
                        email:
                        <form:input id="user-email" path="email" />
                    </p>

                    <c:if test="${user.signInProvider == null}">

                        <p>
                            password:
                            <form:password id="user-password" path="password" />
                        </p>

                        <p>
                            password verification:
                            <form:password id="user-passwordVerification" path="passwordVerification" />
                        </p>
                    </c:if>
                    <!-- Add the submit button to the form. -->
                    <p>
                        <button type="submit" class="btn btn-default">Register</button>
                    </p>

                </form:form>
            </div>
        </sec:authorize>

    </tiles:putAttribute>
</tiles:insertDefinition>