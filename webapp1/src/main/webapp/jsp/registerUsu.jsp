<%-- 
    Document   : registerUsu
    Created on : 20-feb-2022, 18:20:25
    Author     : defie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register user page</h1>
        <form action="/webapp1/servletUsuarios" method="post">
            <br><input type="text" placeholder="Username" name="username" maxlength="16" /></br>
            <br><input type="password" id="txtPassword" placeholder="Contraseña" name="passwd" minlength="8" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe contener al menos un número y una letra mayúscula y minúscula, y al menos 8 o más caracteres"/></br>
            <br><input type="password" id="txtConfirmPassword" placeholder="Repita contraseña" name="passwd2" minlength="8" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe contener al menos un número y una letra mayúscula y minúscula, y al menos 8 o más caracteres"/></br>
            <br><input type="text" placeholder="Nombre" name="realName" maxlength="20"/></br>
            <br><input type="text" placeholder="Apellido" name="surName"maxlength="20"/></br>
            <br><input type="email" placeholder="Email" name="email" maxlength="50"/></br>
            <br><input type="number" placeholder="Edad" name="age" min="12" max="100"/></br>
            <br><input type="text" placeholder="Descripción" name="description" maxlength="200"/></br>
            <br><input type="url" placeholder="(Opcional) Url foto perfil" name="photo" pattern="https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,4}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)(.jpg|.png)"/></br>
            <br></br>
            <br><input type="submit" value="Enviar"/></br>
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
        <br></br>
        <a href="/webapp1/">Iniciar Sesión</a>
    </body>
</html>