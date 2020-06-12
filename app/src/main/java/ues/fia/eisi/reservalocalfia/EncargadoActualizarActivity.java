package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdEncargadoLocal;
    EditText editNomEncargadoLocal;
    EditText editApeENcargadoLocal;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_actualizar);
        helper = new ControlReserveLocal(this);
        editIdEncargadoLocal = (EditText) findViewById(R.id.editIdEncargadoLocal);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoLocal);
        editApeENcargadoLocal = (EditText) findViewById(R.id.editApellido);

    }
    public void actualizarAlumno(View v) {
        Encargado encargado = new Encargado();
        encargado.setIdEncargadoLocal(editIdEncargadoLocal.getText().toString());
        encargado.setNomEncargadoLocal(editNomEncargadoLocal.getText().toString());
        encargado.setApeEncargadoLocal(editApeENcargadoLocal.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(encargado);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdEncargadoLocal.setText("");
        editNomEncargadoLocal.setText("");
        editApeENcargadoLocal.setText("");

    } }

