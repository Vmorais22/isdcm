<%-- 
    Document   : profileUsu
    Created on : 20-feb-2022, 18:21:20
    Author     : defie
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="model.Usuario"%>
<%@page import="java.util.Objects"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<% 
    // String usuario = (String) session.getAttribute("currentUser");
    String usuario = request.getSession().getAttribute("currentUser").toString();
    Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM USERS WHERE username=?");
    preparedStatement.setString(1, usuario);
    ResultSet r = preparedStatement.executeQuery();
    if (r.next()){
        System.out.println("HTML: "+r.getString("username"));
    }
%>
<html>
    <head>
        <title>WebApp1 - Perfil de <%= usuario %></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <h1> Perfil de <%= usuario %> </h1>
        <div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Foto de Usuario</th>
                        <th scope="col">Nombre de Usuario</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Email</th>
                        <th scope="col">Edad</th>
                        <th scope="col">Descripción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row"><%= r.getString("userId") %></th>
                        <td><img src=<%= r.getString("photo")%> alt="pic" width="90" height="100"></td>
                        <td><%= r.getString("username") %></td>
                        <td><%= r.getString("realName") %></td>
                        <td><%= r.getString("surname") %></td>
                        <td><%= r.getString("email") %></td>
                        <td><%= r.getString("age") %></td>
                        <td><%= r.getString("description") %></td>
                    </tr>
                </tbody>
            </table>
        </div>       
        <div class="user">
            <a href="listadoVid.jsp" class="btn btn-primary">Listado de vídeos</a>
            <a href="registerVid.jsp" class="btn btn-primary">Subir nuevo vídeo</a>
            <a href="modifyUsu.jsp" class="btn btn-primary">Modificar perfil</a>
            <a href="/webapp1/" class="btn btn-danger">Cerrar sesión</a>
        </div>
        </body>
</html>
