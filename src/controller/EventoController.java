package controller;

import dao.EventoDAO;
import model.Evento;
import java.util.List;

public class EventoController {

    private EventoDAO eventoDAO;

    public EventoController() {
        this.eventoDAO = new EventoDAO();
    }

    /** Valida y persiste un evento. */
    public boolean crearEvento(Evento evento) {
        // Validaciones básicas
        if (evento.getTitEve().isEmpty()
         || evento.getDesEve().isEmpty()
         || evento.getFecEve() == null
         || evento.getHorEve() == null) {
            return false;
        }
        // (aquí puedes agregar más checks: fecha/hora válidas, no duplicados, etc.)
        return eventoDAO.insertarEvento(evento);
    }

    /** Devuelve la lista actualizada de eventos para un usuario. */
    public List<Evento> listarEventos(String idUsuario) {
        return eventoDAO.obtenerEventosPorUsuario(idUsuario);
    }
}