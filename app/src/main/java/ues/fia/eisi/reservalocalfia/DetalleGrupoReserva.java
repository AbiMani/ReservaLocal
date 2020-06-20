package ues.fia.eisi.reservalocalfia;

public class DetalleGrupoReserva {
    Integer idHorario, idroldocente;
    String codigoAsignatura, codigoCiclo, carnetDocente, idGrupo, codigoLocal;

    public  DetalleGrupoReserva(){

    }

    public  DetalleGrupoReserva(Integer idHorario,Integer idroldocente,String codigoAsignatura, String codigoCiclo, String carnetDocente, String idGrupo, String codigoLocal){
        this.idHorario=idHorario;
        this.idroldocente = idroldocente;
        this.codigoAsignatura = codigoAsignatura;
        this.codigoCiclo= codigoCiclo;
        this.carnetDocente= carnetDocente;
        this.idGrupo= idGrupo;
        this.codigoLocal= codigoLocal;

    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public Integer getIdroldocente() {
        return idroldocente;
    }

    public String getCarnetDocente() {
        return carnetDocente;
    }

    public String getCodigoCiclo() {
        return codigoCiclo;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setCarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }

    public void setCodigoCiclo(String codigoCiclo) {
        this.codigoCiclo = codigoCiclo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public void setIdroldocente(Integer idroldocente) {
        this.idroldocente = idroldocente;
    }
}
