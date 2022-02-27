<%-- 
    Document   : profileUsu
    Created on : 20-feb-2022, 18:21:20
    Author     : defie
--%>

<%@page import="java.util.Objects"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<% 
    if (Objects.isNull(request.getAttribute("dataProfile"))) {
        System.out.println("ÑEÑE");
        
            request.getRequestDispatcher("/servletProfileUsu").forward(request, response);
    }
%>
<html>
    <head>
        <title>Perfil del usuario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <%
            String usuario = (String) session.getAttribute("username");
        %>
        <div class="user">Usuario: <%= usuario %>. <form action="/webapp1/servletUsuarios" method="post"><button type="submit" name="button" value="logout">Cerrar sesi&oacute;n</button></form></div>
        <h1> Perfil de <%= usuario %> </h1>
        <center> <a href="/webapp1/jsp/registroVid.jsp"><button type="button">Modificar perfil</button></a> </center>    
        
        <div>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Edad</th>
                    <th>Descripci&oacute;n</th>
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
                    <td>${user.photo}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>   
        <!--<center> <button type="button" action="/webapp1/servletListadoVid" method="get">Mis videos</button></a> </center>    -->
    </body>
</html>
