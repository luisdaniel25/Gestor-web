package servlets;

import dao.ProductoDAO;
import model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

// iText 5
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/PdfCertificadoServlet")
public class PdfCertificadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"productos_certificado.pdf\"");

        List<Producto> productos = dao.listar();

        Document document = new Document(PageSize.LETTER, 36, 36, 54, 36);
        try (OutputStream out = response.getOutputStream()) {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 11);

            Paragraph title = new Paragraph("Certificado / Resumen de Productos", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(16f);
            document.add(title);

            // Tabla: ID, Nombre, Precio, Cantidad
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 4f, 2f, 2f});

            // Encabezados
            table.addCell(new Phrase("ID", headerFont));
            table.addCell(new Phrase("Nombre", headerFont));
            table.addCell(new Phrase("Precio", headerFont));
            table.addCell(new Phrase("Cantidad", headerFont));

            // Filas
            for (Producto p : productos) {
                table.addCell(new Phrase(String.valueOf(p.getId()), normalFont));
                table.addCell(new Phrase(p.getNombre(), normalFont));
                table.addCell(new Phrase(String.format("$ %.2f", p.getPrecio()), normalFont));
                table.addCell(new Phrase(String.valueOf(p.getCantidad()), normalFont));
            }

            document.add(table);

            Paragraph footer = new Paragraph("\nEmitido automáticamente por GestorWebCRUD", normalFont);
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);

            document.close();
            out.flush();
        } catch (DocumentException de) {
            de.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generando PDF");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inesperado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
