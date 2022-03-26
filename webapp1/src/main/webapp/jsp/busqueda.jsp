<%--
    Document   : busqueda
    Created on : 20-mar-2022, 11:59:49
    Author     : defie
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Video"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Búsqueda de vídeo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="/webapp1/css/style.css" >

    </head>
    <body>  
       <br/>
        <form action="/webapp1/servletREST" method="get">
            <label>Título</label>
            <input type="text" name="titulo"/>
            <label>Autor</label>
            <input type="text" name="autor"/>
            <label>Fecha</label>
            <input type="text" name="fecha"/>
            <input type="submit" name="submit" value="Enviar"/>
        </form>  
        <br/>
        <br/>
        <% 
            Object a = request.getSession().getAttribute("videoSearch");
            List<Video> videos;
            if(a != null) {
                videos =(List<Video>)a;
                if(!videos.isEmpty()) {
        %>
        <h1>Resultados de la búsqueda:</h1>
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
                    <% for(Video vid: videos) { %>   
                    <tr>
                        <th scope="row"><%= vid.getVideoId() %></th>
                        <td><img src=<%= vid.getMiniature() %> alt="pic" width="100" height="50" /></td>
                        <td><%= vid.getTitle() %></td>
                        <td><%= vid.getAuthor() %></td>
                        <td><%= vid.getCreationDate() %></td>
                        <td><%= vid.getDuration() %></td>
                        <td><%= vid.getViews()%></td>
                        <td><%= vid.getDescription() %></td>
                        <td><%= vid.getFormat() %></td>
                        <!--<td><%= vid.getUrl()%></td>-->
                        <td>
                            <!--<form id="boardButton" action="visorVid.jsp">-->
                             <form id="boardButton" action="/webapp1/servletREST" method="post">   
                                <input type="hidden" name="link_url" value=<%= vid.getUrl() %>/>
                                <input type="hidden" name="currentVideoId" value=<%= vid.getVideoId() %>/>
                                <input type="hidden" name="currentVideoTitle" value=<%= vid.getTitle() %>/>
                                <input type="hidden" name="currentVideoAuthor" value=<%= vid.getAuthor() %>/>
                                <input type="hidden" name="currentVideoDescription" value=<%= vid.getDescription() %>/>
                                <input type="hidden" name="currentVideoViews" value=<%= vid.getViews() %>/>
                                
                                <button class="btn btn-primary">Reproducir</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
                
            </table>
        </div>
        <% 
          }
        else { %>
        <p>  ⚠ No hay vídeos que coincidan con los parámetros introducidos ⚠</p>
        <%}
    }%>

        <br/><br/>
        <a href="profileUsu.jsp" class="btn btn-secondary">Volver</a>
        <!--
        <form action="resources/javaee8/postInfo" method="post">
            <label>Información</label>            
            <input type="text" name="info"/>
            <label>Fecha</label>
            <input type="text" name="fecha"/>
            <input type="submit" name="submit" value="Enviar con POST"/>
        </form>-->
    </body>    
</html>