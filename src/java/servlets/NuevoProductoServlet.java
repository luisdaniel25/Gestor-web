package servlets;

import dao.ProductoDAO;
import model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productos/nuevo")
public class NuevoProductoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/productos/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = 0d;
        int cantidad = 0;
        try {
            precio = Double.parseDouble(request.getParameter("precio"));
        } catch (Exception e) {
            /* manejar si viene vacío */ }
        try {
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
        } catch (Exception e) {
            /* manejar si viene vacío */ }

        Producto p = new Producto(nombre, descripcion, precio, cantidad);
        dao.agregar(p);
        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
