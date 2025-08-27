<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Producto" %>
<%
    Producto producto = (Producto) request.getAttribute("producto");
    boolean editar = (producto != null);
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%= editar ? "Editar Producto" : "Nuevo Producto"%> - GestorWebCRUD</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                background-color: #f4f6f9;
            }
            .card {
                border-radius: 15px;
            }
            .card-header {
                background-color: #0d6efd;
                color: white;
                font-weight: bold;
                font-size: 1.2rem;
            }
            .btn-custom {
                border-radius: 50px;
            }
            .breadcrumb {
                background: none;
                padding-left: 0;
            }
        </style>
    </head>
    <body>

        <!-- Header -->
        <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
            <div class="container">
                <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/index.jsp">
                    <i class="bi bi-box-seam me-2"></i>GestorWebCRUD
                </a>
            </div>
        </nav>

        <div class="container mt-5">
            <!-- Breadcrumb -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/productos">Productos</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><%= editar ? "Editar" : "Nuevo"%> Producto</li>
                </ol>
            </nav>

            <!-- Formulario -->
            <div class="card shadow-sm mx-auto" style="max-width: 700px;">
                <div class="card-header text-center">
                    <i class="bi bi-pencil-square me-2"></i> <%= editar ? "Editar Producto" : "Nuevo Producto"%>
                </div>
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/productos" method="post">
                        <% if (editar) {%>
                        <input type="hidden" name="accion" value="actualizar" />
                        <input type="hidden" name="id" value="<%= producto.getId()%>" />
                        <% } else { %>
                        <input type="hidden" name="accion" value="agregar" />
                        <% }%>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Nombre</label>
                            <input type="text" name="nombre" class="form-control" placeholder="Ej: Laptop Dell" required
                                   value="<%= editar && producto.getNombre() != null ? producto.getNombre() : ""%>" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Descripción</label>
                            <textarea name="descripcion" class="form-control" rows="3" placeholder="Descripción breve del producto"><%= editar && producto.getDescripcion() != null ? producto.getDescripcion() : ""%></textarea>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Precio</label>
                                <input type="number" step="0.01" name="precio" class="form-control" placeholder="0.00" required
                                       value="<%= editar ? producto.getPrecio() : ""%>" />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Cantidad</label>
                                <input type="number" name="cantidad" class="form-control" placeholder="0" required
                                       value="<%= editar ? producto.getCantidad() : ""%>" />
                            </div>
                        </div>

                        <div class="d-flex justify-content-between mt-4">
                            <a href="${pageContext.request.contextPath}/productos" class="btn btn-outline-secondary btn-custom">
                                <i class="bi bi-arrow-left-circle me-1"></i> Cancelar
                            </a>
                            <button type="submit" class="btn btn-primary btn-custom">
                                <i class="bi <%= editar ? "bi-check-circle" : "bi-save"%> me-1"></i> 
                                <%= editar ? "Actualizar" : "Guardar"%>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
