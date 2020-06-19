package ues.fia.eisi.reservalocalfia;

public class Local {
    private String codigoLocal;
    private String idEncargadoLocal;
    private String ubicacionLocal;
    private int capacidadLocal;
    private String idTipoLocal;
    public Local() {

    }
    public Local(String codigoLocal, String idEncargadoLocal, String idTipoLocal, String ubicacionLocal, Integer capacidadLocal) {
        this.codigoLocal = codigoLocal;
        this.idEncargadoLocal = idEncargadoLocal;
        this.idTipoLocal = idTipoLocal;
        this.ubicacionLocal = ubicacionLocal;
        this.capacidadLocal = capacidadLocal;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public String getIdEncargadoLocal() {
        return idEncargadoLocal;
    }

    public void setIdEncargadoLocal(String idEncargadoLocal) {
        this.idEncargadoLocal = idEncargadoLocal;
    }

    public String getUbicacionLocal() {
        return ubicacionLocal;
    }

    public void setUbicacionLocal(String ubicacionLocal) {
        this.ubicacionLocal = ubicacionLocal;
    }

    public int getCapacidadLocal() {
        return capacidadLocal;
    }

    public void setCapacidadLocal(int capacidadLocal) {
        this.capacidadLocal = capacidadLocal;
    }

    public String getIdTipoLocal() {
        return idTipoLocal;
    }

    public void setIdTipoLocal(String idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
    }
}
