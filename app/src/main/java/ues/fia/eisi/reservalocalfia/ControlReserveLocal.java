package ues.fia.eisi.reservalocalfia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ControlReserveLocal {

    //campos de las tablas de la base de datos
    private static final String[] camposAsignatura = new String[] {"codigoAsignatura","codigoLocal","codigoEscuela","nomAsignatura","idPrioridad"};
    private static final String[] camposCargaAcademica = new String[] {"idRolDocente","codigoAsignatura", "codigoCiclo","carnetDocente"};
    private static final String[] camposHorario = new String[] {"idHorario","idDia","horaInicio","horaFin"};
    private static final String[] camposDia = new String[] {"idDia","nomDia"};
    private static final String[] camposPri = new String[] {"idPrioridad","nivelPrioridad"};

    private static final String[]camposDetalleReserva = new String [] {"idhorario","idreservaevento","codigolocal"};
    private static final String[]camposReservaEvento = new String [] {"idreservaevento","codigoescuela","idtipoevento","nombreevento", "capacidadtotalevento", "fechareserva"};
    private static final String[] camposTipoEvento = new String [] {"idtipoEvento","nomtipoevento"};

    //campos de las tablas docente
    private static final String[]camposDocente = new String [] {"carnetDocente","nombreDocente","apellido", "rol", "nomEscuela"};
    private static final String[]camposRolDocente = new String [] {"idRolDocente","nomRolDocente"};
    private static final String[]camposTipoLocal = new String [] {"idTipoLocal","nomTipoLocal"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    private static final String DROP_TABLE1 ="DROP TABLE IF EXISTS detallereserva; ";
    private static final String DROP_TABLE2 ="DROP TABLE IF EXISTS reservaevento; ";
    private static final String DROP_TABLE3 ="DROP TABLE IF EXISTS tipoevento; ";

    public ControlReserveLocal(Context ctx) {
        this.context=ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "reserveLocal.s3db"; //nombre de la base de datos SQLite
        private static final int VERSION= 1;

        DatabaseHelper(Context context){
            super(context,BASE_DATOS,null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE asignatura(codigoAsignatura VARCHAR(6) NOT NULL PRIMARY KEY, codigoLocal VARCHAR(20),codigoEscuela VARCHAR(20),nomAsignatura VARCHAR(30),idPrioridad Varchar(1) not null);");
                db.execSQL("CREATE TABLE horario(idHorario INTEGER NOT NULL PRIMARY KEY,idDia VARCHAR(1),horaInicio VARCHAR(30),horaFin VARCHAR(30));");
                db.execSQL("CREATE TABLE cargaAcademica(idRolDocente VARCHAR(2) NOT NULL , codigoAsignatura VARCHAR(6) NOT NULL ,codigoCiclo VARCHAR(6) ,carnetDocente Varchar(7) ,PRIMARY KEY(codigoAsignatura,carnetDocente,codigoCiclo));");
                db.execSQL("CREATE TABLE dia(idDia VARCHAR(4) NOT NULL PRIMARY KEY,nomDia varchar(20) not null);");
                //db.execSQL("CREATE TABLE rolDocente(idRolDocente VARCHAR(2) NOT NULL PRIMARY KEY,nomRolDocente VARCHAR(20) not null);");
                db.execSQL("CREATE TABLE prioridad(idPrioridad VARCHAR(1) NOT NULL PRIMARY KEY, nivelPrioridad VARCHAR(20));");
                db.execSQL("create trigger fk_carga_asignatura before insert on cargaAcademica for each row begin select " +
                        "case when ((select codigoAsignatura from asignatura where codigoAsignatura=new.codigoAsignatura) is null)" +
                        " then raise(abort,'no puede insertar, no existe asignatura') end; end;");
             /**   db.execSQL("create trigger fk_carga_ciclo before insert on cargaAcademica for each row begin select " +
                        "case when ((select codigoCiclo from ciclo where codigoCiclo=new.codigoCiclo) is null)" +
                        " then raise(abort,'no puede insertar, no existe ciclo') end; end;");
                db.execSQL("create trigger fk_carga_docente before insert on cargaAcademica for each row begin select " +
                        "case when ((select carnetDocente from docente where carnetDocente=new.carnetDocente) is null)" +
                        " then raise(abort,'no puede insertar, no existe docente') end; end;");
                db.execSQL("create trigger fk_carga_roldocente before insert on cargaAcademica for each row begin select " +
                        "case when ((select idRolDocente from rolDocente where idRolDocente=new.idRolDocente) is null)" +
                        " then raise(abort,'no puede insertar, no existe rol docente') end; end;");
                db.execSQL("create trigger fk_asignatura_local before insert on asignatura for each row begin select " +
                        "case when ((select codigoLocal from local where codigoLocal=new.codigoLocal) is null)" +
                        " then raise(abort,'no puede insertar, no existe local') end; end;");
                db.execSQL("create trigger fk_asignatura_escuela before insert on asignatura for each row begin select " +
                        "case when ((select codigoEscuela from escuela where codigoEscuela=new.codigoEscuela) is null)" +
                        " then raise(abort,'no puede insertar, no existe escuela') end; end;"); **/
                db.execSQL("create trigger fk_asignatura_prioridad before insert on asignatura for each row begin select " +
                        "case when ((select idPrioridad from prioridad where idPrioridad=new.idPrioridad) is null)" +
                        " then raise(abort,'no puede insertar, no existe escuela') end; end;");
                db.execSQL("create trigger dependencias_asignatura after update of codigoAsignatura on asignatura begin " +
                        "update cargaAcademica set codigoAsignatura= new.codigoAsignatura where cargaAcademica.codigoAsignatura=old.codigoAsignatura; end;");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('L-X','Lunes-Miercoles');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('M-V','Martes-Viernes');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('L','Lunes');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('M','Martes');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('X','Miercoles');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('J','Jueves');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('S','Sabado');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('D','Domingo');");
                db.execSQL("INSERT INTO prioridad (idPrioridad,nivelPrioridad) VALUES ('A','Alta');");
                db.execSQL("INSERT INTO prioridad (idPrioridad,nivelPrioridad) VALUES ('M','Media');");
                db.execSQL("INSERT INTO prioridad (idPrioridad,nivelPrioridad) VALUES ('B','Baja');");

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
                db.execSQL("CREATE TABLE detallereserva(iddetalle INTEGER PRIMARY KEY AUTOINCREMENT, idhorario INTEGER NOT NULL, idreservaevento INTEGER NOT NULL  ,codigolocal VARCHAR(7) NOT NULL)");
                db.execSQL("CREATE TABLE reservaevento(idreservaevento INTEGER PRIMARY KEY AUTOINCREMENT,codigoescuela VARCHAR(20) NOT NULL,idtipoevento VARCHAR(2) NOT NULL,nombreevento VARCHAR(30),capacidadtotalevento INTEGER, fechareserva VARCHAR(10))");
                db.execSQL("CREATE TABLE tipoevento(idtipoevento VARCHAR(2) NOT NULL ,nomtipoevento VARCHAR(30),PRIMARY KEY(idtipoevento))");

                db.execSQL("INSERT INTO tipoevento(idtipoevento, nomtipoevento)  VALUES('T1', 'Evaluacion')");
                db.execSQL("INSERT INTO tipoevento(idtipoevento, nomtipoevento)  VALUES('T2', 'Capacitacion')");
                db.execSQL("INSERT INTO tipoevento(idtipoevento, nomtipoevento)  VALUES('T3', 'Clases')");
                //TRIGGER VERIFICAR EXISTENCIA DE IDHORARIO EN TABLA HORARIO
                db.execSQL("CREATE TRIGGER fk_horario_detallereserva\n" +
                        "BEFORE INSERT ON detallereserva\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    SELECT CASE\n" +
                        "    WHEN ((SELECT idHorario FROM horario\n" +
                        "          WHERE idHorario=new.idHorario)IS NULL )\n" +
                        "     THEN RAISE(ABORT, 'No existe Horario')\n" +
                        "    END;\n" +
                        "END");
                //TRIGGER EXISTENCIA DE CODIGO ESCUELA EN TABLA ESCUELA
               /* db.execSQL("CREATE TRIGGER fk_escuela_reservaevento\n" +
                        "BEFORE INSERT ON reservaevento\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    SELECT CASE\n" +
                        "    WHEN ((SELECT codigoEscueala FROM Escuela\n" +
                        "          WHERE codigoEscuela=new.codigoEscuela)IS NULL )\n" +
                        "     THEN RAISE(ABORT, 'No codigo escuela')\n" +
                        "    END;\n" +
                        "END");
                        //TIGGER EXISTENCIA DE CODIGOLOCAL EN TABLA LOCAL
                 db.execSQL("CREATE TRIGGER fk_local_detallereserva\n" +
                       "BEFORE INSERT ON detallereserva\n" +
                       "FOR EACH ROW\n" +
                       "BEGIN\n" +
                       "    SELECT CASE\n" +
                       "    WHEN ((SELECT codigoLocal FROM Local\n" +
                       "          WHERE codigoLocal=new.codigoLocal)IS NULL )\n" +
                       "     THEN RAISE(ABORT, 'No existe el Local seleccionado')\n" +
                       "    END;\n" +
                       "END");
                        */
                //tabla docente
                db.execSQL("CREATE TABLE docente(carnetDocente VARCHAR(7) NOT NULL,nombreDocente VARCHAR(30)," +
                        "apellido VARCHAR(30),rol VARCHAR(30),nomEscuela VARCHAR(30),PRIMARY KEY(carnetDocente));");
                //tabla tipo local
                db.execSQL("CREATE TABLE tipoLocal(idTipoLocal INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nomTipoLocal varchar(30));");
                //tabla rol docente
                db.execSQL("CREATE TABLE rolDocente(idRolDocente INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nomRolDocente varchar(30));");


            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    //Metodo para leer datos de la base
    public SQLiteDatabase abrirConsultar() throws SQLException{
        db = DBHelper.getReadableDatabase();
        return db;
    }
    public void cerrar(){
        DBHelper.close();
    }

    public String insertar(Asignatura asignatura){

        String regInsertados="Registro Insertado No= ";
        long contador=0;

        ContentValues asig= new ContentValues();
        asig.put("codigoAsignatura", asignatura.getCodigoAsignatura());
        asig.put("codigoLocal", asignatura.getCodigoLocal());
        asig.put("codigoEscuela", asignatura.getCodigoEscuela());
        asig.put("nomAsignatura", asignatura.getNomAsignatura());
        asig.put("idPrioridad", asignatura.getIdPrioridad());
        contador=db.insert("asignatura",null,asig);
        if(contador==-1||contador==0){
            regInsertados="Error al insertar el registro, Registro Duplicado. Vereficar insercion";
        }else{
            regInsertados= regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(CargaAcademica cargaAcademica){
        String regInsertados = "Registro Insertado No= ";
        long contador=0;
        if(verificarIntegridad(cargaAcademica,1)){
            ContentValues cargaAcademicas = new ContentValues();
            cargaAcademicas.put("idRolDocente", cargaAcademica.getidRolDocente());
            cargaAcademicas.put("codigoAsignatura", cargaAcademica.getcodigoAsignatura());
            cargaAcademicas.put("codigoCiclo", cargaAcademica.getcodigoCiclo());
            cargaAcademicas.put("carnetDocente", cargaAcademica.getcarnetDocente());
            contador=db.insert("cargaAcademica", null,cargaAcademicas);
        }
        if(contador==-1|| contador==0){
            regInsertados= "Error al insertar el registro, Registro Dublicado. Verificar insercion";
        }else {
            regInsertados= regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(Horario horario){
        String regInsertados = "Registro Insertado No= ";
        long contador=0;
        ContentValues hor = new ContentValues();
        hor.put("idHorario", horario.getidHorario());
        hor.put("idDia", horario.getidDia());
        hor.put("horaInicio", horario.gethoraInicio());
        hor.put("horaFin", horario.gethoraFin());
        contador= db.insert("horario",null,hor);
        if(contador==1||contador==0){
            regInsertados= "Error al insertar el registro, Registro Duplicado. Verificar insercion";
        }else{
            regInsertados= regInsertados+ contador;
        }
        return regInsertados;
    }

    public String actualizar(Asignatura asignatura){
        if(verificarIntegridad(asignatura, 5)){
            String[] id= {asignatura.getCodigoAsignatura()};
            ContentValues cv = new ContentValues();
            cv.put("codigoLocal", asignatura.getCodigoLocal());
            cv.put("codigoEscuela", asignatura.getCodigoEscuela());
            cv.put("nomAsignatura", asignatura.getNomAsignatura());
            db.update("asignatura", cv, "codigoAsignatura = ?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro con codigoAsignatura "+ asignatura.getCodigoAsignatura() + "no existe";
        }
    }

    public String actualizar(Horario horario){
        if(verificarIntegridad(horario,6)){
            String [] id= {String.valueOf(horario.getidHorario())}; //error por ser entero
            ContentValues cv = new ContentValues();
            cv.put("idDia",horario.getidDia());
            cv.put("horaInicio",horario.gethoraInicio());
            cv.put("horaFin",horario.gethoraFin());
            db.update("horario",cv,"idHorario = ?", id);
            return "Registro actualizado correctamente";
        }else {
            return "Registro con codigo de horario " +horario.getidHorario()+ "no existe";
        }

    }

    public String actualizar(CargaAcademica cargaAcademica){

        if(verificarIntegridad(cargaAcademica, 2)){
            String[] id = { cargaAcademica.getcodigoAsignatura(), cargaAcademica.getcodigoCiclo(), cargaAcademica.getcarnetDocente()};
            ContentValues cv = new ContentValues();
            cv.put("idRolDocente", cargaAcademica.getidRolDocente());
            db.update("cargaAcademica", cv, "codigoAsignatura = ? AND codigoCiclo = ? AND carnetDocente = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String eliminar(Asignatura asignatura){
        String regAfectados= "filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(asignatura,5)) {
            if (verificarIntegridad(asignatura, 3)) {
                regAfectados += "No se puede eliminar, Tiene dependencias en otra tabla";
            } else {
                contador += db.delete("asignatura", "codigoAsignatura='" + asignatura.getCodigoAsignatura() + "'", null);
                regAfectados += contador;
            }
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public String eliminar(CargaAcademica cargaAcademica){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="codigoAsignatura='"+cargaAcademica.getcodigoAsignatura()+"'";
        where=where+" AND carnetDocente='"+cargaAcademica.getcarnetDocente()+"'";
        where=where+" AND codigoCiclo="+cargaAcademica.getcodigoCiclo();
        if (verificarIntegridad(cargaAcademica,2)) {
            contador += db.delete("cargaAcademica", where, null);
            regAfectados += contador;
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public String eliminar(Horario horario){
        String regAfectados= "filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(horario,6)){
            if(verificarIntegridad(horario,4)){
                regAfectados+="No se puede eliminar, Tiene dependencias en otra tabla";
            }else{
                contador+= db.delete("horario", "idHorario='"+horario.getidHorario()+"'",null);
                regAfectados+= contador;
            }
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public Asignatura consultarAsignatura(String codigoAsignatura){
        String[] id= {codigoAsignatura};
        Cursor cursor = db.query("asignatura", camposAsignatura, "codigoAsignatura = ?",id,null,null,null);
        if(cursor.moveToFirst()){
            Asignatura asignatura= new Asignatura();
            asignatura.setCodigoAsignatura(cursor.getString(0));
            asignatura.setCodigoLocal(cursor.getString(1));
            asignatura.setCodigoEscuela(cursor.getString(2));
            asignatura.setNomAsignatura(cursor.getString(3));
            asignatura.setIdPrioridad(cursor.getString(4));
            return asignatura;
        }else{
            return null;
        }
    }

    public Horario consultarHorario(String idHorario){
        String[] id= {idHorario};
        Cursor cursor = db.query("horario", camposHorario, "idHorario = ?",id,null,null,null);
        if(cursor.moveToFirst()){
            Horario horario = new Horario();
            horario.setidHorario(cursor.getInt(0));
            horario.setidDia(cursor.getString(1));
            horario.sethoraInicio(cursor.getString(2));
            horario.sethoraFin(cursor.getString(3));
            return horario;
        }else{
            return null;
        }
    }

    public ArrayList<Dia> listaDia(){
        abrirConsultar();
        Dia dia;
        ArrayList<Dia> lista = new ArrayList<Dia>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM dia", null);
        while(cursor.moveToNext()){
            dia = new Dia();
            dia.setIdDia(cursor.getString(0));
            dia.setNomDia(cursor.getString(1));
            lista.add(dia);

        }return lista;
    }

    public ArrayList<Prioridad> listaPrioridad(){
        abrirConsultar();
        Prioridad prioridad;
        ArrayList<Prioridad> lista = new ArrayList<Prioridad>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM prioridad", null);
        while(cursor.moveToNext()){
            prioridad = new Prioridad();
            prioridad.setIdprioridad(cursor.getString(0));
            lista.add(prioridad);
        }return lista;
    }


    public CargaAcademica consultarCargaAcademica( String carnetDocente,String codigoAsignatura, String codigoCiclo){
        String[] id= {carnetDocente,codigoAsignatura,codigoCiclo};
        Cursor cursor= db.query("cargaAcademica",camposCargaAcademica, "carnetDocente = ? AND codigoAsignatura = ?  AND codigoCiclo= ?", id,null,null,null);
        if (cursor.moveToFirst()){
            CargaAcademica cargaAcademica = new CargaAcademica();
            cargaAcademica.setcarnetDocente(cursor.getString(3));
            cargaAcademica.setcodigoAsignatura(cursor.getString(1));
            cargaAcademica.setcodigoCiclo(cursor.getString(2));
            cargaAcademica.setidRolDocente(cursor.getString(0));
            return cargaAcademica;
        }else {
            return null;
        }
    }
    //---------------------------------TipoEvento---------------------------------
    public String insertar(TipoEvento tipoEvento){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        //*
        if(verificarIntegridad(tipoEvento,7))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else
        {

            ContentValues tipoEv = new ContentValues();
            tipoEv.put("idtipoevento", tipoEvento.getIdTipoEvento());
            tipoEv.put("nomtipoevento", tipoEvento.getNomTipoEvento());
            contador=db.insert("tipoevento", null, tipoEv);
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public String actualizar(TipoEvento tipoEvento){

        // * 2 Verificar que el registro que exista
        if(verificarIntegridad(tipoEvento, 7)){
            String[] id = {tipoEvento.getIdTipoEvento()};
            ContentValues cv = new ContentValues();
            cv.put("nomtipoevento", tipoEvento.getNomTipoEvento());
            db.update("tipoEvento", cv, "idtipoevento = ? ", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }
    public String eliminar(TipoEvento tipoEvento){

        String regAfectados="filas afectadas= ";
        int contador=0;

        if(verificarIntegridad(tipoEvento, 7))
        {
            String where="idtipoevento='"+tipoEvento.getIdTipoEvento()+"'";

            contador+=db.delete("tipoEvento", where, null);
            regAfectados+=contador;
            return regAfectados;
        }
        else
        {
            return "Registro no Existe";
        }
    }
    public TipoEvento consultarTipoEvento(String idtipoevento){

        String[] id = {idtipoevento};
        Cursor cursor = db.query("tipoevento", camposTipoEvento, "idtipoevento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoEvento(cursor.getString(0));
            tipoEvento.setNomTipoEvento(cursor.getString(1));
            return tipoEvento;
        }else{
            return null;
        }
    }
    public ArrayList<TipoEvento> ListTipoEventos() {

        abrirConsultar();
        TipoEvento tipoEvento;
        ArrayList<TipoEvento> lista = new ArrayList<TipoEvento>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM tipoevento", null);
        while(cursor.moveToNext()){
            tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoEvento(cursor.getString(0));
            lista.add(tipoEvento);
        }
        return lista;
    }
    //---------------------------------ReservaEvento------------------------------------------------------
    public String insertar(ReservaEvento reservaEvento){
        String regInsertados;
        long contador=0;
        ContentValues reservas = new ContentValues();
        reservas.put("codigoEscuela", reservaEvento.getCodigoEscuela());
        reservas.put("idTipoEvento", reservaEvento.getIdTipoEvento());
        reservas.put("nombreEvento", reservaEvento.getNombreEvento());
        reservas.put("capacidadTotalEvento", reservaEvento.getCapacidadTotalEvento());
        reservas.put("fechaReserva", reservaEvento.getFechaReservaEvento());
        contador = db.insert("reservaevento", "idReservaEvento", reservas);
        if (contador==-1 || contador==0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {

            regInsertados = "Registro Insertado con exito.";
        }
        return regInsertados;

    }

    public String actualizar(ReservaEvento reservaEvento){
        int contador=0;
        String[] id = {String.valueOf(reservaEvento.getIdReservaEvento())};
        ContentValues cv = new ContentValues();
        cv.put("codigoEscuela", reservaEvento.getCodigoEscuela());
        cv.put("idTipoevento", reservaEvento.getIdTipoEvento());
        cv.put("nombreEvento", reservaEvento.getNombreEvento());
        cv.put("capacidadTotalEvento", reservaEvento.getCapacidadTotalEvento());
        cv.put("fechaReserva", reservaEvento.getFechaReservaEvento());
        String where="idReservaEvento= ?";
        contador+=db.update("reservaevento",cv, where, id);
        return "Registro actualizado con exito";
    }

    public String eliminar(ReservaEvento reservaEvento){


        String regAfectados="filas afectadas= ";
        int contador=0;
        //
        if(verificarIntegridad(reservaEvento, 8))
        {
            String where="idReservaEvento='"+reservaEvento.getIdReservaEvento()+"'";
            where=where+" AND codigoEscuela='"+reservaEvento.getCodigoEscuela()+"'";
            where=where+" AND idTipoEvento='"+reservaEvento.getIdTipoEvento()+"'";
            contador+=db.delete("reservaevento", where, null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con identificador " + reservaEvento.getCodigoEscuela() + " no existe";
        }
        return regAfectados + "El registro se elimino";
    }
    public ReservaEvento consultarReserva(int idreservaevento){

        String[] id = {String.valueOf(idreservaevento)};
        Cursor cursor = db.query("reservaevento", camposReservaEvento, "idReservaEvento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            ReservaEvento reservaEvento = new ReservaEvento();
            reservaEvento.setIdReservaEvento(cursor.getInt(0));
            reservaEvento.setCodigoEscuela(cursor.getString(1));
            reservaEvento.setIdTipoEvento(cursor.getString(2));
            reservaEvento.setNombreEvento(cursor.getString(3));
            reservaEvento.setCapacidadTotalEvento(cursor.getInt(4));
            reservaEvento.setFechaReservaEvento(cursor.getString(5));
            return reservaEvento;
        }else{
            return null;
        }
    }
    public ArrayList<ReservaEvento> consultarReservas() {

        abrirConsultar();
        ReservaEvento reservaEvento;
        ArrayList<ReservaEvento> lista = new ArrayList<ReservaEvento>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM reservaevento", null);
        while(cursor.moveToNext()){
            reservaEvento = new ReservaEvento();
            reservaEvento.setIdReservaEvento(cursor.getInt(0));
            lista.add(reservaEvento);
        }
        return lista;
    }
    //-----------------------DetalleReserva-----------------------------------------------------------------------
    public String insertar(DetalleReservaEvento detalleReservaEvento){

        String regInsertados;
        long contador=0;


        if (verificarIntegridad(detalleReservaEvento, 9)) {
            regInsertados = "Error. Registro duplicado";
        } else {
            ContentValues detalle = new ContentValues();
            detalle.put("idHorario", detalleReservaEvento.getIdHorario());
            detalle.put("idReservaEvento", detalleReservaEvento.getIdReservaEvento());
            detalle.put("codigoLocal", detalleReservaEvento.getCodigoLocal());
            contador = db.insert("detallereserva", null, detalle);
            if (contador==-1){
                regInsertados = "Error. No se encontro horario asignado";
            }
            else {
                regInsertados = "Registro insertado exitosamente ";
            }
        }
        return regInsertados;
    }
    public String actualizar(DetalleReservaEvento detalleReservaEvento){

        String[] id = {String.valueOf(detalleReservaEvento.getIdReservaEvento())};
        ContentValues reservas = new ContentValues();
        reservas.put("idHorario", detalleReservaEvento.getIdHorario());
        reservas.put("idReservaEvento", detalleReservaEvento.getIdReservaEvento());
        reservas.put("codigoLocal", detalleReservaEvento.getCodigoLocal());
        db.update("detallereserva", reservas, "idReservaEvento = ?", id);
        return "Registro Actualizado Correctamente";
    }
    public String eliminar(DetalleReservaEvento detalleReservaEvento){
        String regAfectados="El registro se elimino ";
        int contador=0;

        if (verificarIntegridad(detalleReservaEvento,9)) {
            String where="idHorario = '"+detalleReservaEvento.getIdHorario()+"'";
            where+=" AND idReservaEvento = '"+detalleReservaEvento.getIdReservaEvento()+"'";
            where+=" AND codigoLocal = '"+detalleReservaEvento.getCodigoLocal()+"'";
            contador+=db.delete("detallereserva", where, null);
            regAfectados+=contador;
            return regAfectados;
        }
        else{
            return "El registro no se encontró.";
        }


    }
    public DetalleReservaEvento consultarDetalle( int idHorario, int idReservaEvento, String codLocal){
        String[] id = {String.valueOf(idHorario), String.valueOf(idReservaEvento), codLocal};
        Cursor cursor = db.query("detallereserva", camposDetalleReserva, "idHorario = ? AND idReservaEvento = ? AND codigoLocal= ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetalleReservaEvento detalleReservaEvento=new DetalleReservaEvento();
            detalleReservaEvento.setIdHorario(cursor.getInt(0));
            detalleReservaEvento.setIdReservaEvento(cursor.getInt(1));
            detalleReservaEvento.setCodigoLocal(cursor.getString(2));

            return detalleReservaEvento;
        }else{
            return null;
        }
    }

    // Insertar Docente
    public String insertar(Docente docente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        // verificar que no exista docente
        if(verificarIntegridadJavier(docente,1))
        {
            regInsertados= "Error al Insertar el registro ya existe, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues doce = new ContentValues();
            doce.put("carnetDocente", docente.getCarnetDocente());
            doce.put("nombreDocente", docente.getNombreDocente());
            doce.put("apellido", docente.getApellido());
            doce.put("rol", docente.getRol());
            doce.put("nomEscuela", docente.getNomEscuela());
            contador=db.insert("docente", null, doce);
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    //Metodo Consultar Docente
    public Docente consultarDocente(String carnetDocente){

        String[] id = {carnetDocente};
        Cursor cursor = db.query("docente", camposDocente, "carnetDocente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente doce = new Docente();
            doce.setCarnetDocente(cursor.getString(0));
            doce.setNombreDocente(cursor.getString(1));
            doce.setApellido(cursor.getString(2));
            doce.setRol(cursor.getString(3));
            doce.setNomEscuela(cursor.getString(4));
            return doce;
        }else{
            return null;
        }
    }

    //Metodo de Actualizar Docente
    public String actualizar(Docente docente){
        //Si existe
        if(verificarIntegridadJavier(docente, 1)){
            String[] id = {docente.getCarnetDocente()};
            ContentValues cv = new ContentValues();
            cv.put("nombreDocente", docente.getNombreDocente());
            cv.put("apellido", docente.getApellido());
            cv.put("rol", docente.getRol());
            cv.put("nomEscuela", docente.getNomEscuela());
            db.update("docente", cv, "carnetDocente = ?", id);
            return "Registro del Docente se Actualizo Correctamente";
        }else{
            return "Registro con carnet del Docente " + docente.getCarnetDocente() + " no existe";
        }
    }

    //Metodo eliminar docente
    public String eliminar(Docente docente){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe carnet
        if(verificarIntegridadJavier(docente, 1))        {
            //si tiene registros relacionados, se eliminan primero
            /*if (verificarIntegridad(alumno,3)) {
                contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
            }*/
            contador+=db.delete("docente", "carnetDocente='"+docente.getCarnetDocente()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con carnet del Docente  " + docente.getCarnetDocente() + " no existe";
        }

        return regAfectados;
    }

    //Insertar Rol docente
    public String insertar(RolDocente rolDocente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        // verificar que no exista el rol docente
        if(verificarIntegridadJavier(rolDocente,2))
        {
            regInsertados= "Error al Insertar el rol para el docente ya existe, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues rol = new ContentValues();
            rol.put("nomRolDocente", rolDocente.getNomRolDocente());
            contador=db.insert("rolDocente", "idRolDocente", rol);
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    //Consultar el rol del Docente
    public RolDocente consultarRolDocente(String nomRolDocente){

        String[] id = {nomRolDocente};
        Cursor cursor = db.query("rolDocente", camposRolDocente, "nomRolDocente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            RolDocente rol = new RolDocente();
            rol.setIdRolDocente(cursor.getInt(0));
            rol.setNomRolDocente(cursor.getString(1));
            return rol;
        }else{
            return null;
        }
    }

    //Actualizar el rol del Docente
    public String actualizar(RolDocente rolDocente){
        //Si existe
        if(verificarIntegridadJavier(rolDocente, 2)){
            String[] id = {rolDocente.getNomRolDocente()};
            ContentValues variable = new ContentValues();
            variable.put("nomRolDocente", rolDocente.getNomRolDocente());
            db.update("rolDocente", variable, "nomRolDocente = ?", id);
            return "Registro del Rol del Docente Actualizado Correctamente";
        }else{
            return "El Rol Docente " + rolDocente.getNomRolDocente() + " no existe";
        }
    }

    public String actualizar1(RolDocente rolDocente){
        //verifica si existe un rol docente existente
        if(verificarIntegridadJavier(rolDocente, 2)){
            return "Registro del Rol del Docente que quiere actualizar ya existe";
        }else{
            String[] id = {String.valueOf(rolDocente.getIdRolDocente())};
            ContentValues variable = new ContentValues();
            variable.put("idRolDocente", rolDocente.getIdRolDocente());
            variable.put("nomRolDocente", rolDocente.getNomRolDocente());
            db.update("rolDocente", variable, "idRolDocente = ?", id);
            return "Registro del Rol del Docente se Actualizo Correctamente";
        }

    }

    //eliminar el rol del Docente
    public String eliminar(RolDocente rolDocente){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe carnet
        if(verificarIntegridadJavier(rolDocente, 2))        {
            //si tiene registros relacionados, se eliminan primero
            /*if (verificarIntegridad(alumno,3)) {
                contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
            }*/
            contador+=db.delete("rolDocente", "nomRolDocente='"+rolDocente.getNomRolDocente()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con el Rol Docente " + rolDocente.getNomRolDocente() + " no existe";
        }

        return regAfectados;
    }

    //Insertar Tipo Local
    public String insertar(TipoLocal tipoLocal){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        // verificar que no exista el tipo de local
        if(verificarIntegridadJavier(tipoLocal,3))
        {
            regInsertados= "Error al Insertar el registro del Tipo Local, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues tipo = new ContentValues();
            tipo.put("nomTipoLocal", tipoLocal.getNomTipoLocal());
            contador=db.insert("tipoLocal", "idTipoLocal", tipo);
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    //Consultar el Tipo Local
    public TipoLocal consultarTipoLocal(String nomTipoLocal){

        String[] id = {nomTipoLocal};
        Cursor cursor = db.query("tipoLocal", camposTipoLocal, "nomTipoLocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoLocal local = new TipoLocal();
            local.setIdTipoLocal(cursor.getInt(0));
            local.setNomTipoLocal(cursor.getString(1));
            return local;
        }else{
            return null;
        }
    }

    //Actualizar el Tipo de Local
    public String actualizar(TipoLocal tipoLocal){
        //verifica si existe un tipo Local
        if(verificarIntegridadJavier(tipoLocal, 3)){
            return "Registro del Tipo Local que quiere actualizar ya existe";
        }else{
            String[] id = {String.valueOf(tipoLocal.getIdTipoLocal())};
            ContentValues variable = new ContentValues();
            variable.put("idTipoLocal", tipoLocal.getIdTipoLocal());
            variable.put("nomTipoLocal", tipoLocal.getNomTipoLocal());
            db.update("tipoLocal", variable, "idTipoLocal = ?", id);
            return "Registro del Tipo Local se Actualizo Correctamente";
        }

    }

    //eliminar el rol del Docente
    public String eliminar(TipoLocal tipoLocal){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe carnet
        if(verificarIntegridadJavier(tipoLocal, 3))        {
            //si tiene registros relacionados, se eliminan primero
            /*if (verificarIntegridad(alumno,3)) {
                contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
            }*/
            contador+=db.delete("tipoLocal", "nomTipoLocal='"+tipoLocal.getNomTipoLocal()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con el Tipo Local " + tipoLocal.getNomTipoLocal() + " no existe";
        }

        return regAfectados;
    }

    //---------------------------------------------------------------------------------------
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch (relacion){
            case 1:
            {
                //verificar que al insertar cargaAcademica exista codigoAsignatura de asignatura
                CargaAcademica cargaAcademica = (CargaAcademica)dato;
                String[] id1 = {cargaAcademica.getcodigoAsignatura()};
                abrir();
                Cursor cursor1 = db.query("asignatura", null, "codigoAsignatura = ?", id1, null, null, null);
                if(cursor1.moveToFirst() ){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 2:
            {
                //verificar que al modificar cargaAcademica exista codigoAsignatura del asignatura, el codigo de horario y el codigoCiclo
                CargaAcademica cargaAcademica1 = (CargaAcademica)dato;
                String[] ids = {cargaAcademica1.getcodigoAsignatura(),cargaAcademica1.getcodigoCiclo(),cargaAcademica1.getcarnetDocente() };
                abrir();
                Cursor c = db.query("cargaAcademica", null, "codigoAsignatura = ? AND codigoCiclo = ? AND carnetDocente = ? ", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 3:
            {
                Asignatura asignatura = (Asignatura)dato;
                Cursor c=db.query(true, "cargaAcademica", new String[] {"codigoAsignatura" }, "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null, null, null, null, null);
                //Cursor c1=db.query(true, "detalleGrupoReserva", new String[] {"idHorario" }, "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null, null, null, null, null);
                //Cursor c2=db.query(true, "grupo", new String[] {"idHorario" }, "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null, null, null, null, null);

                if(c.moveToFirst())
                    return true; //Se encontraron datos
                else
                    return false;
            }
            case 4:
            {
                Horario horario = (Horario)dato;
                Cursor dghor=db.query(true, "detalleGrupoReserva", new String[] {"idHorario" }, "idHorario='"+horario.getidHorario()+"'",null, null, null, null, null);
                Cursor drhor=db.query(true, "detalleReservaEvento", new String[] {"idHorario" }, "idHorario='"+horario.getidHorario()+"'",null, null, null, null, null);

                if(dghor.moveToFirst() && drhor.moveToFirst())
                   return true; //Se encontraron datos
                else
                    return false;
            }
            case 5:
            {
                //verificar que exista asignatura
                Asignatura asignatura2 = (Asignatura)dato;
                String[] id = {asignatura2.getCodigoAsignatura()};
                abrir();
                Cursor c2 = db.query("asignatura", null, "codigoAsignatura = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Asignatura
                    return true;
                }
                return false;
            }
            case 6:
            {
                //verificar que exista Horario
                Horario horario2 = (Horario)dato;
                String[] idm = {String.valueOf(horario2.getidHorario())};
                abrir();
                Cursor cm = db.query("horario", null, "idHorario = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Horario
                    return true;
                }
                return false;
            }
            case 7:{
                TipoEvento tipoEvento = (TipoEvento) dato;
                String[] id = {tipoEvento.getIdTipoEvento()};
                abrir();
                Cursor c2 = db.query("tipoevento", null, "idtipoevento = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Tipo de evento
                    return true;
                }
                return false;
            }
            case 8:{
                //verificar que exista codigoEscuela en ReservaEvento
                ReservaEvento reserva1 = (ReservaEvento) dato;
                String[] id = {String.valueOf(reserva1.getCodigoEscuela())};
                abrir();
                Cursor c2 = db.query("reservaevento", null, "codigoEscuela = ? ", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }
            case 9: {
                DetalleReservaEvento detalleReservaEvento = (DetalleReservaEvento) dato;
                String[] ids = {String.valueOf(detalleReservaEvento.getIdHorario()),String.valueOf(detalleReservaEvento.getIdReservaEvento()), detalleReservaEvento.getCodigoLocal()};
                abrir();
                Cursor c = db.query("detallereserva", null, "idHorario = ? AND idReservaEvento = ? AND codigoLocal= ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }

    /*verificacion de integridad de javier */
    private boolean verificarIntegridadJavier(Object dato, int relacion) throws SQLException{

        switch(relacion){

            case 1:
            {
                //verificar que exista un docente
                Docente docente2 = (Docente)dato;
                String[] id = {docente2.getCarnetDocente()};
                abrir();
                Cursor c2 = db.query("docente", null, "carnetDocente = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Docente
                    return true;
                }
                return false;
            }

            case 2:
            {
                //verificar que exista un rol docente
                RolDocente rolDocente2 = (RolDocente)dato;
                String[] id = {rolDocente2.getNomRolDocente()};
                abrir();
                Cursor c2 = db.query("rolDocente", null, "nomRolDocente = ?",id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro el rol docente
                    return true;
                }
                return false;
            }

            case 3:
            {
                //verificar que exista el tipo de local
                TipoLocal tipoLocal2 = (TipoLocal) dato;
                String[] id = {tipoLocal2.getNomTipoLocal()};
                abrir();
                Cursor c2 = db.query("tipoLocal", null, "nomTipoLocal = ?",id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro el rol docente
                    return true;
                }
                return false;
            }

            default:
                return false;

        }


    }

    public String llenarBD(){

        // tabla Asignatura--------------------- 4 tuplas
        final String[] VAcodigoAsignatura = {"MAT115","PRN115","IEC115","TSI115"};
        final String[] VAcodigoLocal = {"B11","C31","c31","B11"};
        final String[] VAcodigoEscuela = {"UDCB","EISI","Gonzales","EISI"};
        final String[] VAnomAsignatura = {"Matematica I","Programacion I","Ingenieria Economica","Teoria de Sistemas"};
        final String[] VAidPrioridad = {"A","B","B","B"};

        //tabla Horario--------------------- 4 tuplas
        final Integer[] VHidHorario = {1,2,3,4};
        final String[] VHidDia = {"L-X","L-X","L-X","L-X"};
        final String[] VHhoraInicio = {"6:20","8:05","9:50","11:35"};
        final String[] VHhoraFin = {"8:00","9:45","11:30","13:15"};

        // tabla CargaAcademica--------------------- 7 tuplas
        final String[] VCidRolDocente = {"01","01","02","02","03","04","01"};
        final String[] VCcodigoAsignatura = {"MAT115","PRN115","IEC115","TSI115","IEC115","MAT115","PRN115"};
        final String[] VCcodigoCiclo = {"12016","12016","22016","22016","22020","22020","22016"};
        final String[] VCcarnetDocente = {"vvvvvvv","fffffff","sssssss","eeeeeee","ttttttt","vvvvvvv","fffffff"};

        //Tabla docente
        final String[] VDcarnetDocente = {"OO12035","OF12044","GG11098","CC12021"};
        final String[] VDnombreDocente = {"Oscar","Fatima","Sara","Gabriela"};
        final String[] VDapellido = {"Rodriguez","Sanchez","Gonzales","Coto"};
        final String[] VDrol = {"Docente","Docente","Asesor","Instructor"};
        final String[] VDnomEscuela = {"Ing. Sistema Informatico","Ing. Sistema Informatico","Ing. Sistema informatico",
                "Ing. Sistema Informatico"};

        //tabla rol docente
        final String[] VRDnomRolDocente = {"Docente","Docente","Jefe Catedra","Encargado de Laboratorio"};

        //tipo de local
        final String[] V_TPnomTipoLocal = {"B11","C11","D11","Lcomp1"};

        abrir();
        db.execSQL("DELETE FROM asignatura");
        db.execSQL("DELETE FROM horario");
        db.execSQL("DELETE FROM cargaAcademica");

        db.execSQL("DELETE FROM docente;");
        db.execSQL("DELETE FROM rolDocente;");
        db.execSQL("DELETE FROM tipoLocal;");

        //llenado tabla Asignatura
        Asignatura asignatura = new Asignatura();
        for(int i=0;i<4;i++){
            asignatura.setCodigoAsignatura(VAcodigoAsignatura[i]);
            asignatura.setCodigoLocal(VAcodigoLocal[i]);
            asignatura.setCodigoEscuela(VAcodigoEscuela[i]);
            asignatura.setNomAsignatura(VAnomAsignatura[i]);
            asignatura.setIdPrioridad(VAidPrioridad[i]);
            insertar(asignatura);
        }

        //llenado tabla Horario
        Horario horario = new Horario();
        for(int i=0;i<4;i++){ //i<4 porque son 4 tuplas los que se ingresan
            horario.setidHorario(VHidHorario[i]);
            horario.setidDia(VHidDia[i]);
            horario.sethoraInicio(VHhoraInicio[i]);
            horario.sethoraFin(VHhoraFin[i]);
            insertar(horario);
        }

        //llenado tabla CargaAcademica
        CargaAcademica cargaAcademica = new CargaAcademica();
        for(int i=0;i<7;i++){ //i<7 porque son 7 tuplas los que se ingresan
            cargaAcademica.setidRolDocente(VCidRolDocente[i]);
            cargaAcademica.setcodigoAsignatura(VCcodigoAsignatura[i]);
            cargaAcademica.setcodigoCiclo(VCcodigoCiclo[i]);
            cargaAcademica.setcarnetDocente(VCcarnetDocente[i]);
            insertar(cargaAcademica);
        }

        //llenado de las tabla docente
        Docente docente = new Docente();
        for(int i=0;i<4;i++){
            docente.setCarnetDocente(VDcarnetDocente[i]);
            docente.setNombreDocente(VDnombreDocente[i]);
            docente.setApellido(VDapellido[i]);
            docente.setRol(VDrol[i]);
            docente.setNomEscuela(VDnomEscuela[i]);
            insertar(docente);
        }

        //llenado de la tabla rol docente
        RolDocente rolDocente = new RolDocente();
        for(int i=0;i<4;i++){
            rolDocente.setNomRolDocente(VRDnomRolDocente[i]);
            insertar(rolDocente);
        }

        //llenado de la tabla tipo local
        TipoLocal tipoLocal = new TipoLocal();
        for (int i=0;i<4;i++){
            tipoLocal.setNomTipoLocal(V_TPnomTipoLocal[i]);
            insertar(tipoLocal);
        }

        cerrar();
        return "Guardado Correctamente";
    }
}
