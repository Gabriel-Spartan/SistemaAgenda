package controller;

import dao.LoginDAO;
import java.util.List;

public class LoginController {

    private final LoginDAO loginDAO;

    public LoginController() {
        loginDAO = new LoginDAO();
    }

    public String validarCredenciales(String cedula, String password) {
        if (cedula.length() != 10 || !cedula.matches("\\d+")) {
            return "❌ La cédula debe tener 10 dígitos.";
        }

        boolean valido = loginDAO.validarUsuario(cedula, password);
        return valido ? "✅ Bienvenido al sistema" : "❌ Usuario o contraseña incorrecta";
    }
    
    public String iniciarSesion(String cedula, String contrasena) {
        dao.UsuarioDAO usuarioDAO = new dao.UsuarioDAO();
        dao.EventoDAO eventoDAO = new dao.EventoDAO();

        model.Usuario usuario = usuarioDAO.obtenerPorCedula(cedula);
        if (usuario == null || !usuario.getConUsu().equals(contrasena)) {
            return "❌ Credenciales inválidas.";
        }

        List<model.Evento> eventos = eventoDAO.obtenerEventosPorUsuario(cedula);
        session.UsuarioActivo.iniciarSesion(usuario, eventos);
        return "✅ Sesión iniciada correctamente";
    }
}
