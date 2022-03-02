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
        <form action="servletRegisterVid" method="post">
            <br><input type="text" placeholder="Título" name="title"/></br>
            <br><input type="text" placeholder="Descripción" name="description"/></br>
            <br></br>
            <br><input type="submit" value="Enviar"/></br>          
        </form>
    </body>
</html>
