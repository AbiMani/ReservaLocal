package ues.fia.eisi.reservalocalfia;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editNombreDocente;
    EditText editApellido;
    EditText editRol;
    EditText editNomEscuela;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new ControlReserveLocal(this);

        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editNombreDocente = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editRol = (EditText) findViewById(R.id.editRol);
        editNomEscuela = (EditText) findViewById(R.id.editNomEscuela);
    }

    public void actualizarDocente(View v) {
        Docente variable = new Docente();
        variable.setCarnetDocente(editCarnetDocente.getText().toString());
        variable.setNombreDocente(editNombreDocente.getText().toString());
        variable.setApellido(editApellido.getText().toString());
        variable.setRol(editRol.getText().toString());
        variable.setNomEscuela(editNomEscuela.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(variable);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {

        editCarnetDocente.setText("");
        editNombreDocente.setText("");
        editApellido.setText("");
        editRol.setText("");
        editNomEscuela.setText("");
    }
}


