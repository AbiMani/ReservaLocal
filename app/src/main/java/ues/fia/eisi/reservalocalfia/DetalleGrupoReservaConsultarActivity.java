package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleGrupoReservaConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdGrupo, editCodigoAsignatura, editCodigoCiclo, editIdRolDocente,editCarnetDocente, editidHorario, editCodigoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_grupo_reserva_consultar);
        helper=new ControlReserveLocal(this);
        editIdGrupo=(EditText)findViewById(R.id.editIdGrupo);
        editIdRolDocente=(EditText)findViewById(R.id.editIdRolDocente);
        editCodigoAsignatura=(EditText)findViewById(R.id.editCodigoAsignatura);
        editCodigoCiclo=(EditText)findViewById(R.id.editCodciclo);
        editCarnetDocente=(EditText)findViewById(R.id.editCarnetDocente);
        editidHorario= (EditText) findViewById(R.id.editidHorario);
        editCodigoLocal= (EditText) findViewById(R.id.editcodigoLocal);
    }

    public void consultarDetalleGrupo(View v) {
        helper.abrir();
        DetalleGrupoReserva dgrupo = helper.consultarDetalleGrupo(String.valueOf(editidHorario.getText().toString()),String.valueOf(editIdRolDocente.getText().toString()),
                editCodigoAsignatura.getText().toString(),editCodigoCiclo.getText().toString(),editCarnetDocente.getText().toString(),editIdGrupo.getText().toString());
        helper.cerrar();
        if(dgrupo == null)
            Toast.makeText(this, "Grupo no registrado", Toast.LENGTH_LONG).show();
        else{
            editCodigoLocal.setText(String.valueOf(dgrupo.getCodigoLocal()));
        }
    }
    public void limpiarTexto(View v) {
        editIdGrupo.setText("");
        editIdRolDocente.setText("");
        editCodigoAsignatura.setText("");
        editCodigoCiclo.setText("");
        editCarnetDocente.setText("");
        editCodigoLocal.setText("");
        editidHorario.setText("");
    }
}
