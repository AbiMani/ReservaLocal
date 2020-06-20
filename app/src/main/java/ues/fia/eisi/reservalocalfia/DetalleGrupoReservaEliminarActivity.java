package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleGrupoReservaEliminarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdGrupo, editCodigoAsignatura, editCodigoCiclo, editIdRolDocente,editCarnetDocente, editidHorario, editCodigoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_grupo_reserva_eliminar);
        helper=new ControlReserveLocal(this);
        editIdGrupo=(EditText)findViewById(R.id.editIdGrupo);
        editIdRolDocente=(EditText)findViewById(R.id.editIdRolDocente);
        editCodigoAsignatura=(EditText)findViewById(R.id.editCodigoAsignatura);
        editCodigoCiclo=(EditText)findViewById(R.id.editCodciclo);
        editCarnetDocente=(EditText)findViewById(R.id.editCarnetDocente);
        editidHorario= (EditText) findViewById(R.id.editidHorario);
        editCodigoLocal= (EditText) findViewById(R.id.editcodigoLocal);
    }

    public void eliminarDetalleGrupo(View v){
        String regEliminados;
        DetalleGrupoReserva detalleGrupo=new DetalleGrupoReserva();
        detalleGrupo.setIdGrupo(editIdGrupo.getText().toString());
        detalleGrupo.setIdroldocente(Integer.valueOf(editIdRolDocente.getText().toString()));
        detalleGrupo.setCodigoAsignatura(editCodigoAsignatura.getText().toString());
        detalleGrupo.setCodigoCiclo(editCodigoCiclo.getText().toString());
        detalleGrupo.setCarnetDocente(editCarnetDocente.getText().toString());
        helper.abrir();
        regEliminados=helper.eliminarDetalleGrupo(detalleGrupo);
        helper.cerrar();
        Toast.makeText(this, regEliminados, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editIdGrupo.setText("");
        editIdRolDocente.setText("");
        editCodigoAsignatura.setText("");
        editCodigoCiclo.setText("");
        editCarnetDocente.setText("");
        editCodigoLocal.setText("");
        editidHorario.setText("");
    }
}
