// autor: gabriel0llerena@gmail.com/Gabriel-Spartan
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;
import util.ConexionBD;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (ID_USU, NOM_USU, APE_USU, CON_USU) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getIdUsu());
            ps.setString(2, usuario.getNomUsu());
            ps.setString(3, usuario.getApeUsu());
            ps.setString(4, usuario.getConUsu()); // En producción deberías cifrarla

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean existeUsuario(String idUsu) {
        String sql = "SELECT 1 FROM USUARIOS WHERE ID_USU = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idUsu);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // Retorna true si hay coincidencia

        } catch (SQLException e) {
            System.err.println("❌ Error al verificar existencia: " + e.getMessage());
            return false;
        }
    }

    public Usuario obtenerPorCedula(String cedula) {
        String sql = "SELECT * FROM USUARIOS WHERE ID_USU = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getString("ID_USU"),
                    rs.getString("NOM_USU"),
                    rs.getString("APE_USU"),
                    rs.getString("CON_USU")
                );
            }

        } catch (Exception e) {
            System.err.println("❌ Error al obtener usuario por cédula: " + e.getMessage());
        }

        return null;
    }
    
}
