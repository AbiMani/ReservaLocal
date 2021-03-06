package ues.fia.eisi.reservalocalfia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

import static java.sql.Types.NULL;

@SuppressLint("NewApi")
public class RolDocenteInsertarWsActivity extends AppCompatActivity {

    EditText editNomRolDocente;
    EditText editDescripcionRol;
    int idRolDocente=NULL;
    EditText editFecha_inicio;
    EditText editFecha_fin;
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_rolDocente_insertar.php";
    private final String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi04/AH13059/ws_rolDocente_insertar.php";
    //private String urlPublicoUES =
    //"https://eisi.fia.ues.edu.sv/eisi25/MQ25001/ws_nota_insert.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_docente_insertar_ws);
        //helper = new ControlReserveLocal(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        editNomRolDocente = (EditText) findViewById(R.id.editNomRolDocente);
        editDescripcionRol = (EditText) findViewById(R.id.editDescripcionRol);

    }

    public void insertarRolDoce(View v){
        JSONObject datosNota = new JSONObject();
        JSONObject nota = new JSONObject();
        String url=null;
        String nomRolDocente=editNomRolDocente.getText().toString();
        String descripcionRol=editDescripcionRol.getText().toString();
        String regInsertados;

        switch (v.getId()) {

            case R.id.btn_insertarPublicoUES:
                url = urlPublicoUES+ "?idRolDocente=" + idRolDocente + "&nomRolDocente=" + nomRolDocente + "&descripcionRol=" + descripcionRol;
                ControladorServicioJavier.insertarCiclo(url, this);
                break;
            case R.id.btn_insertarExterno:
                url = urlHostingGratuito+ "?idRolDocente=" + idRolDocente + "&nomRolDocente=" + nomRolDocente + "&descripcionRol=" + descripcionRol;
                ControladorServicioJavier.insertarCiclo(url, this);
                break;
        }

        //url = urlHostingGratuito+ "?idRolDocente=" + idRolDocente + "&nomRolDocente=" + nomRolDocente + "&descripcionRol=" + descripcionRol;
        //ControladorServicioJavier.insertarCiclo(url, this);
    }

    public void limpiarTexto(View v) {
        editDescripcionRol.setText("");
        editNomRolDocente.setText("");
    }
}
