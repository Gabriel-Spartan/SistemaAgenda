// autor: gabriel0llerena@gmail.com/Gabriel-Spartan
package model;

import java.sql.Date;
import java.sql.Time;

public class Evento {
    private String idUsuPer;  // FK a USUARIOS(ID_USU)
    private Date fecEve;
    private Time horEve;
    private String titEve;
    private String desEve;

    public Evento() {}

    public Evento(String idUsuPer, Date fecEve, Time horEve, String titEve, String desEve) {
        this.idUsuPer = idUsuPer;
        this.fecEve = fecEve;
        this.horEve = horEve;
        this.titEve = titEve;
        this.desEve = desEve;
    }

    public String getIdUsuPer() {
        return idUsuPer;
    }

    public void setIdUsuPer(String idUsuPer) {
        this.idUsuPer = idUsuPer;
    }

    public Date getFecEve() {
        return fecEve;
    }

    public void setFecEve(Date fecEve) {
        this.fecEve = fecEve;
    }

    public Time getHorEve() {
        return horEve;
    }

    public void setHorEve(Time horEve) {
        this.horEve = horEve;
    }

    public String getTitEve() {
        return titEve;
    }

    public void setTitEve(String titEve) {
        this.titEve = titEve;
    }

    public String getDesEve() {
        return desEve;
    }

    public void setDesEve(String desEve) {
        this.desEve = desEve;
    }
}
