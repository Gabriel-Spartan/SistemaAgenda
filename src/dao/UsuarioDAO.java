package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Usuario;
import util.ConexionBD;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (ID_USU, NOM_USU, APE_USU, CON_USU) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

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
}
