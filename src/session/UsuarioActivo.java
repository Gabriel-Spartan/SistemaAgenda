package session;

import java.util.List;
import model.Evento;
import model.Usuario;

public class UsuarioActivo {
    
    private static Usuario usuarioActual;
    private static List<Evento> eventosDelUsuario;
    //private static List<Evento> eventosDelUsuario;

    private UsuarioActivo() {}

    public static void iniciarSesion(Usuario usuario, List<Evento> eventos) {
        usuarioActual = usuario;
        eventosDelUsuario = eventos;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static List<Evento> getEventosDelUsuario() {
        return eventosDelUsuario;
    }

        public static void cerrarSesion() {
        usuarioActual = null;
        eventosDelUsuario = null;
    }

    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public static void actualizarEventos(List<Evento> nuevosEventos) {
        eventosDelUsuario = nuevosEventos;
    }
    
}
