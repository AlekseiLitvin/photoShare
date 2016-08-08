<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>

    <form action="registration" method="post">
        <div class="block">
            <label for="login">Login</label>
            <input  type="text" name="<%=Constants.LOGIN%>" id="login"
                    required pattern="<%=Constants.LOGIN_PATTERN%>" title="min 3, max 10 chars"><br>
        </div>
        <div class="block">
            <label for="pass">Password</label>
            <input type="password" name="<%=Constants.PASSWORD%>" id="pass"
                   required pattern="<%=Constants.PASSWORD_PATTERN%>" title="min 3, max 10 chars"><br>
        </div>
        <div class="block">
            <label for="passCheck">Repeat password</label>
            <input type="password" name="<%=Constants.PASSWORD_CHECK%>" id="passCheck"
                   required pattern="<%=Constants.PASSWORD_PATTERN%>" title="min 3, max 10 chars"><br>
            <input type="submit" value="Submit">
            <a href="<c:url value="start"/> ">Main page</a>
        </div>

    </form>

    <%@include file="templates/error.jsp"%>

    <%@include file="templates/footer.jsp"%>
</body>
</html>
