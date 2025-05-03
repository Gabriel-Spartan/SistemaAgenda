// autor: gabriel0llerena@gmail.com/Gabriel-Spartan
package model;

public class Usuario {

    private String idUsu;
    private String nomUsu;
    private String apeUsu;
    private String conUsu;

    public Usuario() {
    }

    public Usuario(String idUsu, String nomUsu, String apeUsu, String conUsu) {
        this.idUsu = idUsu;
        this.nomUsu = nomUsu;
        this.apeUsu = apeUsu;
        this.conUsu = conUsu;
    }

    public String getIdUsu() {
        return this.idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    public String getNomUsu() {
        return this.nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getApeUsu() {
        return this.apeUsu;
    }

    public void setApeUsu(String apeUsu) {
        this.apeUsu = apeUsu;
    }

    public String getConUsu() {
        return this.conUsu;
    }

    public void setConUsu(String conUsu) {
        this.conUsu = conUsu;
    }
}
