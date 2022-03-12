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
        <title>Perfil del usuario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <h1> Perfil de <%= usuario %> </h1>
        <!--
        <center> <a href="registroVid.jsp"><button type="button">Modificar perfil</button></a> </center>
        --> 
        <div>
            <table>
                <tr>
                    <th>Photo<th>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Edad</th>
                    <th>Descripción</th>
                </tr>
                <tr>
                     <td><img src=<%= r.getString("photo")%> alt="pic" width="90" height="100" /></td>
                    <td><%= r.getString("userId") %></td>
                    <td><%= r.getString("username") %></td>
                    <td><%= r.getString("realName") %></td>
                    <td><%= r.getString("surname") %></td>
                    <td><%= r.getString("email") %></td>
                    <td><%= r.getString("age") %></td>
                    <td><%= r.getString("description") %></td>
                </tr>
            </table>
        </div>
        <br></br>        
        <div class="user">
            <a href="listadoVid.jsp">
                <button>Listado de vídeos</button>
            </a>
            </br>
            </br>
            <a href="registerVid.jsp">
                <button>Subir nuevo vídeo</button>
            </a>
            </br>
            </br>
            <a href="modifyUsu.jsp">
                <button>Modificar perfil</button>
            </a>
            </br>
            </br>
            <a href="/webapp1/
               ">
                <button>Cerrar sesión</button>
            </a>
        </div>
        <!--<center> <button type="button" action="/webapp1/servletListadoVid" method="get">Mis videos</button></a> </center>    -->
    </body>
</html>
