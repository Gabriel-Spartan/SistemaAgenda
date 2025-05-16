/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author USUARIO
 */

import java.sql.*;
import util.ConexionBD;

public class LoginDAO {

    public boolean validarUsuario(String cedula, String password) {
        String sql = "SELECT 1 FROM USUARIOS WHERE ID_USU = ? AND CON_USU = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("❌ Error en login: " + e.getMessage());
            return false;
        }
    }
}
