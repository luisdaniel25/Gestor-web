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

// Apache POI
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/ExcelExportServlet")
public class ExcelExportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> productos = dao.listar();

        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"productos.xlsx\"");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Productos");

            // Estilos encabezado
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Header row
            Row header = sheet.createRow(0);
            String[] cols = {"ID", "Nombre", "Descripción", "Precio", "Cantidad"};
            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(headerStyle);
            }

            // Filas de datos
            int rowIdx = 1;
            for (Producto p : productos) {
                Row r = sheet.createRow(rowIdx++);
                r.createCell(0).setCellValue(p.getId());
                r.createCell(1).setCellValue(p.getNombre());
                r.createCell(2).setCellValue(p.getDescripcion());
                r.createCell(3).setCellValue(p.getPrecio());
                r.createCell(4).setCellValue(p.getCantidad());
            }

            // Auto size
            for (int i = 0; i < cols.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir al output
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error exportando Excel: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
