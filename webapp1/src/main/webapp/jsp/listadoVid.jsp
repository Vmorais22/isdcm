<%-- 
    Document   : profileUsu
    Created on : 20-feb-2022, 18:21:20
    Author     : defie
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
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
    String usuario = request.getSession().getAttribute("currentUser").toString();
    Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM VIDEOS ORDER BY creationYear, creationMonth, creationDay DESC");
    ResultSet r = preparedStatement.executeQuery();
    ArrayList<Video> listVideo = new ArrayList<Video>(); 
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
                    <% 
                        if(request.getSession().getAttribute("listVideos") == null) {
                            while(r.next()) {
                            listVideo.add(new Video(Integer.parseInt(r.getString("videoId")),r.getString("title"), r.getString("author"), r.getInt("creationYear"), r.getInt("creationMonth"), r.getInt("creationDay"), r.getString("duration"), Integer.parseInt(r.getString("views")), r.getString("description"), r.getString("format"), r.getString("url"), r.getString("miniature")));
                            request.getSession().setAttribute("listVideos", listVideo);
                            }
                        }
                        else {
                            listVideo = (ArrayList<Video>) request.getSession().getAttribute("listVideos");
                        }
                        for(int i = 0; i < listVideo.size(); ++i) {
                        %>   
                    <tr>
                        <th scope="row"><%= listVideo.get(i).getVideoId() %></th>
                        <td><img src=<%= listVideo.get(i).getMiniature() %> alt="pic" width="100" height="50" /></td>
                        <td><%= listVideo.get(i).getTitle() %></td>
                        <td><%= listVideo.get(i).getAuthor() %></td>
                        <td><%= listVideo.get(i).getCreationYear() + "-" + listVideo.get(i).getCreationMonth() + "-" + listVideo.get(i).getCreationDay()%></td>
                        <td><%= listVideo.get(i).getDuration() %></td>
                        <td><%= listVideo.get(i).getViews() %></td>
                        <td><%= listVideo.get(i).getDescription() %></td>
                        <td><%= listVideo.get(i).getFormat() %></td>
                        <!--<td><%= listVideo.get(i).getUrl() %></td>-->
                        <td>
                            <!--<form id="boardButton" action="visorVid.jsp">-->
                             <form id="boardButton" action="/webapp1/servletREST" method="post">   
                                <input type="hidden" name="link_url" value=<%= listVideo.get(i).getUrl() %>/>
                                <input type="hidden" name="currentVideoId" value=<%= listVideo.get(i).getVideoId() %>/>
                                <input type="hidden" name="currentVideoTitle" value=<%= URLEncoder.encode(listVideo.get(i).getTitle()) %>/>
                                <input type="hidden" name="currentVideoAuthor" value=<%= URLEncoder.encode(listVideo.get(i).getAuthor()) %>/>
                                <input type="hidden" name="currentVideoDescription" value=<%= URLEncoder.encode(listVideo.get(i).getDescription()) %>/>
                                <input type="hidden" name="currentVideoViews" value=<%= listVideo.get(i).getViews() %>/>
                                
                                <button class="btn btn-primary">Reproducir</button>
                            </form>
                                </br>
                                <form id="boardButton" action="/webapp1/servletEncription" method="get">   
                                    <input type="hidden" name="vid" value=<%= i %>/>
                                    <% if(!listVideo.get(i).getEncripted()) { %>
                                        <button class="btn btn-danger">No encriptado</button>
                                    <% } else { %>
                                        <button class="btn btn-success">Encriptado</button>
                                    <% } %>
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
