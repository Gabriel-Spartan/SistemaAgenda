package reportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.ConexionBD;

public class Reporte {

    public static void mostrarReporte(String rutaRelativa, Map<String, Object> parametros) { // Método estático que muestra el reporte dado un archivo y parámetros
        try {
            File file = new File(rutaRelativa); // Crea un objeto File a partir de la ruta del archivo del reporte
            if (!file.exists()) { // Verifica si el archivo no existe
                JOptionPane.showMessageDialog(null, "❌ Reporte no encontrado:\n" + file.getAbsolutePath()); // Muestra mensaje si no se encuentra el archivo
                return; // Sale del método si no hay archivo
            }

            try (InputStream input = new FileInputStream(file); Connection conn = ConexionBD.conectar()) { // Abre flujo de entrada del archivo y conexión a BD

                JasperReport reporte = (JasperReport) JRLoader.loadObject(input); // Carga el archivo .jasper como objeto JasperReport
                JasperPrint print = JasperFillManager.fillReport(reporte, parametros, conn); // Llena el reporte con los datos de la BD y parámetros

                if (print.getPages().isEmpty()) { // Verifica si el reporte no tiene páginas (no hay datos)
                    JOptionPane.showMessageDialog(null, "El reporte no contiene información para los criterios seleccionados.", "Reporte vacío", JOptionPane.INFORMATION_MESSAGE); // Muestra mensaje si el reporte está vacío
                } else {
                    JasperViewer.viewReport(print, false); // Muestra el reporte al usuario sin cerrar la aplicación al cerrar el visor
                }

            }
        } catch (Exception e) { // Captura cualquier excepción durante el proceso
            e.printStackTrace(); // Muestra el error en consola
            JOptionPane.showMessageDialog(null, "❌ Error al generar el reporte:\n" + e.getMessage()); // Muestra el error al usuario
        }
    }

}