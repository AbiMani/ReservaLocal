package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EncargadoActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdEncargadoLocal;
    EditText editNomEncargadoLocal;
    EditText editApeEncargadoLocal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_actualizar);
        helper = new ControlReserveLocal(this);
        editIdEncargadoLocal = (EditText) findViewById(R.id.editIdEncargadoLocales);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editNomEncargadoLocal);
        editApeEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoLocal);
    }

    public void actualizarEncargado(View v) {
        Encargado encargado = new Encargado();
        encargado.setIdEncargadoLocal(editIdEncargadoLocal.getText().toString());
        encargado.setNomEncargadoLocal(editNomEncargadoLocal.getText().toString());
        encargado.setApeEncargadoLocal(editApeEncargadoLocal.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(encargado);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdEncargadoLocal.setText("");
        editNomEncargadoLocal.setText("");
        editApeEncargadoLocal.setText("");
    }
}

