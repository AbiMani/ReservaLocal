package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editCodigoLocal;
    EditText editIdEncargadoLocal;
    EditText editIdTipoLocal;
    EditText editUbicacionLocal;
    EditText editCapacidadLocal;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        helper = new ControlReserveLocal(this);
        editCodigoLocal = (EditText) findViewById(R.id.editCodigoLocal);
        editIdEncargadoLocal = (EditText) findViewById(R.id.editIdEncargadoLocal);
        editIdTipoLocal = (EditText) findViewById(R.id.editIdTipoLocal);
        editCapacidadLocal = (EditText) findViewById(R.id.editCapacidadLocal);
        editUbicacionLocal = (EditText) findViewById(R.id.editUbicacionLocal);
    }
    public void consultar(View v) {
        helper.abrir();
        Local local =
                helper.consultarLocal(editCodigoLocal.getText().toString());
        helper.cerrar();
        if(local == null)
            Toast.makeText(this, "Local con codigo " +
                    editCodigoLocal.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCodigoLocal.setText(local.getCodigoLocal());
            editIdEncargadoLocal.setText(local.getIdEncargadoLocal());
            editIdTipoLocal.setText(local.getIdTipoLocal());
            editCapacidadLocal.setText(String.valueOf(local.getCapacidadLocal()));


        } }
    public void limpiarTexto(View v){
        editCodigoLocal.setText("");
        editIdEncargadoLocal.setText("");
        editIdTipoLocal.setText("");
        editCapacidadLocal.setText("");
        editUbicacionLocal.setText("");
    }
    }

