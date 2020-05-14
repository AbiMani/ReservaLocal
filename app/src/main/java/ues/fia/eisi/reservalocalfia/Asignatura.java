package ues.fia.eisi.reservalocalfia;

public class Asignatura {
    private String codigoAsignatura;
    private String codigoLocal;
    private String codigoEscuela;
    private Integer idPrioridad;
    private String nomAsignatura;

    public Asignatura(){}

    public Asignatura(String codigoAsignatura,String codigoLocal, String codigoEscuela, Integer idPrioridad, String nomAsignatura){
        this.codigoAsignatura = codigoAsignatura;
        this.codigoLocal = codigoLocal;
        this.codigoEscuela = codigoEscuela;
        this.idPrioridad = idPrioridad;
        this.nomAsignatura = nomAsignatura;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getCodigoEscuela() {
        return codigoEscuela;
    }

    public void setCodigoEscuela(String codigoEscuela) {
        this.codigoEscuela = codigoEscuela;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public String getNomAsignatura() {
        return nomAsignatura;
    }

    public void setNomAsignatura(String nomAsignatura) {
        this.nomAsignatura = nomAsignatura;
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }
}
