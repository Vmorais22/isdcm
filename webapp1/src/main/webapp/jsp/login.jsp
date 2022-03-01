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
        <form action="servletUsuarios" method="post">
            <br><input type="text" placeholder="Username" name="username"/></br>
            <br><input type="password" placeholder="Contraseña" name="passwd"/></br>
            <br></br>
            <br><input type="submit" value="Enviar"/></br>           
        </form>
        <br></br>
        <a href="registerUsu.jsp">¿No estás registrado?</a>
    </body>
</html>
