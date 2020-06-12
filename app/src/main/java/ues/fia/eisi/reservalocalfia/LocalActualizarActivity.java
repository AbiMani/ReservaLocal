package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoLocal;
    EditText editIdEncargadoLocal;
    EditText editIdTipoLocal;
    EditText editUbicacionLocal;
    EditText editCapacidadLocal;

    /**
     * Called when the activity is first created.
     */
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

    public void actualizarLocal(View v) {
        Local local = new Local();
        local.setCodigoLocal(editCodigoLocal.getText().toString());
        local.setIdTipoLocal(editIdEncargadoLocal.getText().toString());
        local.setIdEncargadoLocal(editIdEncargadoLocal.getText().toString());
        local.setUbicacionLocal(editUbicacionLocal.getText().toString());
        local.setCapacidadLocal(editCapacidadLocal.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(local);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCodigoLocal.setText("");
        editIdTipoLocal.setText("");
        editIdEncargadoLocal.setText("");
        editCapacidadLocal.setText("");
        editUbicacionLocal.setText("");


    }
}

