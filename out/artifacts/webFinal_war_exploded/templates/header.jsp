<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="position: static">

    <c:choose>
        <c:when test="${empty user.login}">
            <ul>
                <li>User: guest</li>
                <li><a href="<c:url value="../login.jsp"/>">Login</a></li>
                <li><a href="<c:url value="../registration.jsp"/>">Registration</a></li>
            </ul>
        </c:when>
        <c:otherwise>

            <form name="form" method="post" action="logout">
                <ul>
                    <li>User: ${user.login}</li>
                    <li><a href="<c:url value="/mainpage"/>">My page</a></li>
                    <li><a href="JavaScript:document.form.submit()">Logout</a></li>
                </ul>
            </form>

        </c:otherwise>
    </c:choose>
</header>

