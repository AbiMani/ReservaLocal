package ues.fia.eisi.reservalocalfia;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] menu={"Asignatura","Carga Academica","Horario","Reserva de Evento","Detalle de reserva","Tipo de Evento","Tabla Local",
            "Tabla Encargado","Tabla Detalle Grupo Reserva",
            "Gestion Docente","Gestion Rol de Docentes","Gestion del tipo de local","Tabla Ciclo","Tabla Grupo","Tabla Dias No Habiles","Gestion Escuela","Servicios Web","LLenar Base de Datos"};
    String[] activities={"AsignaturaMenuActivity","CargaAcademicaMenuActivity","HorarioMenuActivity", "ReservaEventoMenuActivity",
            "LocalMenuActivity","EncargadoMenuActivity","DetalleGrupoReservaMenuActivity",
            "DetalleReservaMenuActivity","TipoEventoMenuActivity","DocenteMenuActivity", "RolDocenteMenuActivity","TipoLocalMenuActivity","CicloMenuActivity","GrupoMenuActivity","DiasNoHabilesMenuActivity","EscuelaMenuActivity","ServiciosMenuActivity"};
    ControlReserveLocal BDhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        BDhelper= new ControlReserveLocal(this);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        if(position!=14) {
            String nombreValue = activities[position];
            try {
                Class<?> clase = Class.forName("ues.fia.eisi.reservalocalfia." + nombreValue);
                Intent inte = new Intent(this, clase);
                this.startActivity(inte);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
             BDhelper.abrir();
             String tost =BDhelper.llenarBD();
             BDhelper.cerrar();
             Toast.makeText(this,tost, Toast.LENGTH_SHORT).show();
        }
    }
}

