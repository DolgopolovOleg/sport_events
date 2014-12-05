<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <c:set var="place" value="${placeViewView.place}" />


        <div class="body">
            <h1>Place</h1>


            <p>${place.name}</p>
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


        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>