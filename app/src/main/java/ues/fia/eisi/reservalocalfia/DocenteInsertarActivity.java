package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteInsertarActivity extends Activity { //cambiar por activity correcto

    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editNombreDocente;
    EditText editApellido;
    EditText editRol;
    EditText editNomEscuela;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlReserveLocal(this);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editNombreDocente = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editRol = (EditText) findViewById(R.id.editRol);
        editNomEscuela = (EditText) findViewById(R.id.editNomEscuela);
    }
    public void insertarDocente(View v) {
        String carnetDocente=editCarnetDocente.getText().toString();
        String nombreDocente=editNombreDocente.getText().toString();
        String apellido=editApellido.getText().toString();
        String rol=editRol.getText().toString();
        String nomEscuela=editNomEscuela.getText().toString();
        String regInsertados;
        Docente docente=new Docente();
        docente.setCarnetDocente(carnetDocente);
        docente.setNombreDocente(nombreDocente);
        docente.setApellido(apellido);
        docente.setRol(rol);
        docente.setNomEscuela(nomEscuela);
        helper.abrir();
        regInsertados=helper.insertar(docente);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {

        editCarnetDocente.setText("");
        editNombreDocente.setText("");
        editApellido.setText("");
        editRol.setText("");
        editNomEscuela.setText("");
    }
}
