package model;

import java.sql.Date;
import java.sql.Time;

public class Evento {

    private String idUsuPer;   // Clave foránea a USUARIOS(ID_USU)
    private Date fecEve;       // Fecha del evento
    private Time horEve;       // Hora del evento
    private String titEve;     // Título del evento
    private String desEve;     // Descripción del evento

    // Constructor vacío (obligatorio para frameworks o herramientas como JasperReports)
    public Evento() {}

    // Constructor completo
    public Evento(String idUsuPer, Date fecEve, Time horEve, String titEve, String desEve) {
        this.idUsuPer = idUsuPer;
        this.fecEve = fecEve;
        this.horEve = horEve;
        this.titEve = titEve;
        this.desEve = desEve;
    }

    // Getters y Setters
    public String getIdUsuPer() {
        return this.idUsuPer;
    }

    public void setIdUsuPer(String idUsuPer) {
        this.idUsuPer = idUsuPer;
    }

    public Date getFecEve() {
        return this.fecEve;
    }

    public void setFecEve(Date fecEve) {
        this.fecEve = fecEve;
    }

    public Time getHorEve() {
        return this.horEve;
    }

    public void setHorEve(Time horEve) {
        this.horEve = horEve;
    }

    public String getTitEve() {
        return this.titEve;
    }

    public void setTitEve(String titEve) {
        this.titEve = titEve;
    }

    public String getDesEve() {
        return this.desEve;
    }

    public void setDesEve(String desEve) {
        this.desEve = desEve;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "Usuario='" + this.idUsuPer + '\'' +
                ", Fecha=" + this.fecEve +
                ", Hora=" + this.horEve +
                ", Título='" + this.titEve + '\'' +
                ", Descripción='" + this.desEve + '\'' +
                '}';
    }
}
