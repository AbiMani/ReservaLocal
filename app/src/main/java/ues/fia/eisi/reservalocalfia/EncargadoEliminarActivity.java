package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoEliminarActivity extends AppCompatActivity {

    EditText editIdEncargadoLocal;
    ControlReserveLocal controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_eliminar);
        controlhelper=new ControlReserveLocal (this);
        editIdEncargadoLocal=(EditText)findViewById(R.id.editIdEncargadoLocal);
    }

    public void eliminar(View v){
        String regEliminadas;
        Encargado encargadoLocal=new Encargado();
        encargadoLocal.setIdEncargadoLocal(editIdEncargadoLocal.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(encargadoLocal);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    }

