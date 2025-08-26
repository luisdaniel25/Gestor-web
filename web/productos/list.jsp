<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Producto" %>
<%@ page import="java.util.List" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Productos</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-4">
        <h1>Productos</h1>
        <a class="btn btn-primary mb-2" href="${pageContext.request.contextPath}/productos/nuevo">Nuevo Producto</a>
        <a class="btn btn-success mb-2" href="${pageContext.request.contextPath}/productos/exportarExcel">Exportar a Excel</a>
        <a class="btn btn-info mb-2" href="${pageContext.request.contextPath}/productos/pdf">Generar PDF (Certificados)</a>

        <table class="table table-striped">
            <thead><tr><th>ID</th><th>Nombre</th><th>Precio</th><th>Cantidad</th><th>Acciones</th></tr></thead>
            <tbody>
                <% for (Producto p : productos) {%>
                <tr>
                    <td><%= p.getId()%></td>
                    <td><%= p.getNombre()%></td>
                    <td><%= p.getPrecio()%></td>
                    <td><%= p.getCantidad()%></td>
                    <td>
                        <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/productos/editar?id=<%=p.getId()%>">Editar</a>
                        <form action="${pageContext.request.contextPath}/productos/eliminar" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%=p.getId()%>">
                            <button class="btn btn-sm btn-danger" type="submit" onclick="return confirm('Eliminar?')">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </body>
</html>
