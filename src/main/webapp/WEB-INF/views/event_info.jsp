<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <c:set var="event" value="${eventView.event}" />
        <c:set var="place" value="${eventView.event.place}" />


        <div class="body">
            <h1>Event</h1>


            <p>${event.name}</p>
            <ul>
                <li><u>Играем в </u> : ${event.sport.name}</li>
                <%--<li><u>Начинам </u> : ${event.date_start}</li>--%>
                <li><u>Описание</u> :
                    <p>${event.description}</p>
                </li>
                <li>
                    <u>Место</u> :
                    <ul>
                        <li><u>Координаты</u> : ${place.longitude} - ${place.latitude}</li>
                        <li><u>Описание</u> :
                            <p>${place.description}</p>
                        </li>
                        <li><u>Создатель</u> : ${place.creator.name}</li>
                        <li><u>Оборудование</u> :
                            <ul>
                                <c:forEach items="${eventView.placeView.equipments}" var="equipment">
                                    <li><b>${equipment.key.name}</b> x ${equipment.value}</li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li>
                    <u>Участники</u> :
                    <ul>
                        <c:forEach items="${eventView.participants}" var="participant">
                            <li><b>${participant.user.name}</b> (${participant.role})</li>
                        </c:forEach>
                    </ul>
                </li>

                <li>
                    <u>Комменты</u> :
                    <ul>
                        <c:forEach items="${eventView.comments}" var="comment">
                            <li><b>${comment.user.name} :</b>
                                <p style="font-style:italic;">${comment.text}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>



        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>