<%-- 
    Document   : registerVid
    Created on : 20-feb-2022, 18:20:35
    Author     : defie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register video</h1>
            <!--title, author, creationDate, duration, views, description, format-->
        <form action="/webapp1/servletRegistroVid" method="post">
            <br><input type="text" placeholder="Título" name="title" maxlength="50"/></br>
            <br><input type="text" placeholder="Descripción" name="description" maxlength="200"/></br>
            <br><input type="text" placeholder="Formato" name="format" maxlength="10"/></br>
            <br><input type="url" placeholder="Url del video" name="url" id="myURL"/></br>
            <br><input type="url" placeholder="Miniatura (url)" name="miniature"/></br>
            <br></br>
            <br><input type="submit" value="Enviar"/></br>          
        </form>
    </body>
</html>
