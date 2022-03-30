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
            
            <span>
                <label>Año:</label>
                 <span>
                 <select name="year">
                    <option value="00"> - </option>
                    <option value="2022">2022</option>
                    <option value="2021">2021</option>
                    <option value="2020">2020</option>
                    <option value="2019">2019</option>
                    <option value="2018">2018</option>
                    <option value="2017">2017</option>
                    <option value="2016">2016</option>
                    <option value="2015">2015</option>
                    <option value="2014">2014</option>
                    <option value="2013">2013</option>
                    <option value="2012">2012</option>
                    <option value="2011">2011</option>
                    <option value="2010">2010</option>
                    <option value="2009">2009</option>
                    <option value="2008">2008</option>
                    <option value="2007">2007</option>
                    <option value="2006">2006</option>
                    <option value="2005">2005</option>
                    <option value="2004">2004</option>
                    <option value="2003">2003</option>
                    <option value="2002">2002</option>
                    <option value="2001">2001</option>
                    <option value="2000">2000</option>
                    <option value="1999">1999</option>
                    <option value="1998">1998</option>
                    <option value="1997">1997</option>
                    <option value="1996">1996</option>
                    <option value="1995">1995</option>
                    <option value="1994">1994</option>
                    <option value="1993">1993</option>
                    <option value="1992">1992</option>
                    <option value="1991">1991</option>
                    <option value="1990">1990</option>
            </select>
       </span>
                <span>
                <label>Mes:</label>
                <select name="month">
                   <option value="00"> - </option>
                   <option value="01">Enero</option>
                   <option value="02">Febrero</option>
                   <option value="03">Marzo</option>
                   <option value="04">Abril</option>
                   <option value="05">Mayo</option>
                   <option value="06">Junio</option>
                   <option value="07">Julio</option>
                   <option value="08">Agosto</option>
                   <option value="09">Septiembre</option>
                   <option value="10">Octubre</option>
                   <option value="11">Noviembre</option>
                   <option value="12">Diciembre</option>
                </select> 
            </span>
            <span>
                <label>Día:</label>
                <select name="day">
                    <option value="00"> - </option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08">8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    <option value="29">29</option>
                    <option value="30">30</option>
                    <option value="31">31</option>
                </select>
           </span>
           
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
                        <td><%= vid.getCreationYear() + "-" + vid.getCreationMonth() + "-" + vid.getCreationDay()%></td>
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