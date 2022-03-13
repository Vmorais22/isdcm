<%-- 
    Document   : registerVid
    Created on : 20-feb-2022, 18:20:35
    Author     : defie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <div class="container">
            <h1>Register video</h1>
            <div class="card">
                <div class="card-body">
                    <form action="/webapp1/servletRegistroVid" method="post">
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Title</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="text" placeholder="Título" name="title" maxlength="50">
                        </div>
                        </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Description</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="text" placeholder="Descripción" name="description" maxlength="200">
                        </div>
                        </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Format</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="text" placeholder="Formato" name="format" maxlength="10">
                        </div>
                        </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">URL Miniatura</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="url" placeholder="Miniatura (url)" name="miniature" pattern="https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,4}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)(.jpg|.png)">
                        </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="profileUsu.jsp" class="btn btn-secondary">Volver</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
