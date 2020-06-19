package ues.fia.eisi.reservalocalfia;

public class Encargado {
    public Encargado() {

    }

    public String getIdEncargadoLocal() {
        return idEncargadoLocal;
    }

    public void setIdEncargadoLocal(String idEncargadoLocal) {
        this.idEncargadoLocal = idEncargadoLocal;
    }

    private String idEncargadoLocal;

    public Encargado(String idEncargadoLocal, String nomEncargadoLocal, String apeEncargadoLocal) {
        this.idEncargadoLocal = idEncargadoLocal;
        nomEncargadoLocal = nomEncargadoLocal;
        apeEncargadoLocal = apeEncargadoLocal;
    }

    public String getNomEncargadoLocal() {
        return nomEncargadoLocal;
    }

    public void setNomEncargadoLocal(String nomEncargadoLocal) {
        nomEncargadoLocal = nomEncargadoLocal;
    }

    private String nomEncargadoLocal;

    public String getApeEncargadoLocal() {
        return apeEncargadoLocal;
    }

    public void setApeEncargadoLocal(String apeEncargadoLocal) {
        apeEncargadoLocal = apeEncargadoLocal;
    }

    private String apeEncargadoLocal;

}

