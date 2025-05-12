package exception;

public class AnioNoValidoException extends ExcepcionVista {

    public AnioNoValidoException() {
        super("Año no válido", "Por favor ingresa un año válido.");
    }
}
