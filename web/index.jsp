<%-- 
    Document   : index
    Created on : 26/08/2025
    Author     : Luis Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestor Web CRUD</title>
        <!-- Bootstrap 5 CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow">
            <div class="container">
                <a class="navbar-brand fw-bold" href="index.jsp">Gestor CRUD</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link active" href="index.jsp">Inicio</a></li>
                        <li class="nav-item"><a class="nav-link" href="Controlador?accion=listar">Ver Registros</a></li>
                        <li class="nav-item"><a class="nav-link" href="Controlador?accion=nuevo">Nuevo Registro</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Reportes</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Hero -->
        <header class="bg-white py-5 shadow-sm">
            <div class="container text-center">
                <h1 class="display-5 fw-bold text-primary">Bienvenido a tu Gestor Web CRUD</h1>
                <p class="lead text-muted">Administra tus datos fácilmente con Servlets, JSP y JDBC</p>
                <a href="Controlador?accion=listar" class="btn btn-primary btn-lg mt-3">Comenzar</a>
            </div>
        </header>

        <!-- Features -->
        <section class="container my-5">
            <div class="row text-center">
                <div class="col-md-4">
                    <div class="card shadow-sm border-0">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">📄 Listado</h5>
                            <p class="card-text">Visualiza todos los registros almacenados en la base de datos.</p>
                            <a href="Controlador?accion=listar" class="btn btn-outline-primary">Ver</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 mt-4 mt-md-0">
                    <div class="card shadow-sm border-0">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">➕ Agregar</h5>
                            <p class="card-text">Inserta nuevos registros en el sistema de manera sencilla.</p>
                            <a href="Controlador?accion=nuevo" class="btn btn-outline-success">Agregar</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 mt-4 mt-md-0">
                    <div class="card shadow-sm border-0">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">📊 Reportes</h5>
                            <p class="card-text">Genera reportes en PDF y exporta tus datos a Excel.</p>
                            <a href="#" class="btn btn-outline-warning">Generar</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Footer -->
        <footer class="bg-primary text-white text-center py-3 mt-5">
            <p class="mb-0">© 2025 - Gestor Web CRUD | Desarrollado con Servlets + JSP + JDBC</p>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
