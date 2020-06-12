package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdEncargadoLocal;
    EditText editNomEncargadoLocal;
    EditText editApeEncargadoLocal;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_consultar);
        helper = new  ControlReserveLocal (this);
        editIdEncargadoLocal = (EditText) findViewById(R.id.editIdEncargadoLocal);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editNomEncargadoLocal);
        editApeEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoLocal);

    }
    public void consultarEncargado  (View v) {
        helper.abrir();
        Encargado encargado = helper.consultarEncargado(editIdEncargadoLocal.getText().toString());
        helper.cerrar();
        if(encargado == null)
            Toast.makeText(this, "encargado con codigo " +
                    editIdEncargadoLocal.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNomEncargadoLocal.setText(encargado.getNomEncargadoLocal());
            editApeEncargadoLocal.setText(encargado.getApeEncargadoLocal());

        } }
    public void limpiarTexto(View v){
        editIdEncargadoLocal.setText("");
        editNomEncargadoLocal.setText("");
        editApeEncargadoLocal.setText("");

    }
}