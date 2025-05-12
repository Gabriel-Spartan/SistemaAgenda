package exception;

public class FechaNoValidaException extends ExcepcionVista {
    public FechaNoValidaException() {
        super("Fecha no válida", "Debes seleccionar una fecha antes de generar el reporte.");
    }
}
