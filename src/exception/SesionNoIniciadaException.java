package exception;

public class SesionNoIniciadaException extends ExcepcionVista {
    public SesionNoIniciadaException() {
        super("Sesión no iniciada", "No hay un usuario autenticado en la sesión.");
    }
}
