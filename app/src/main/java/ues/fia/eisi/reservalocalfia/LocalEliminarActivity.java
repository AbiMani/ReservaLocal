package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalEliminarActivity extends Activity {
    EditText editCarnet;
    ControlReserveLocal controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        controlhelper=new ControlReserveLocal  (this);
        editCarnet=(EditText)findViewById(R.id.editCodigoLocal);
    }
    public void eliminarLocal(View v){
        String regEliminadas;
        Local local=new Local();
        local.setCodigoLocal(editCarnet.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(local);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    } }



