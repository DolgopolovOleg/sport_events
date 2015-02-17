<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <div class="body">
            <h1>Activation:</h1>

            <ul>

                <form method="GET" action="/activateUser">
                    <input type="text" name="activationCode" size="30" />
                    <input type="hidden" name="userID" value="${user._id}" />
                    <input type="submit" value="Активировать" />
                </form>
            </ul>

        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>