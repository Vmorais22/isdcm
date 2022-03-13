<%-- 
    Document   : login
    Created on : 20-feb-2022, 18:20:07
    Author     : defie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebApp1 - Iniciar Sesión</title>
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
    <div class="container">
        <br>
        <h1>Iniciar Sesión</h1>
        <div class="card">
         <div class="card-body">
          <form action="servletUsuarios" method="get">

           <div class=" form-group row">
            <label for="lastName" class="col-sm-2 col-form-label">Nombre de usuario</label>
            <div class="col-sm-7">
             <input type="text" class="form-control" name="username"
              placeholder="Nombre de usuario">
            </div>
           </div>

           <div class="form-group row">
            <label for="lastName" class="col-sm-2 col-form-label">Contraseña</label>
            <div class="col-sm-7">
             <input type="password" class="form-control" name="passwd"
              placeholder="Contraseña">
            </div>
           </div>

           <button type="submit" class="btn btn-primary">Enviar</button>
           <a href="jsp/registerUsu.jsp" class="btn btn-link">¿No estás registrado?</a>
          </form>
         </div>
        </div>
       </div>
    </body>
</html>
