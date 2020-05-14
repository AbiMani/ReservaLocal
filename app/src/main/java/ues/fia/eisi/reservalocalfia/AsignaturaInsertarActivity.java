package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaturaInsertarActivity extends Activity {
    
        ControlReserveLocal helper;
        EditText editCodigoAsignatura;
        EditText editCodigoLocal;
        EditText editCodigoEscuela;
        EditText editNomAsignatura;
        EditText editIdPrioridad;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState); setContentView(R.layout.activity_asignatura_insertar);
            helper = new ControlReserveLocal(this);
            editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
            editCodigoLocal = (EditText) findViewById(R.id.editCodigoLocal);
            editCodigoEscuela = (EditText) findViewById(R.id.editCodigoEscuela);
            editNomAsignatura = (EditText) findViewById(R.id.editNomAsignatura);
            editIdPrioridad = (EditText) findViewById(R.id.editIdPrioridad);
        }
        public void insertarAsignatura(View v) {
            String codigoAsignatura=editCodigoAsignatura.getText().toString();
            String codigoLocal=editCodigoLocal.getText().toString();
            String codigoEscuela=editCodigoEscuela.getText().toString();
            String nomAsignatura=editNomAsignatura.getText().toString();
            Integer idPrioridad= Integer.valueOf(editIdPrioridad.getText().toString());
            String regInsertados;
            Asignatura asignatura=new Asignatura();
            asignatura.setCodigoAsignatura(codigoAsignatura);
            asignatura.setCodigoLocal(codigoLocal);
            asignatura.setCodigoEscuela(codigoEscuela);
            asignatura.setNomAsignatura(nomAsignatura);
            asignatura.setIdPrioridad(idPrioridad);
            helper.abrir();
            regInsertados=helper.insertar(asignatura);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        public void limpiarTexto(View v) {
            editCodigoAsignatura.setText("");
            editCodigoLocal.setText("");
            editCodigoEscuela.setText("");
            editNomAsignatura.setText("");
        }
    
}
