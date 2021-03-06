package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargaAcademicaEliminarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editCodigoAsignatura;
    EditText editCodigoCiclo;
    EditText editIdRolDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_eliminar);
        helper = new ControlReserveLocal(this);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodigoCiclo);
    }

    public void eliminarCargaAcademica(View v){
        String regEliminadas;
        CargaAcademica cargaAcademica=new CargaAcademica();
        cargaAcademica.setcodigoAsignatura(editCodigoAsignatura.getText().toString());
        cargaAcademica.setcarnetDocente(editCarnetDocente.getText().toString());
        cargaAcademica.setcodigoCiclo(editCodigoCiclo.getText().toString());
        helper.abrir();
        regEliminadas=helper.eliminar(cargaAcademica);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        editCodigoAsignatura.setText("");
        editCarnetDocente.setText("");
        editCodigoCiclo.setText("");
        editIdRolDocente.setText("");
    }
}
