<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Producto" %>
<%@ page import="java.util.List" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Productos - GestorWebCRUD</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2>Productos</h2>
                <a href="${pageContext.request.contextPath}/productos?action=nuevo" class="btn btn-primary">
                    <i class="bi bi-plus-circle me-1"></i> Nuevo Producto
                </a>
            </div>

            <table class="table table-hover bg-white shadow-sm rounded">
                <thead class="table-primary">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (productos != null) {
                            for (Producto p : productos) {%>
                    <tr>
                        <td><%= p.getId()%></td>
                        <td><%= p.getNombre()%></td>
                        <td><%= p.getDescripcion()%></td>
                        <td>$<%= p.getPrecio()%></td>
                        <td><%= p.getCantidad()%></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/productos?action=editar&id=<%=p.getId()%>" class="btn btn-sm btn-warning">
                                <i class="bi bi-pencil-square"></i>
                            </a>
                            <a href="${pageContext.request.contextPath}/productos?action=eliminar&id=<%=p.getId()%>" class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Eliminar este producto?');">
                                <i class="bi bi-trash"></i>
                            </a>
                        </td>
                    </tr>
                    <%   }
                    } else { %>
                    <tr>
                        <td colspan="6" class="text-center">No hay productos registrados</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
