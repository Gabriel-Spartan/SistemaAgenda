package exception;

public abstract class ExcepcionVista extends RuntimeException {

    private final String titulo;

    public ExcepcionVista(String titulo, String mensaje) {
        super(mensaje);
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
