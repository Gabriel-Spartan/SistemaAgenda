// autor: gabriel0llerena@gmail.com/Gabriel-Spartan
package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class RegistroController {

    private final UsuarioDAO usuarioDAO;

    public RegistroController() {
        usuarioDAO = new UsuarioDAO();
    }

    public String registrarUsuario(Usuario u) {
        // Validación básica
        if (u.getIdUsu().isEmpty() || u.getNomUsu().isEmpty()
                || u.getApeUsu().isEmpty() || u.getConUsu().isEmpty()) {
            return "❌ Todos los campos son obligatorios";
        }

        if (usuarioDAO.existeUsuario(u.getIdUsu())) {
            return "⚠️ El usuario ya existe";
        }

        boolean registrado = usuarioDAO.registrarUsuario(u);
        return registrado ? "✅ Usuario registrado correctamente" : "❌ Error al registrar en la base de datos";
    }

    public String validarYRegistrar(Usuario u, String confirmacion) {
        if (u.getIdUsu().length() != 10 || !u.getIdUsu().matches("\\d+")) {
            return "❌ La cédula debe tener 10 dígitos numéricos.";
        }

        if (!u.getNomUsu().matches("[A-Z][a-záéíóúñ]+") || !u.getApeUsu().matches("[A-Z][a-záéíóúñ]+")) {
            return "❌ Nombre y Apellido deben empezar con mayúscula y solo contener letras.";
        }

        if (!u.getConUsu().equals(confirmacion)) {
            return "⚠️ Las contraseñas no coinciden.";
        }

        if (usuarioDAO.existeUsuario(u.getIdUsu())) {
            return "⚠️ El usuario ya está registrado.";
        }

        return usuarioDAO.registrarUsuario(u)
                ? "✅ Usuario registrado exitosamente."
                : "❌ Ocurrió un error al registrar.";
    }

}
