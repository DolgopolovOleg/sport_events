<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
</head>
<body>
<div class="page">
    <tiles:insertAttribute name="header" />
    <div class="content">
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>