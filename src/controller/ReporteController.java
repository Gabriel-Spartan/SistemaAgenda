package controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import reportes.Reporte;
import session.UsuarioActivo;
import exception.FechaNoValidaException;
import exception.SesionNoIniciadaException;

public class ReporteController {

    public static void generarPorDia(java.util.Date fecha) {
        if (fecha == null) {
            throw new FechaNoValidaException();
        }
        if (!UsuarioActivo.haySesionActiva()) {
            throw new SesionNoIniciadaException();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fecha", new Date(fecha.getTime()));
        parametros.put("usuario_id", UsuarioActivo.getUsuarioActual().getIdUsu());

        Reporte.mostrarReporte("src/reportes/reporteDia.jasper", parametros);
    }

    public static void generarPorMes(int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new exception.MesNoValidoException();
        }

        if (anio < 2000 || anio > 2100) {
            throw new exception.AnioNoValidoException();
        }

        if (!UsuarioActivo.haySesionActiva()) {
            throw new exception.SesionNoIniciadaException();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("mes", mes);
        parametros.put("anio", anio);
        parametros.put("usuario_id", UsuarioActivo.getUsuarioActual().getIdUsu());

        Reporte.mostrarReporte("src/reportes/reporteMes.jasper", parametros);
    }

    public static void graficoPorDia(java.util.Date fecha) {
        if (fecha == null) {
            throw new FechaNoValidaException();
        }
        if (!UsuarioActivo.haySesionActiva()) {
            throw new SesionNoIniciadaException();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fecha", new Date(fecha.getTime()));
        parametros.put("usuario_id", UsuarioActivo.getUsuarioActual().getIdUsu());

        Reporte.mostrarReporte("src/reportes/graficoDia.jasper", parametros);
    }
    
 public static void graficoPorMes(int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new exception.MesNoValidoException();
        }

        if (anio < 2000 || anio > 2100) {
            throw new exception.AnioNoValidoException();
        }

        if (!UsuarioActivo.haySesionActiva()) {
            throw new exception.SesionNoIniciadaException();
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("mes", mes);
        parametros.put("anio", anio);
        parametros.put("usuario_id", UsuarioActivo.getUsuarioActual().getIdUsu());

        Reporte.mostrarReporte("src/reportes/graficoMes.jasper", parametros);
    }    

}
