<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My page</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script>
        function getRequest(node){
            var url = $(node).parent().css("background-image");
            var begin = url.lastIndexOf("/");
            var end = url.lastIndexOf("\"");
            return url.substring(begin, end);
        }

        $(document).ready(function(){
            $("img#download").click(function(){
                window.location.replace("/download" + getRequest(this))
            });


            $("img#delete").click(function() {
                if(confirm("Image will be deleted")){
                    $.post("/delete" + getRequest(this), function(){
                        location.reload();
                    });
                }
            });
        });

    </script>
</head>
<body>
    <%@include file="../templates/header.jsp"%>

    <br><br>
    <form method="post" action="upload" enctype="multipart/form-data">
        <input type="file" name="file" accept="image/*">
        Description: <input type="text" name="description" maxlength="30">
        Black and white filter <input type="checkbox" name="blackAndWhite">
        <input type="submit" value="Upload">
    </form>
    <%@include file="../templates/error.jsp"%>

    <c:forEach items="${userImageList}" var="image">
        <div class="container">
            <div class="photo" style="background-image: url(${pageContext.request.contextPath}/image/${image.id})">
                <img id="download" src="../img/download.png"/>
                <img id="delete" src="../img/delete.png"/>
            </div>
            <hr>
            <div class="text">
                <p class="info"><span>${image.user.login}</span>, ${image.date} ${image.description}</p>
            </div>
        </div>
    </c:forEach>

    <%@include file="../templates/footer.jsp"%>
</body>
</html>
