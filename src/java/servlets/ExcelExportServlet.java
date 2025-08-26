package servlets;

import dao.ProductoDAO;
import model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
// ====== Apache POI ======
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(name = "ExcelExportServlet", urlPatterns = {"/ExcelExportServlet"})
public class ExcelExportServlet extends HttpServlet {

    private ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> productos = dao.listar();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Productos");

            // ==== ESTILOS DE ENCABEZADO ====
            CellStyle headerStyle = workbook.createCellStyle();
            Font hFont = workbook.createFont();
            hFont.setBold(true);
            headerStyle.setFont(hFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // ==== FILA DE ENCABEZADO ====
            Row header = sheet.createRow(0);
            String[] cols = {"ID", "Nombre", "Descripcion", "Precio", "Cantidad"};
            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(headerStyle);
            }

            // ==== FILAS CON PRODUCTOS ====
            int rowIdx = 1;
            for (Producto p : productos) {
                Row r = sheet.createRow(rowIdx++);
                r.createCell(0).setCellValue(p.getId());
                r.createCell(1).setCellValue(p.getNombre());
                r.createCell(2).setCellValue(p.getDescripcion());
                r.createCell(3).setCellValue(p.getPrecio());
                r.createCell(4).setCellValue(p.getCantidad());
            }

            // ==== AUTO-AJUSTAR ANCHO DE COLUMNAS ====
            for (int i = 0; i < cols.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // ==== ESCRIBIR AL OUTPUT STREAM ====
            workbook.write(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error exportando Excel: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Para que POST también exporte
    }

    @Override
    public String getServletInfo() {
        return "Exporta lista de productos a Excel con Apache POI";
    }
}
