package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Evento;
import util.ConexionBD;

public class EventoDAO {

    public List<Evento> obtenerEventosPorUsuario(String idUsuario) {
        List<Evento> lista = new ArrayList<>();

        String sql = "SELECT * FROM EVENTOS WHERE ID_USU_PER = ? ORDER BY FEC_EVE DESC, HOR_EVE DESC";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evento e = new Evento(
                    rs.getString("ID_USU_PER"),
                    rs.getDate("FEC_EVE"),
                    rs.getTime("HOR_EVE"),
                    rs.getString("TIT_EVE"),
                    rs.getString("DES_EVE")
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertarEvento(Evento evento) {
        String sql = "INSERT INTO EVENTOS (ID_USU_PER, FEC_EVE, HOR_EVE, TIT_EVE, DES_EVE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, evento.getIdUsuPer());
            ps.setDate(2, evento.getFecEve());
            ps.setTime(3, evento.getHorEve());
            ps.setString(4, evento.getTitEve());
            ps.setString(5, evento.getDesEve());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
