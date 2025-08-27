package servlets;

import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Producto;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {

    ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "listar" : req.getParameter("action");

        switch (action) {
            case "listar":
                List<Producto> lista = dao.listar();
                req.setAttribute("productos", lista);
                req.getRequestDispatcher("/productos/list.jsp").forward(req, resp);
                break;

            case "nuevo":
                req.getRequestDispatcher("/productos/form.jsp").forward(req, resp);
                break;

            case "editar":
                int id = Integer.parseInt(req.getParameter("id"));
                Producto p = dao.obtenerPorId(id);
                req.setAttribute("producto", p);
                req.getRequestDispatcher("/productoseditar.jsp").forward(req, resp);
                break;

            case "eliminar":
                dao.eliminar(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("ProductoServlet?action=listar");
                break;

            default:
                resp.sendRedirect("ProductoServlet?action=listar");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/productos");
            return;
        }

        switch (accion) {
            case "agregar":
                Producto nuevo = new Producto(
                        request.getParameter("nombre"),
                        request.getParameter("descripcion"),
                        Double.parseDouble(request.getParameter("precio")),
                        Integer.parseInt(request.getParameter("cantidad"))
                );
                dao.agregar(nuevo);
                break;

            case "actualizar":
                Producto actualizar = new Producto(
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("nombre"),
                        request.getParameter("descripcion"),
                        Double.parseDouble(request.getParameter("precio")),
                        Integer.parseInt(request.getParameter("cantidad"))
                );
                dao.actualizar(actualizar);
                break;
        }
        response.sendRedirect(request.getContextPath() + "/productos");

    }
}
