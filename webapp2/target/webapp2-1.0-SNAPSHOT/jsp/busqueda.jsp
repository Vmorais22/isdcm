<%--
    Document   : busqueda
    Created on : 20-mar-2022, 11:59:49
    Author     : defie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>  
       
        <form action="resources/javaee8/getInfo" method="get">
            <label>Título</label>
            <input type="text" name="titulo"/>
            <label>Autor</label>
            <input type="text" name="autor"/>
            <label>Fecha</label>
            <input type="text" name="fecha"/>
            <input type="submit" name="submit" value="Enviar"/>
        </form>        
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