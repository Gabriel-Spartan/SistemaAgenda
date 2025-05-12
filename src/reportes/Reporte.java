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

    public static void mostrarReporte(String rutaRelativa, Map<String, Object> parametros) {
        try {
            File file = new File(rutaRelativa);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "❌ Reporte no encontrado:\n" + file.getAbsolutePath());
                return;
            }

            try (InputStream input = new FileInputStream(file); Connection conn = ConexionBD.conectar()) {

                JasperReport reporte = (JasperReport) JRLoader.loadObject(input);
                JasperPrint print = JasperFillManager.fillReport(reporte, parametros, conn);

                if (print.getPages().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El reporte no contiene información para los criterios seleccionados.", "Reporte vacío", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JasperViewer.viewReport(print, false);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "❌ Error al generar el reporte:\n" + e.getMessage());
        }
    }

}
