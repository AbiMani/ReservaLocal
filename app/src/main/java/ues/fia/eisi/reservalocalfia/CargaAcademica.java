package ues.fia.eisi.reservalocalfia;

public class CargaAcademica {
    private String idRolDocente;
    private String codigoCiclo;
    private String carnetDocente;
    private String codigoAsignatura;

    public CargaAcademica(){}

    public CargaAcademica(String idRolDocente, String codigoAsignatura, String codigoCiclo, String carnetDocente){
        this.idRolDocente = idRolDocente;
        this.codigoAsignatura = codigoAsignatura;
        this.codigoCiclo = codigoCiclo;
        this.carnetDocente = carnetDocente;
    }

    public String getidRolDocente() {
        return idRolDocente;
    }

    public void setidRolDocente(String idRolDocente) {
        this.idRolDocente = idRolDocente;
    }

    public String getcarnetDocente() {
        return carnetDocente;
    }

    public void setcarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }

    public String getcodigoCiclo() {
        return codigoCiclo;
    }

    public void setcodigoCiclo(String codigoCiclo) {
        this.codigoCiclo = codigoCiclo;
    }

    public String getcodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setcodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

}
