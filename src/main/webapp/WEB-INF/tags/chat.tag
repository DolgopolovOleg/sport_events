<%@ attribute name="comments" required="true"  type="java.util.ArrayList"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul>
    <c:forEach items="${comments}" var="comment">
        <li><b>${comment.user.name} :</b>
            <p style="font-style:italic;">${comment.text}</p>
        </li>
    </c:forEach>
</ul>