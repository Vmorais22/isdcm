<%-- 
    Document   : profileUsu
    Created on : 20-feb-2022, 18:21:20
    Author     : defie
--%>

<%@page import="java.util.List"%>
<%@page import="model.Video"%>
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
    PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM VIDEOS ORDER BY creationDate DESC");
    ResultSet r = preparedStatement.executeQuery();
%>
<html>
    <head>
        <title>Listado de videos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <h1>Listado de vídeos</h1>
        <div>
            <table>
                <tr>
                    <th>Miniatura</th>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Fecha de creación</th>
                    <th>Duración</th>
                    <th>Vistas</th>
                    <th>Descripción</th>
                    <th>Formato</th>
                    <th>Video</th>
                </tr>
                <% while(r.next()) { %>   
                <tr>
                    <td><img src=<%= r.getString("miniature")%> alt="pic" width="100" height="50" /></td>
                    <td><%= r.getString("videoId") %></td>
                    <td><%= r.getString("title") %></td>
                    <td><%= r.getString("author") %></td>
                    <td><%= r.getString("creationDate") %></td>
                    <td><%= r.getString("duration") %></td>
                    <td><%= r.getString("views") %></td>
                    <td><%= r.getString("description") %></td>
                    <td><%= r.getString("format") %></td>
                    <td><%= r.getString("url") %></td>

                </tr>
                <% } %>
            </table>
        </div>
        <br></br>        
        <div class="user">
            <a href="registerVid.jsp">
                <button>Subir nuevo video</button>
            </a>
            </br>
            </br>
            <a href="/webapp1/">
                <button>Cerrar sesión</button>
            </a>
        </div>
    </body>
</html>
