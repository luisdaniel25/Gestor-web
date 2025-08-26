<%-- 
    Document   : form
    Created on : 26/08/2025, 12:56:01 p. m.
    Author     : Luis Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor CRUD - Generar PDF</title>
    </head>
    <body>
        <h1>Gestor de Productos</h1>

        <p>Haz clic en el botón para generar un certificado PDF con la lista de productos:</p>

        <!-- Enlace directo al servlet -->
        <a href="PdfCertificadoServlet" target="_blank">
            <button>Generar PDF</button>
        </a>

        <br><br>

        <!-- También puedes hacerlo con un formulario -->
        <form action="PdfCertificadoServlet" method="get">
            <input type="submit" value="Descargar PDF desde Formulario">
        </form>
    </body>
</html>
