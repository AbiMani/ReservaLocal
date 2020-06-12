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
        NomEncargadoLocal = nomEncargadoLocal;
        ApeEncargadoLocal = apeEncargadoLocal;
    }

    public String getNomEncargadoLocal() {
        return NomEncargadoLocal;
    }

    public void setNomEncargadoLocal(String nomEncargadoLocal) {
        NomEncargadoLocal = nomEncargadoLocal;
    }

    private String NomEncargadoLocal;

    public String getApeEncargadoLocal() {
        return ApeEncargadoLocal;
    }

    public void setApeEncargadoLocal(String apeEncargadoLocal) {
        ApeEncargadoLocal = apeEncargadoLocal;
    }

    private String ApeEncargadoLocal;

}

