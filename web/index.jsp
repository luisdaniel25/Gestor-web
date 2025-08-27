<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>GestorWebCRUD - Sistema de Gestión de Productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <!-- Estilos omitidos por brevedad -->
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container">
                <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/index.jsp">
                    <i class="fas fa-cubes me-2"></i>GestorWebCRUD
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/productos/list.jsp">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/productos/form.jsp">Agregar Producto</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="fas fa-user me-1"></i> Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <div class="main-container">
            <div class="container">
                <!-- Hero Section -->
                <div class="hero-section text-center">
                    <h1 class="display-4 fw-bold mb-4">Sistema de Gestión de Productos</h1>
                    <p class="lead mb-4">GestorWebCRUD es una aplicación web para administrar tu inventario de productos de manera eficiente y sencilla.</p>
                    <a href="${pageContext.request.contextPath}/productos/list.jsp" class="btn btn-primary btn-lg">
                        <i class="fas fa-box-open me-2"></i>Gestionar Productos
                    </a>
                </div>

                <!-- Features Section -->
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="feature-card p-4 text-center">
                            <div class="feature-icon">
                                <i class="fas fa-list"></i>
                            </div>
                            <h3>Gestión Completa</h3>
                            <p>Administra todos tus productos en un solo lugar. Crea, lee, actualiza y elimina productos fácilmente.</p>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="feature-card p-4 text-center">
                            <div class="feature-icon">
                                <i class="fas fa-file-pdf"></i>
                            </div>
                            <h3>Reportes PDF</h3>
                            <p>Genera certificados y reportes en formato PDF para tus productos con solo un clic.</p>
                            <a href="${pageContext.request.contextPath}/PdfCertificadoServlet" class="btn btn-outline-danger mt-2">
                                <i class="fas fa-download me-1"></i> Generar PDF
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="feature-card p-4 text-center">
                            <div class="feature-icon">
                                <i class="fas fa-file-excel"></i>
                            </div>
                            <h3>Exportación a Excel</h3>
                            <p>Exporta tus datos de productos a Excel para análisis avanzado y procesamiento externo.</p>
                            <a href="${pageContext.request.contextPath}/ExcelExportServlet" class="btn btn-outline-success mt-2">
                                <i class="fas fa-download me-1"></i> Exportar Excel
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer class="footer text-center">
            <div class="container">
                <p class="mb-0">© 2023 GestorWebCRUD - Sistema de Gestión de Productos</p>
                <p class="text-muted">Desarrollado con Java Servlets, JSP y MySQL</p>
            </div>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
