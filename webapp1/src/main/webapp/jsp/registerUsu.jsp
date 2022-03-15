<%-- 
    Document   : registerUsu
    Created on : 20-feb-2022, 18:20:25
    Author     : defie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebApp1 - Registro de Usuario</title>
        <link rel="stylesheet" href="/webapp1/css/style.css" >
    </head>
    <body>
        <div class="container">
            <h1>Registro de Usuario</h1>
            <div class="card">
                <div class="card-body">
                    <form action="/webapp1/servletUsuarios" method="post">
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Nombre de usuario</label>
                        <div class="col-sm-7">
                         <input type="text" class="form-control" name="username"
                          placeholder="Nombre de usuario" maxlength="16">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Contraseña</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="password" id="txtPassword" placeholder="Contraseña" name="passwd" minlength="8" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe contener al menos un número y una letra mayúscula y minúscula, y al menos 8 o más caracteres">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Confirmación de la Contraseña</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="password" id="txtConfirmPassword" placeholder="Repita contraseña" name="passwd2" minlength="8" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe contener al menos un número y una letra mayúscula y minúscula, y al menos 8 o más caracteres">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Nombre</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="text" placeholder="Nombre" name="realName" maxlength="20">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Apellido</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="text" placeholder="Apellido" name="surName"maxlength="20">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="email" placeholder="Email" name="email" maxlength="50">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Edad</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="number" placeholder="Edad" name="age" min="12" max="100">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Descripción</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="text" placeholder="Descripción" name="description" maxlength="200">
                        </div>
                       </div>
                        <div class=" form-group row">
                        <label for="lastName" class="col-sm-2 col-form-label">Foto de perfil</label>
                        <div class="col-sm-7">
                         <input class="form-control" type="url" placeholder="(Opcional) Url foto perfil" name="photo" pattern="https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,4}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)(.jpg|.png)">
                        </div>
                       </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a href="/webapp1/" class="btn btn-link">Iniciar Sesión</a>
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