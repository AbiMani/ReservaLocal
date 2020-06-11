package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

public class InsertReservaActivity extends AppCompatActivity {
    ControlReserveLocal db;
    EditText editCodigoEscuela;
    EditText editnombreEvento;
    EditText editCapacidad;
    EditText editFechaEvento;
    EditText editidtipoevento;
    private final String urlLocal = "http://192.168.1.6/ws_reserva_insert.php";
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_reserva_insert.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi04/BV12004/ws_reserva_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_reserva);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editCapacidad=(EditText) findViewById(R.id.editCapacidad);
        editFechaEvento=(EditText) findViewById(R.id.editFecha);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        editidtipoevento=(EditText) findViewById(R.id.editidTipoEvento);
    }

    public void insertarReservaWS(View v) {
        String codigoEsc=editCodigoEscuela.getText().toString();
        String nomEve=editnombreEvento.getText().toString();
        String capacidad=editCapacidad.getText().toString();
        String fechaEve=editFechaEvento.getText().toString();
        String idTipoEve=editidtipoevento.getText().toString();
        String url = null;
        JSONObject datosReserva = new JSONObject();
        JSONObject reserva = new JSONObject();
        switch (v.getId()) {
            case R.id.btn_reservaPhp:
                if (codigoEsc.equals("") || nomEve.equals("") ||capacidad.equals("") || fechaEve.equals("") || idTipoEve.equals("") )
                {
                    Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();}
                else {
                    url = urlHostingGratuito + "?codigoEscuela=" + codigoEsc + "?idTipoEvento=" + idTipoEve + "&nombreEvento=" + nomEve + "?capacidadEvento="
                            + capacidad + "&fechaEvento=" + fechaEve  ;
                    ControladorServicio.insertarReservaExterno(url, this);
                }
                break;
            case R.id.btn_reservaLocalUES:
                if (codigoEsc.equals("") || nomEve.equals("") ||capacidad.equals("") || fechaEve.equals("") || idTipoEve.equals(""))
                {
                    Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();}
                else {
                    url = urlPublicoUES +  "?codigoEscuela=" + codigoEsc + "?idTipoEvento=" + idTipoEve + "&nombreEvento=" + nomEve + "?capacidadEvento="
                            + capacidad + "&fechaEvento=" + fechaEve;
                    ControladorServicio.insertarReservaExterno(url, this);
                }
                break;
        }
    }
    public void limpiarTexto(View v) {
        editCodigoEscuela.setText("");
        editnombreEvento.setText("");
        editFechaEvento.setText("");
        editCapacidad.setText("");
        editidtipoevento.setText("");

    }
}
