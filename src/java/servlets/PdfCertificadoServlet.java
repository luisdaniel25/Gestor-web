package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// ====== iText (PDF) ======
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ProductoDAO;
import java.io.OutputStream;
import java.util.List;
import model.Producto;

/**
 *
 * @author Luis Daniel
 */
@WebServlet(name = "PdfCertificadoServlet", urlPatterns = {"/PdfCertificadoServlet"})
public class PdfCertificadoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PdfCertificadoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PdfCertificadoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=productos_certificado.pdf");

        try {
            // Crear documento PDF
            Document document = new Document(PageSize.LETTER, 50, 50, 60, 60);
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();

            // Fuente básica
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            // Título
            Paragraph titulo = new Paragraph("Certificado / Resumen de Productos", titleFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Tabla de productos
            PdfPTable table = new PdfPTable(4); // 4 columnas
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 3f, 2f, 1f});

            // Encabezados
            table.addCell(new Phrase("ID", normalFont));
            table.addCell(new Phrase("Nombre", normalFont));
            table.addCell(new Phrase("Precio", normalFont));
            table.addCell(new Phrase("Cantidad", normalFont));

            // ✅ Instanciar el DAO antes de usarlo
            ProductoDAO dao = new ProductoDAO();
            List<Producto> productos = dao.listar();

            // Llenar la tabla
            for (Producto p : productos) {
                table.addCell(String.valueOf(p.getId()));
                table.addCell(p.getNombre());
                table.addCell(String.format("$ %.2f", p.getPrecio()));
                table.addCell(String.valueOf(p.getCantidad()));
            }

            document.add(table);

            // Pie de página
            Paragraph footer = new Paragraph("\n\nEmitido automáticamente por el sistema CRUD", normalFont);
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);

            document.close();
            out.close();

        } catch (DocumentException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generando PDF");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Genera un PDF con el listado de productos";
    }// </editor-fold>

}
