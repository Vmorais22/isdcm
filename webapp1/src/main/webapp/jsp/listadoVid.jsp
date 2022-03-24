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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>WebApp1 - Listado de videos</title>
        <meta charset="UTF-16">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <h1>Listado de vídeos</h1>
        <div>
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Miniatura</th>
                        <th scope="col">Título</th>
                        <th scope="col">Autor</th>
                        <th scope="col">Fecha de creación</th>
                        <th scope="col">Duración</th>
                        <th scope="col">Vistas</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Formato</th>
                        <th scope="col">Video</th>
                    </tr>
                </thead>
                <tbody>
                    <% while(r.next()) { %>   
                    <tr>
                        <th scope="row"><%= r.getString("videoId") %></th>
                        <td><img src=<%= r.getString("miniature")%> alt="pic" width="100" height="50" /></td>
                        <td><%= r.getString("title") %></td>
                        <td><%= r.getString("author") %></td>
                        <td><%= r.getString("creationDate") %></td>
                        <td><%= r.getString("duration") %></td>
                        <td><%= r.getString("views") %></td>
                        <td><%= r.getString("description") %></td>
                        <td><%= r.getString("format") %></td>
                        <!--<td><%= r.getString("url") %></td>-->
                        <td>
                            <!--<form id="boardButton" action="visorVid.jsp">-->
                             <form id="boardButton" action="/webapp1/servletREST" method="post">   
                                <input type="hidden" name="link_url" value=<%= r.getString("url") %>/>
                                <input type="hidden" name="currentVideoId" value=<%= r.getString("videoId") %>/>
                                <input type="hidden" name="currentVideoTitle" value=<%= r.getString("title") %>/>
                                <input type="hidden" name="currentVideoAuthor" value=<%= r.getString("author") %>/>
                                <input type="hidden" name="currentVideoDescription" value=<%= r.getString("description") %>/>
                                <input type="hidden" name="currentVideoViews" value=<%= r.getString("views") %>/>
                                
                                <button class="btn btn-primary">Reproducir</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <br></br>        
        <div class="user">
            <a href="registerVid.jsp" class="btn btn-primary">Subir nuevo video</a>
            <a href="profileUsu.jsp" class="btn btn-secondary">Volver</a>
        </div>
    </body>
</html>
