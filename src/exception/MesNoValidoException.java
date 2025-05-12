package exception;

public class MesNoValidoException extends ExcepcionVista {
    public MesNoValidoException() {
        super("Mes no válido", "Selecciona un mes entre 1 y 12.");
    }
}