package ues.fia.eisi.reservalocalfia;



public class Horario {
    private int idHorario;
    private String idDia;
    private String horaInicio;
    private String horaFin;

    public Horario(){}

    public Horario(int idHorario,String idDia, String horaInicio, String horaFin){
        this.idHorario = idHorario;
        this.idDia = idDia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getidHorario() {
        return idHorario;
    }

    public void setidHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String gethoraInicio() {
        return horaInicio;
    }

    public void sethoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getidDia() {
        return idDia;
    }

    public void setidDia(String idDia) {
        this.idDia = idDia;
    }

    public String gethoraFin() {
        return horaFin;
    }

    public void sethoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
