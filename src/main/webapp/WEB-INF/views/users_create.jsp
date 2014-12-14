<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<fmt:requestEncoding value="UTF-8" />

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <div class="body">
            <h1>Create user</h1>

            <f:form method="POST" modelAttribute="user">
                <div>
                    <label for="user_name">Name : </label>
                    <f:input path="name" id="user_name" size="10"/>
                    <f:errors path="name" cssClass="error" />
                </div>

                <div>
                    <label for="user_sname">Surname : </label>
                    <f:input path="sname" id="user_sname" size="10"/>
                    <f:errors path="sname" cssClass="error" />
                </div>

                <div>
                    <label for="user_nickname">Nickname : </label>
                    <f:input path="nickname" id="user_nickname" size="10"/>
                    <f:errors path="nickname" cssClass="error" />
                </div>

                <div>
                    <label for="user_phone">Phone : </label>
                    <f:input path="phone" id="user_phone" size="10"/>
                    <f:errors path="phone" cssClass="error" />
                </div>

                <div>
                    <label for="user_email">Email : </label>
                    <f:input path="email" id="user_email" size="10"/>
                    <f:errors path="email" cssClass="error" />
                </div>

                <div>
                    <label for="user_password">Email : </label>
                    <f:input path="password" id="user_password" size="10"/>
                    <f:errors path="password" cssClass="error" />
                </div>

                <input type="submit">
            </f:form>

        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>