<%-- 
    Document   : profileUsu
    Created on : 20-feb-2022, 18:21:20
    Author     : defie
--%>

<%@page import="model.Usuario"%>
<%@page import="java.util.Objects"%>
<!DOCTYPE html>

<% 
    // String usuario = (String) session.getAttribute("currentUser");
    String usuario = request.getSession().getAttribute("currentUser").toString();
    if (Objects.isNull(request.getAttribute("dataProfile"))) {
        request.setAttribute("dataProfile", new Usuario().getProfile(usuario));
    }
%>
<html>
    <head>
        <title>Perfil del usuario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/css/style.css" >
    </head>
    <body>
        <div class="user">Usuario: <%= usuario %>. <form action="servletUsuarios" method="post"><button type="submit" name="button" value="logout">Cerrar sesión</button></form></div>
        <h1> Perfil de <%= usuario %> </h1>
        <center> <a href="registroVid.jsp"><button type="button">Modificar perfil</button></a> </center>    
        <div>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Edad</th>
                    <th>Descripción</th>
                    <th>Foto</th>
                </tr>
                <c:forEach var="user" items="${dataProfile}">
                    <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.realName}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>${user.age}</td>
                    <td>${user.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>   
        <!--<center> <button type="button" action="/webapp1/servletListadoVid" method="get">Mis videos</button></a> </center>    -->
    </body>
</html>
