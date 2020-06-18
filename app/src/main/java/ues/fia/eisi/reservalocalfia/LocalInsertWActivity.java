package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class LocalInsertWActivity extends AppCompatActivity {

        EditText codigolocaltxt;
        EditText idencargadolocaltxt;
        EditText idtipolocaltxt;
        EditText ubicacionlocaltxt;
        EditText capacidadlocaltxt;


        private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_ciclo_insert.php";
        private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi25/MQ25001/ws_dia_insert.php";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_local_insert_w);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            codigolocaltxt = (EditText) findViewById(R.id.editCodigoLocal);
            idencargadolocaltxt = (EditText) findViewById(R.id.editIdEncargadoLocal);
            idtipolocaltxt = (EditText) findViewById((R.id.editIdTipoLocal));
            ubicacionlocaltxt=(EditText)findViewById(R.id.editUbicacionLocal);
            capacidadlocaltxt=(EditText)findViewById(R.id.editCodigoLocal);
        }

        public void insertarLocal(View v) {
            String codigoLocal = codigolocaltxt.getText().toString();
            String idencargadolocal = idencargadolocaltxt.getText().toString();
            String idtipolocal = idtipolocaltxt.getText().toString();
            String ubicacionlocal = ubicacionlocaltxt.getText().toString();
            String capacidadlocal = capacidadlocaltxt.getText().toString();


            String url = null;
            JSONObject datosCiclo = new JSONObject();
            JSONObject ciclo = new JSONObject();
            switch (v.getId()) {

                case R.id.btn_localesLocal:
                    url = urlPublicoUES + "?codigoLocal=" + codigoLocal + "&idEncargadoLocal=" + idencargadolocal + "&idTipoLocal=" + idtipolocal + "&ubicacionLocal+" + ubicacionlocal + "&capacidadLocal" +capacidadlocal;
                    ControladorServicio.insertarCicloExterno(url, this);
                    break;
                case R.id.btn_localExterno:
                    url = urlHostingGratuito +"?codigoLocal=" + codigoLocal + "&idEncargadoLocal=" + idencargadolocal + "&idTipoLocal=" + idtipolocal + "&ubicacionLocal+" + ubicacionlocal + "&capacidadLocal" +capacidadlocal;
                    ControladorServicio.insertarCicloExterno(url, this);
                    break;
            }
        }
        public void limpiarTexto(View v) {
            codigolocaltxt.setText("");
            idencargadolocaltxt.setText("");
            idtipolocaltxt.setText("");
            ubicacionlocaltxt.setText("");
            capacidadlocaltxt.setText("");
        }
    }
