<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <div class="body">
            <h1>Place</h1>


            <p><u>Название</u> :${place.username}</p>
            <ul>
                <li>
                    <u>Координаты</u> : ${place.longitude} - ${place.latitude}
                </li>
                <li>
                    <u>Описание</u> :
                    <p>${place.description}</p>
                </li>
                <li>
                    <u>Создатель</u> : ${place.creator.username}
                </li>
                <li>
                    <u>Оборудование:</u> :
                    <ul>
                        <c:forEach items="${place.equipments}" var="equipment">
                            <li><b>${equipment.equipment.username}</b> x ${equipment.count}</li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <u>Комменты</u> :

                    <h:chat comments="${greetings}" />

                    <hr>

                    <h3><b>Ваше высказывание:</b></h3>
                    <f:form action="/comments/addComment" method="post" modelAttribute="comment">
                        <label for="comment_text">Text:</label>
                        <f:textarea path="text" id="comment_text"></f:textarea>
                        <f:hidden path="from" />
                        <f:hidden path="from_id" />
                        <input type="submit" value="Высказаться"/>
                    </f:form>

                    <%--<ul>--%>
                        <%--<c:forEach items="${comments}" var="comment">--%>
                            <%--<li><b>${comment.user.username} :</b>--%>
                                <%--<p style="font-style:italic;">${comment.text}</p>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                </li>
            </ul>


        </div>



    </tiles:putAttribute>
</tiles:insertDefinition>