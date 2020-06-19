package ues.fia.eisi.reservalocalfia;

public class Encargado {
    private String idEncargadoLocal;
    private String nomEncargadoLocal;
    private String apeEncargadoLocal;
    public Encargado() {

    }

    public String getIdEncargadoLocal() {
        return idEncargadoLocal;
    }

    public void setIdEncargadoLocal(String idEncargadoLocal) {
        this.idEncargadoLocal = idEncargadoLocal;
    }



    public Encargado(String idEncargadoLocal, String nomEncargadoLocal, String apeEncargadoLocal) {
        this.idEncargadoLocal = idEncargadoLocal;
        this.nomEncargadoLocal = nomEncargadoLocal;
        this.apeEncargadoLocal = apeEncargadoLocal;
    }

    public String getNomEncargadoLocal() {
        return nomEncargadoLocal;
    }

    public void setNomEncargadoLocal(String nomEncargadoLocal) {
        this.nomEncargadoLocal = nomEncargadoLocal;
    }



    public String getApeEncargadoLocal() {
        return apeEncargadoLocal;
    }

    public void setApeEncargadoLocal(String apeEncargadoLocal) {
        this.apeEncargadoLocal = apeEncargadoLocal;
    }



}

