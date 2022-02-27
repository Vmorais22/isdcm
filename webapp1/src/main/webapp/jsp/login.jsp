<%-- 
    Document   : login
    Created on : 20-feb-2022, 18:20:07
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
        <h1>Login page</h1>
        <form action="/webapp1/servletUsuarios" method="post">
            <input type="text" placeholder="Username" name="user"/>
            <input type="password" placeholder="Password" name="passwd"/>
            <input type="submit" value="Enviar"/>            
        </form>
        <a href="/webapp1/jsp/registerUsu.jsp">¿No estás registrado?</a>
    </body>
</html>
