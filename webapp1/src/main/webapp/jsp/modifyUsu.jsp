<%-- 
    Document   : modifyUsu
    Created on : 20-feb-2022, 18:20:25
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
        <br>
        <h1>Modify Profile </h1>
        <div class="card">
         <div class="card-body">
            <form action="/webapp1/servletProfileModUsu" method="post">
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-7">
                 <input class="form-control" type="text" placeholder="Username" name="username" maxlength="16">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-7">
                 <input class="form-control" type="password" id="txtPassword" placeholder="Contraseña" name="passwd" minlength="8" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe contener al menos un número y una letra mayúscula y minúscula, y al menos 8 o más caracteres">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Confirm password</label>
                <div class="col-sm-7">
                 <input class="form-control" type="password" id="txtConfirmPassword" placeholder="Repita contraseña" name="passwd2" minlength="8" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe contener al menos un número y una letra mayúscula y minúscula, y al menos 8 o más caracteres">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-7">
                 <input class="form-control" type="text" placeholder="Nombre" name="realName" maxlength="20">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Surname</label>
                <div class="col-sm-7">
                 <input class="form-control" type="text" placeholder="Apellido" name="surName"maxlength="20">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-7">
                 <input class="form-control" type="email" placeholder="Email" name="email" maxlength="50">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Age</label>
                <div class="col-sm-7">
                 <input class="form-control" type="number" placeholder="Edad" name="age" min="12" max="100">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-7">
                 <input class="form-control" type="text" placeholder="Descripción" name="description" maxlength="200">
                </div>
               </div>
                
                <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Profile photo</label>
                <div class="col-sm-7">
                 <input class="form-control" type="url" placeholder="(Opcional) Url foto perfil" name="photo" pattern="https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,4}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)(.jpg|.png)">
                </div>
               </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
                <a href="profileUsu.jsp" class="btn btn-secondary">Volver</a>
            </form>
            <script type="text/javascript">
                window.onload = function () {
                    var txtPassword = document.getElementById("txtPassword");
                    var txtConfirmPassword = document.getElementById("txtConfirmPassword");
                    txtPassword.onchange = ConfirmPassword;
                    txtConfirmPassword.onkeyup = ConfirmPassword;
                    function ConfirmPassword() {
                        txtConfirmPassword.setCustomValidity("");
                        if (txtPassword.value !== txtConfirmPassword.value) {
                            txtConfirmPassword.setCustomValidity("Las contraseñas no coinciden.");
                        }
                    }
                }
            </script>
         </div>
        </div>
       </div>
    </body>
</html>