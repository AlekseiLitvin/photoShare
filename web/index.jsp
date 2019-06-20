<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>First page</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<%@include file="templates/header.jsp" %>
<br>


<c:forEach items="${list}" var="image" end="11">
    <div class="container">
        <div class="photo" style="background-image: url(${pageContext.request.contextPath}/image/${image.id})"></div>
        <hr>
        <div class="text">
            <p class="info"><span>${image.user.login}</span>, ${image.date} ${image.description}</p>
        </div>
    </div>
</c:forEach>

<hr>

<br>
<%@include file="templates/footer.jsp" %>
</body>
</html>
