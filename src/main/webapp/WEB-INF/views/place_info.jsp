<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <div class="body">
            <h1>Place</h1>


            <p><u>Название</u> :${place.name}</p>
            <ul>
                <li>
                    <u>Координаты</u> : ${place.longitude} - ${place.latitude}
                </li>
                <li>
                    <u>Описание</u> :
                    <p>${place.description}</p>
                </li>
                <li>
                    <u>Создатель</u> : ${place.creator.name}
                </li>
                <li>
                    <u>Оборудование:</u> :
                    <ul>
                        <c:forEach items="${place.equipments}" var="equipment">
                            <li><b>${equipment.equipment.name}</b> x ${equipment.count}</li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <u>Комменты</u> :
                    <ul>
                        <c:forEach items="${comments}" var="comment">
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