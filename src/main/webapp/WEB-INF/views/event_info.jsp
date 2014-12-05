<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <c:set var="place" value="${event.place}" />


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
                                <c:forEach items="${placeViewView.equipments}" var="equipment">
                                    <li><b>${equipment.key.name}</b> x ${equipment.value}</li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li>
                    <u>Участники</u> :
                </li>

                <li>
                    <u>Комменты</u> :
                </li>
            </ul>



        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>