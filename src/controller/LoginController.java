/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author USUARIO
 */

import dao.LoginDAO;

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
}
