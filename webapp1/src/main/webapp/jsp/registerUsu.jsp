<%-- 
    Document   : registerUsu
    Created on : 20-feb-2022, 18:20:25
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
        <h1>Register page</h1>
        <form action="servletUsuarios" method="post">
            <br><input type="text" placeholder="Username" name="username"/></br>
            <br><input type="password" placeholder="Contraseña" name="passwd"/></br>
            <br><input type="password" placeholder="Repita contraseña" name="passwd"/></br>
            <br><input type="text" placeholder="Nombre" name="realName"/></br>
            <br><input type="text" placeholder="Apellido" name="surname"/></br>
            <br><input type="text" placeholder="Email" name="email"/></br>
            <br><input type="number" placeholder="Edad" name="age" min="12" max="100"/></br>
            <br><input type="text" placeholder="Descripción" name="description"/></br>
            <br></br>
            <br><input type="submit" value="Enviar"/></br>
        </form>
        <br></br>
        <a href="/webapp1/">Iniciar Sesión</a>
    </body>
</html>
