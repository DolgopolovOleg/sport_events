<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <div class="body">
            <h1>Events</h1>

            <ul>
                <c:forEach items="${events}" var="event">
                    <li>
                        <a href="/events/${event._id}">${event.name}</a> (<a href="/events/delete?eventID=${event._id}">Удалить</a>")
                    </li>
                </c:forEach>
            </ul>

        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>