package ues.fia.eisi.reservalocalfia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlReserveLocal {

    //campos de las tablas de la base de datos
    private static final String[] camposAsignatura = new String[] {"codigoAsignatura","codigoLocal","codigoEscuela","nomAsignatura","idPrioridad"};
    private static final String[] camposCargaAcademica = new String[] {"idRolDocente","codigoAsignatura", "codigoCiclo","carnetDocente"};
    private static final String[] camposHorario = new String[] {"idHorario","idDia","horaInicio","horaFin"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

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
                db.execSQL("CREATE TABLE asignatura(codigoAsignatura VARCHAR(6) NOT NULL PRIMARY KEY, codigoLocal VARCHAR(20),codigoEscuela VARCHAR(20),nomAsignatura VARCHAR(30),idPrioridad INTEGER not null);");
                db.execSQL("CREATE TABLE horario(idHorario INTEGER NOT NULL PRIMARY KEY,idDia VARCHAR(1),horaInicio VARCHAR(30),horaFin VARCHAR(30));");
                db.execSQL("CREATE TABLE cargaAcademica(idRolDocente VARCHAR(2) NOT NULL , codigoAsignatura VARCHAR(6) NOT NULL ,codigoCiclo VARCHAR(6) ,carnetDocente Varchar(7) ,PRIMARY KEY(codigoAsignatura,carnetDocente,codigoCiclo));");
                db.execSQL("CREATE TABLE dia(idDia VARCHAR(1) NOT NULL PRIMARY KEY,nomDia varchar(20) not null);");
                db.execSQL("CREATE TABLE rolDocente(idRolDocente VARCHAR(2) NOT NULL PRIMARY KEY,nomRolDocente VARCHAR(20) not null);");
                db.execSQL("CREATE TABLE prioridad(idPrioridad INTEGER NOT NULL PRIMARY KEY,codigoAsignatura VARCHAR(6), nivelPrioridad VARCHAR(20));");

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
        if(verificarIntegridad(asignatura,3)){
            contador+= db.delete("cargaAcademica", "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null);
        }
        contador+=db.delete("asignatura", "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'", null);
        regAfectados+= contador;
        return regAfectados;
    }

    public String eliminar(CargaAcademica cargaAcademica){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="codigoAsignatura='"+cargaAcademica.getcodigoAsignatura()+"'";
        where=where+" AND carnetDocente='"+cargaAcademica.getcarnetDocente()+"'";
        where=where+" AND codigoCiclo="+cargaAcademica.getcodigoCiclo();
        contador+=db.delete("cargaAcademica", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(Horario horario){//no esta en el pdf que nos dio el ingeniero
        String regAfectados= "filas afectadas= ";
        int contador=0;
        //if(verificarIntegridad(horario,4)){
          //  contador+= db.delete("cargaAcademica","idHorario='"+horario.getidHorario()+"'",null);
        //}
        contador+= db.delete("horario", "idHorario='"+horario.getidHorario()+"'",null);
        regAfectados+= contador;
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
            asignatura.setIdPrioridad(cursor.getInt(4));
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
                if(c.moveToFirst())
                    return true; //Se encontraron datos
                else
                    return false;
            }
            case 4:
            {
                //Horario horario = (Horario)dato;
               // Cursor cmat=db.query(true, "cargaAcademica", new String[] {"idHorario" }, "idHorario='"+horario.getidHorario()+"'",null, null, null, null, null);
                //if(cmat.moveToFirst())
                   // return true; //Se encontraron datos
               // else
                   // return false;
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
            default:
                return false;
        }
    }

    public String llenarBD(){

        // tabla Asignatura--------------------- 4 tuplas
        final String[] VAcodigoAsignatura = {"MAT115","PRN115","IEC115","TSI115"};
        final String[] VAcodigoLocal = {"B11","C31","c31","B11"};
        final String[] VAcodigoEscuela = {"Orantes","Ortiz","Gonzales","Coto"};
        final String[] VAnomAsignatura = {"Matematica I","Programacion I","Ingenieria Economica","Teoria de Sistemas"};
        final Integer[] VAidPrioridad = {1,3,3,4};

        //tabla Horario--------------------- 4 tuplas
        final Integer[] VHidHorario = {1,2,3,4};
        final String[] VHidDia = {"L","L","L","L"};
        final String[] VHhoraInicio = {"6:20","8:05","9:50","11:35"};
        final String[] VHhoraFin = {"8:00","9:45","11:30","13:15"};

        // tabla CargaAcademica--------------------- 7 tuplas
        final String[] VCidRolDocente = {"01","01","02","02","03","04","01"};
        final String[] VCcodigoAsignatura = {"MAT115","PRN115","IEC115","TSI115","IEC115","MAT115","PRN115"};
        final String[] VCcodigoCiclo = {"12016","12016","22016","22016","22020","22020","22016"};
        final String[] VCcarnetDocente = {"vvvvvvv","fffffff","sssssss","eeeeeee","ttttttt","vvvvvvv","fffffff"};

        abrir();
        db.execSQL("DELETE FROM asignatura");
        db.execSQL("DELETE FROM horario");
        db.execSQL("DELETE FROM cargaAcademica");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (1,'Lunes-Miercoles')");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (2,'Martes-Viernes')");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (3,'Jueves')");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (4,'Sabado');");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (5,'Domingo');");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (6,'Lunes');");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (7,'Martes');");
        db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES (8,'Miercoles');");
        db.execSQL("INSERT INTO prioridad (idPrioridad,codigoAsignatura,nivelPrioridad) VALUES (1,'MAT115','Alta');");
        db.execSQL("INSERT INTO prioridad (idPrioridad,codigoAsignatura,nivelPrioridad) VALUES (2,'PRN115','Media');");
        db.execSQL("INSERT INTO prioridad (idPrioridad,codigoAsignatura,nivelPrioridad) VALUES (3,'BAD115','Baja');");

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

        cerrar();
        return "Guardado Correctamente";
    }
}
